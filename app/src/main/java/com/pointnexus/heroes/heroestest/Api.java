package com.pointnexus.heroes.heroestest;

import com.pointnexus.heroes.heroestest.Models.Classes;
import com.pointnexus.heroes.heroestest.Models.Heroi;
import com.pointnexus.heroes.heroestest.Models.HeroiTeste;
import com.pointnexus.heroes.heroestest.Models.Magias;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://heroes.qanw.com.br:6847/";

    @PUT("heroes/{id}")
    Call<HeroiTeste> atualizarHeroi(@Header("key") String key,
                               @Header("value") String value,
                               @Header("description") String description,
                               @Path("id") String id,
                               @Body HeroiTeste heroi);

    @DELETE("heroes/{id}")
    Call<Heroi> deletarHeroi(@Path("id") String id);

    @GET("heroes/{id}")
    Call<Heroi> pegarHerois(@Path("id") String id);

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
    Call<HeroiTeste> createUserr(@Header("key") String key,
                                 @Header("value") String value,
                                 @Header("description") String description,
                                 @Body HeroiTeste heroiteste);


    @Multipart
    @POST("photos")
    Call<ResponseBody> postImage(@Header("key") String key,
                                 @Header("value") String value,
                                 @Header("description") String description,
            @Part MultipartBody.Part image);
}
