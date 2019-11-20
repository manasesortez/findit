package com.wazunga.nhulox97.findit.models

/*
* Created by wazunga.nhulox97 on 18/11/19.
*/

data class Place(
    val geometry: PlaceGeometry,
    val icon: String,
    val id: String,
    val name: String,
    val opening_hours: PlaceOpenHours,
    val place_id: String,
    val rating: Double,
    val user_ratings_total: Int,
    val vicinity: String
)