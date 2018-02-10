package com.example.matthallowell.uncommonhacks2018

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Matt Hallowell on 2/10/2018.
 */
interface WikipediaApiService {
//    @GET("api.php")
//    fun hitCountCheck(@Query("action") action: String,
//                      @Query("format") format: String,
//                      @Query("list") list: String,
//                      @Query("srsearch") srsearch: String): Observable<Model.Result>

    @GET("api.php")
    fun fetchArticle(@Query("action") action: String,
                     @Query("prop") prop: String,
                     @Query("rvprop") rvprop: String,
                     @Query("rvsection") rvsection: Int,
                     @Query("titles") titles: String,
                     @Query("format") format: String) : Observable<ArticleModel.Result>

    companion object {
        fun create(): WikipediaApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://en.wikipedia.org/w/")
                    .build()
            return retrofit.create(WikipediaApiService::class.java)
        }
    }
}