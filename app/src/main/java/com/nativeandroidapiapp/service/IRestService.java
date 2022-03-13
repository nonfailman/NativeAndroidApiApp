package com.nativeandroidapiapp.service;

import com.nativeandroidapiapp.data.ReceivedItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRestService {

    @GET("games")
    Call<List<ReceivedItem>> getItems();

    @GET("game?")
    Call<ReceivedItem> getItem(@Query("id") int id);

}
