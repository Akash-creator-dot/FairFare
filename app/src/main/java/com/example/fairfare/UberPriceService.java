//package com.example.fairfare;
//
//import retrofit2.Call;
//import retrofit2.http.Header;
//import retrofit2.http.Query;
//
//public interface UberPriceService {
//    @GET("v1.2/estimates/price")
//    Call<UberEstimateResponse> getPriceEstimates(
//            @Header("Authorization") String bearerToken,
//            @Query("start_latitude") double startLat,
//            @Query("start_longitude") double startLng,
//            @Query("end_latitude") double endLat,
//            @Query("end_longitude") double endLng
//    );
//}
//
