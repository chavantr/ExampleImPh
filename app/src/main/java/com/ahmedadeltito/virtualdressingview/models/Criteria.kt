package com.mywings.haircutrecommendation.models

data class Criteria(
    var id: Int = 0,
    var iid: Int = 0,
    var one: Boolean = false,
    var front: Boolean = false,
    var skin: Int = 0
)