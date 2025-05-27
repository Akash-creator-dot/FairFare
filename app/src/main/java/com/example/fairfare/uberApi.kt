//package com.example.fairfare
//
//import retrofit2.Call
//import retrofit2.http.GET
//import retrofit2.http.Query
//
//interface UberApi {
//    @GET("v1/estimates/price")
//    fun getPriceEstimates(
//        @Query("start_latitude") startLatitude: Double,
//        @Query("start_longitude") startLongitude: Double,
//        @Query("end_latitude") endLatitude: Double,
//        @Query("end_longitude") endLongitude: Double
//    ): Call<UberPriceResponse?>?
//}