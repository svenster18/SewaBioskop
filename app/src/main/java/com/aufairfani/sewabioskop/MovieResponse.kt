package com.aufairfani.sewabioskop

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("errorMessage")
	val errorMessage: String,

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

data class ItemsItem(

	@field:SerializedName("imDbRating")
	val imDbRating: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("runtimeMins")
	val runtimeMins: String,

	@field:SerializedName("year")
	val year: String,

	@field:SerializedName("genres")
	val genres: String,

	@field:SerializedName("title")
	val title: String
)
