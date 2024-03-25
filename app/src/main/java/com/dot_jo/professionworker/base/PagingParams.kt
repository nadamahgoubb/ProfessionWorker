package com.dot_jo.professionworker.base



open class PagingParams : HashMapParams {
    var page: Int = 1
    override fun dataClass(): Any {
        return 0
    }
}

interface HashMapParams {
    fun dataClass(): Any
}