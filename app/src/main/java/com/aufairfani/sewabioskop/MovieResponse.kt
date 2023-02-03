package com.aufairfani.sewabioskop

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("errorMessage")
	val errorMessage: String,

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

data class ItemsItem(

	@field:SerializedName("imDbRating")
	val imDbRating: String?,

	@field:SerializedName("image")
	val image: String?,

	@field:SerializedName("runtimeMins")
	val runtimeMins: String?,

	@field:SerializedName("year")
	val year: String?,

	@field:SerializedName("genres")
	val genres: String?,

	@field:SerializedName("title")
	val title: String?
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(imDbRating)
		parcel.writeString(image)
		parcel.writeString(runtimeMins)
		parcel.writeString(year)
		parcel.writeString(genres)
		parcel.writeString(title)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ItemsItem> {
		override fun createFromParcel(parcel: Parcel): ItemsItem {
			return ItemsItem(parcel)
		}

		override fun newArray(size: Int): Array<ItemsItem?> {
			return arrayOfNulls(size)
		}
	}
}
