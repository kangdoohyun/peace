package com.base.newPeaceSystemBuild.vo.standard


// 제단꽃은 name 칼럼이 없고, id를 통해 '호'수로 구분한다.
data class Flower(
    val id: Int,
    val regDate: String,
    var updateDate: String,
    var retailPrice: String,
    var costPrice: String
)
