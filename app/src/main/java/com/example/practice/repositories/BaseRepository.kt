package com.example.practice.repositories


import com.example.practice.api.HttpConstants
import com.example.practice.utility.AppConstants
import com.example.practice.utility.LocalizeTextProvider
import com.example.practice.utility.NoInternetException
import com.example.practice.api.Resource
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

open class BaseRepository (private val localizeProvider: LocalizeTextProvider
){

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {

            val response = call()
            return if (response.code() == HttpConstants.STATUS_CODE_OK) {
                val body = response.body()
                if (body != null)
                    Resource.success(body)
                else
                    Resource.empty(response.message())
            } else if (response.code() == HttpConstants.STATUS_CODE_UNAUTHORIZED_401) {
                Resource.error<T>(parseAuthError(response.errorBody()))
            } else if (response.code() == HttpConstants.STATUS_CODE_BAD_REQ_PARAM_400) {
                Resource.error<T>(parseBadRequest(response.errorBody()))
            }
            else {
                Resource.error<T>(parseAuthError(response.errorBody()))
            }
        } catch (e: Exception) {
            return if (e is NoInternetException)
                Resource.noInternet<T>(localizeProvider.getNoInternetMessage())
            else
                Resource.error<T>(localizeProvider.getSomethingWrongMessage())
        }
    }


    private fun parseAuthError(response: ResponseBody?): String {
        var errorMessage = localizeProvider.getSomethingWrongMessage()
        response?.let {
            val jsonObj = JSONObject(it.charStream().readText())
            try {
                if (jsonObj.has("detail")) {
                    errorMessage = jsonObj.optString("detail")
                }
            } catch (e: Exception) {
               //Log.d("JSON Parsing error ")
            }
        }
        return errorMessage.trim()
    }


    private fun parseBadRequest(response: ResponseBody?): String {
        var errorMessage = localizeProvider.getSomethingWrongMessage()
        response?.let {
            val jsonObj = JSONObject(it.charStream().readText())
            val keys: Iterator<String> = jsonObj.keys()
            val errorList = ArrayList<String>()
            try {
                while (keys.hasNext()) {
                    val jsonArrayOfError: JSONArray? =
                        jsonObj.optJSONArray(keys.next())
                    jsonArrayOfError?.let {
                        errorMessage = AppConstants.EMPTY_VALUE
                        for (i in 0 until jsonArrayOfError.length()) {
                            errorList.add(jsonArrayOfError[i].toString())
                        }
                    }
                    for (item in errorList) {
                        errorMessage = errorMessage + "\n" + item
                    }
                    break
                }
            } catch (e: Exception) {
               // Log.d("JSON Parsing error ")
            }
        }
        return errorMessage.trim()
    }
}