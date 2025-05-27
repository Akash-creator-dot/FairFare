//package com.example.fairfare;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class UberLoginActivity extends AppCompatActivity {
//    WebView webView;
//    String clientId = "YOUR_CLIENT_ID";
//    String redirectUri = "https://localhost/callback";
//    String clientSecret = "YOUR_CLIENT_SECRET"; // Keep it safe, not in public repos
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_uber_login);
//        webView = findViewById(R.id.webview);
//        webView.setWebViewClient(new WebViewClient() {
//            public void onPageFinished(WebView view, String url) {
//                if (url.startsWith(redirectUri)) {
//                    Uri uri = Uri.parse(url);
//                    String code = uri.getQueryParameter("code");
//                    if (code != null) {
//                        getAccessToken(code); // Proceed to fetch access token
//                    }
//                }
//            }
//        });
//
//        String authUrl = "https://login.uber.com/oauth/v2/authorize?client_id=" + clientId
//                + "&response_type=code"
//                + "&redirect_uri=" + redirectUri
//                + "&scope=ride_widgets";
//
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl(authUrl);
//    }
//
//    private void getAccessToken(String code) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://login.uber.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        UberOAuthService service = retrofit.create(UberOAuthService.class);
//
//        Call<TokenResponse> call = service.getAccessToken(
//                "authorization_code",
//                clientId,
//                clientSecret,
//                redirectUri,
//                code
//        );
//
//        call.enqueue(new Callback<TokenResponse>() {
//            @Override
//            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
//                if (response.isSuccessful()) {
//                    String accessToken = response.body().accessToken;
//                    Toast.makeText(UberLoginActivity.this, "Access Token: " + accessToken, Toast.LENGTH_LONG).show();
//
//                    // Save token and start UberPriceActivity
//                    Intent intent = new Intent(UberLoginActivity.this, UberPriceActivity.class);
//                    intent.putExtra("access_token", accessToken);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(UberLoginActivity.this, "Token fetch failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TokenResponse> call, Throwable t) {
//                Toast.makeText(UberLoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
