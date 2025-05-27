//package com.example.fairfare;
//
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import java.io.IOException;
//
//public class RetrofitClient {
//
//    private static Retrofit retrofit = null;
//
//    public static Retrofit getClient() {
//        if (retrofit == null) {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request request = chain.request().newBuilder()
//                                    .addHeader("Authorization", "eO9X3gfr-CJQN35hTQAqncENev1OdgVQ0Mt2GBcZ")
//                                    .build();
//                            return chain.proceed(request);
//                        }
//                    }).build();
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl("https://api.uber.com/")
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//}
