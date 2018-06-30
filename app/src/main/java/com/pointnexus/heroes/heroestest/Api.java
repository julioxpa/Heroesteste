package com.pointnexus.heroes.heroestest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "http://heroes.qanw.com.br:6847/";

    @PUT("heroes/{id}")
    Call<Heroi> atualizarHeroi(@Header("key") String key,
                               @Header("value") String value,
                               @Header("description") String description,
                               @Path("id") String id,
                               @Body Heroi heroi);

    @DELETE("heroes/{id}")
    Call<Heroi> deletarHeroi(@Path("id") String id);

    @GET("heroes")
    Call<List<Heroi>> pegarHerois();

    @GET("classes")
    Call<List<Classes>> pegarClasses();

    @GET("classes/{id}")
    Call<Classes> pegarClasses(@Path("id") String id);

    @GET("specialties")
    Call<List<Magias>> pegarMagias();

    @GET("specialties/{id}")
    Call<Magias> pegarMagias(@Path("id") String id);

    @POST("heroes")
    Call<Heroi> createUser(@Header("key") String key,
                           @Header("value") String value,
                           @Header("description") String description,
                           @Body Heroi heroi);

}
