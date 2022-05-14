@file:Suppress("BlockingMethodInNonBlockingContext")

package allanksr.com.datastore_example.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import allanksr.com.datastore_example.DatastoreExample
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<DatastoreExample> {
    override val defaultValue: DatastoreExample = DatastoreExample.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): DatastoreExample {
        try {
            return DatastoreExample.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: DatastoreExample,
        output: OutputStream) = t.writeTo(output)
}
