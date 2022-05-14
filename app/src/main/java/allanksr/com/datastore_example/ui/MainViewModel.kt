package allanksr.com.datastore_example.ui

import allanksr.com.datastore_example.domain.DataStoreProtoRepository
import allanksr.com.datastore_example.domain.DataStoreRepository
import allanksr.com.datastore_example.domain.model.DataStoreProto
import androidx.datastore.preferences.core.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStoreRepository,
    private val dataProto: DataStoreProtoRepository,
) : ViewModel() {

    fun <P> storedData(preferencesKey: Preferences.Key<P>, value: P): Flow<String> {
        return flow {
            //simulate delay
            delay(3000)
            emit(
                dataStore.storeValue(
                    preferencesKey = preferencesKey,
                    value = value
                )
            )
        }
    }
    fun getString(preferencesKey: Preferences.Key<String>): Flow<String> {
        return flow {
            emit(dataStore.getString(preferencesKey))
        }
    }

    fun getLong(preferencesKey: Preferences.Key<Long>): Flow<Long> {
        return flow {
            emit(dataStore.getLong(preferencesKey))
        }
    }

    fun getBoolean(preferencesKey: Preferences.Key<Boolean>): Flow<Boolean> {
        return flow {
            emit(dataStore.getBoolean(preferencesKey))
        }
    }

    fun getInt(preferencesKey: Preferences.Key<Int>): Flow<Int> {
        return flow {
            emit(dataStore.getInt(preferencesKey))
        }
    }

    fun getDouble(preferencesKey: Preferences.Key<Double>): Flow<Double> {
        return flow {
            emit(dataStore.getDouble(preferencesKey))
        }
    }


    fun setProtoValue(dataStoreProtoModel: DataStoreProto): Flow<String>{
        return flow {
            delay(3000)
            emit(dataProto.setValue(dataStoreProtoModel))
        }
    }

    fun getProtoValue(): Flow<DataStoreProto>{
        return flow {
            emit(dataProto.getValue().first())
        }
    }

}