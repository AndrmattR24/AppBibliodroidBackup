package com.andrmatt.appbibliodroid.service;

import com.andrmatt.appbibliodroid.models.dto.BookResponse;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiBook {

    @GET("books")
    Call<BookResponse> listBooks();
}
