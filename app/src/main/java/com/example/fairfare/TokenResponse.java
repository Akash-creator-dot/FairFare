package com.example.fairfare;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("expires_in")
    public int expiresIn;
}
