package allanksr.com.datastore_example.data

import allanksr.com.datastore_example.domain.DataStoreRepository
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImp(
    private var dataStore: DataStore<Preferences>
): DataStoreRepository {

    override suspend fun <T> storeValue(preferencesKey: Preferences.Key<T>, value: T):String {
        return try {
            val ds = dataStore.edit {
                it[preferencesKey] = value
            }
            "value stored successfully ${(ds[preferencesKey]!=null)}"
        }catch (e: Exception){
            "value stored failure ${e.message}"
        }
    }

    override suspend fun getString(preferencesKey: Preferences.Key<String>): String {
        return dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: ""
        }.first()
    }

    override suspend fun getInt(preferencesKey: Preferences.Key<Int>): Int {
        return dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: 0
        }.first()
    }

    override suspend fun getBoolean(preferencesKey: Preferences.Key<Boolean>): Boolean {
        return dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: false
        }.first()
    }

    override suspend fun getLong(preferencesKey: Preferences.Key<Long>): Long {
        return dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: 0L
        }.first()
    }

    override suspend fun getFloat(preferencesKey: Preferences.Key<Float>): Float {
        return dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: 0f
        }.first()
    }

    override suspend fun getDouble(preferencesKey: Preferences.Key<Double>): Double {
        return dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: 0.0
        }.first()
    }

}