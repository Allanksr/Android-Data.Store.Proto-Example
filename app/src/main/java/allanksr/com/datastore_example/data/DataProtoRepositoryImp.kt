package allanksr.com.datastore_example.data

import allanksr.com.datastore_example.DatastoreExample
import allanksr.com.datastore_example.domain.DataStoreProtoRepository
import allanksr.com.datastore_example.domain.model.DataStoreProto
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataProtoRepositoryImp(
    private var dataProto: DataStore<DatastoreExample>
): DataStoreProtoRepository {

    override suspend fun setValue(dataStoreProtoModel: DataStoreProto): String {
        return try {
            val dp =  dataProto.updateData {
                it.toBuilder()
                    .setSomeName(dataStoreProtoModel.someName)
                    .setSomeNumber(dataStoreProtoModel.someNumber)
                    .setSomeBoolean(dataStoreProtoModel.someBoolean)
                    .setSomeLong(dataStoreProtoModel.someLong)
                    .setSomeFloat(dataStoreProtoModel.someFloat)
                    .build()
            }
            "value stored successfully ${(dp.someName!= null)}"
        }catch (e: Exception){
            "value stored failure ${e.message}"
        }
    }

    override suspend fun getValue(): Flow<DataStoreProto> {
        return dataProto.data.map {
            DataStoreProto(
                it.someName,
                it.someNumber,
                it.someBoolean,
                it.someLong,
                it.someFloat
            )
        }
    }

}