//package com.example.fairfare;
//
//import retrofit2.Call;
//import retrofit2.http.Field;
//import retrofit2.http.FormUrlEncoded;
//import retrofit2.http.POST;
//
//public interface UberOAuthService {
//    @FormUrlEncoded
//    @POST("oauth/v2/token")
//    Call<TokenResponse> getAccessToken(
//            @Field("grant_type") String grantType,
//            @Field("client_id") String clientId,
//            @Field("client_secret") String clientSecret,
//            @Field("redirect_uri") String redirectUri,
//            @Field("code") String code
//    );
//}
//
