package com.example.ceudegraos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // Método para registrar um novo usuário (POST)
    @POST("api/Usuarios/cadastrar")
    Call<Void> cadastrarUsuario(@Body Usuario usuario);

    // Método para consultar um usuário específico pelo email (GET)
    @GET("api/Usuarios/email/{email}")
    Call<Usuario> consultarUsuarioPorEmail(@Path("email") String email);
}

