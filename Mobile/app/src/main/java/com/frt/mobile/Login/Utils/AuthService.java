// Mobile/app/src/main/java/com/frt/mobile/Login/Utils/AuthService.java
package com.frt.mobile.Login.Utils;

import com.frt.mobile.Login.Data.Login;
import com.frt.mobile.Login.Data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("auth/login") // Adapte este endpoint se for diferente na sua API
    Call<LoginResponse> login(@Body Login loginRequest);
}