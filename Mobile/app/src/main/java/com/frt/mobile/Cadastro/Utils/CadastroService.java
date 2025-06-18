package com.frt.mobile.Cadastro.Utils;

import com.frt.mobile.Cadastro.Data.Cadastro;
import com.frt.mobile.Shared.Utils.RetrofitClient; // Importe o RetrofitClient
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

// Primeiro, a interface Retrofit para o cadastro
interface CadastroApi {
    @POST("cadastrar") // Substitua "cadastrar" pelo endpoint real da sua API
    Call<Void> cadastrarUsuario(@Body Cadastro cadastro); // O objeto Cadastro agora inclui o perfil
}

// Em seguida, a classe de serviço que você instancia na Activity
public class CadastroService {
    private CadastroApi cadastroApi;

    public CadastroService() {
        // Crie a instância da interface Retrofit
        cadastroApi = RetrofitClient.getRetrofitInstance().create(CadastroApi.class);
    }

    public Call<Void> cadastrarUsuario(Cadastro cadastro) {
        return cadastroApi.cadastrarUsuario(cadastro);
    }
}