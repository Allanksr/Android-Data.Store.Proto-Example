package allanksr.com.datastore_example.domain.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName

data class DataStoreProto(
    @SerializedName("someName") val someName: String = "",
    @SerializedName("someNumber") val someNumber: Int = 0,
    @SerializedName("someBoolean") val someBoolean: Boolean = false,
    @SerializedName("someLong") val someLong: Long = 0L,
    @SerializedName("someFloat") val someFloat: Float = 0f,
) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q) // writeBoolean
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readBoolean(),
        parcel.readLong(),
        parcel.readFloat()
    )

    @RequiresApi(Build.VERSION_CODES.Q) // writeBoolean
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(someName)
        parcel.writeInt(someNumber)
        parcel.writeBoolean(someBoolean)
        parcel.writeLong(someLong)
        parcel.writeFloat(someFloat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataStoreProto> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): DataStoreProto {
            return DataStoreProto(parcel)
        }

        override fun newArray(size: Int): Array<DataStoreProto?> {
            return arrayOfNulls(size)
        }
    }
}