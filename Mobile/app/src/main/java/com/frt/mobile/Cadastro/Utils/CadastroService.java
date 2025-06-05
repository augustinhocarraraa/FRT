package com.frt.mobile.Cadastro.Utils;

import com.frt.mobile.Cadastro.Data.Cadastro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CadastroService {
    @POST("api/cad/usuario")
    Call<String> signUpUser(@Body Cadastro Cadastro);
}
