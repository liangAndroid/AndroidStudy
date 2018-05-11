package com.liangtian.study.api

import com.liangtian.study.bean.SearchBooks
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by zhuliangtian on 2018/5/11.
 */
interface BookService {
    @GET("/book/fuzzy-search")
    fun searchBooks(@Query("query") query: String): Observable<SearchBooks>
}