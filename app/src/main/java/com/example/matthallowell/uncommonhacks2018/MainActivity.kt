package com.example.matthallowell.uncommonhacks2018

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private var disposable: Disposable? = null

    private val wikipediaApiService by lazy {
        WikipediaApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener{
            if (edit_search.text.toString().isNotEmpty()){
                beginSearch(edit_search.text.toString())
            }
        }

    }

    private fun beginSearch(searchString: String) {
        disposable = wikipediaApiService.fetchArticle("query","revisions","content",0,searchString,"json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> txt_search_result.text = "${result.query.searchinfo.article}"},
                        { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                )

    }


    override fun onPause(){
        super.onPause()
        disposable?.dispose()
    }
}
