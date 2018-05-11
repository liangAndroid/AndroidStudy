package com.liangtian.study.bean

/**
 * Created by zhuliangtian on 2018/5/11.
 */
data class SearchBooks(
        val books: List<SearchBook>
) {
    data class SearchBook(
            val _id: String,
            val hasCp: Boolean,
            val title: String,
            val cat: String,
            val author: String,
            val site: String,
            val cover: String,
            val shortIntro: String,
            val lastChapter: String,
            val retentionRatio: String,
            val latelyFollower: Int,
            val wordCount: Int
    )
}
