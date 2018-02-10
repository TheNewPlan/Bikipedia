package com.example.matthallowell.uncommonhacks2018

/**
 * Created by Matt Hallowell on 2/10/2018.
 */
object Model {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}