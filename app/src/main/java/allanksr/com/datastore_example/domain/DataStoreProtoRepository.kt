package allanksr.com.datastore_example.domain

import allanksr.com.datastore_example.domain.model.DataStoreProto
import kotlinx.coroutines.flow.Flow

interface DataStoreProtoRepository {

    suspend fun setValue(dataStoreProtoModel: DataStoreProto): String

    suspend fun getValue(): Flow<DataStoreProto>

}