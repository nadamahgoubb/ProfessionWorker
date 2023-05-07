package com.example.professionworker.base

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class BasePagingResponse<T> (
    @SerializedName("data") var data: DataPaging<T>? = null
/*

    @SerializedName("page") var page: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("orders") var listOfData: ArrayList<T>? = arrayListOf()
*/


    ) : BaseResponse(){
    constructor(parcel: Parcel) : this()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Parcelable> {
        override fun createFromParcel(parcel: Parcel): DevResponse<Parcelable> {
            return DevResponse(parcel)
        }

        override fun newArray(size: Int): Array<Parcelable?> {
            return arrayOfNulls(size)
        }
    }
    }
/*

    @Parcelize
    data class ClientChatResponse(
        val id: Int? = null,
        val name: String? = null,
        val image: String? = null
    ) : Parcelable
*/



