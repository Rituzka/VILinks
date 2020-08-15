package com.e.vilinks.model

import android.os.Parcel
import android.os.Parcelable

class Topics(val name: String, val links: ArrayList<String> = ArrayList()): Parcelable {

    constructor(parcel:Parcel): this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    companion object CREATOR: Parcelable.Creator<Topics> {
        override fun createFromParcel(source: Parcel): Topics  = Topics(source)

        override fun newArray(size: Int): Array<Topics?> = arrayOfNulls(size)

    }


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(links)
    }

    override fun describeContents() = 0


}
