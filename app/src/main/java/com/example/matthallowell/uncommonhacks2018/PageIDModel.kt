package com.example.matthallowell.uncommonhacks2018

/**
 * Created by Matt Hallowell on 2/10/2018.
 */
object PageIDModel {
    data class Result(val query: Query)
    data class Query(val pages: Page)
    data class Page(val pageid: PageID)
    data class PageID(val pageid: Int)
}