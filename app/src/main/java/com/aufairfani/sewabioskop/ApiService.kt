package com.aufairfani.sewabioskop

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("InTheaters/k_oyoxx7t2")
    fun getMovies() : Call<MovieResponse>
}