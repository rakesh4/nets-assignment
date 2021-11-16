package com.example.practice.api

object HttpConstants {
    const val STATUS_CODE_UNAUTHORIZED_401: Int = 401
    const val STATUS_CODE_OK: Int = 200
    const val STATUS_CODE_500: Int = 500
    const val STATUS_CODE_NO_INTERNET: Int = 999
    const val STATUS_CODE_BAD_REQ_PARAM_400: Int = 400
    const val BEARER = "bearer "
    const val EMPTY_VALUE = ""


    // API REQUEST PARAM
    const val REQUEST_PARAM_LATITUDE = "lat"
    const val REQUEST_PARAM_LONGITUDE = "lon"
    const val LOCATION_APP_ID = "appid"

    // API METHODS END POINT
    const val METHOD_GET_FIVE_DAYS_FORECAST = "data/2.5/forecast"
    const val METHOD_GET_TODAY_FORECAST = "data/2.5/weather"




}