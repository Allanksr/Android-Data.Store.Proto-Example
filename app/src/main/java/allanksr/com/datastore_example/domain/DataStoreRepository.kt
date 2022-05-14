package allanksr.com.datastore_example.domain

import androidx.datastore.preferences.core.Preferences

interface DataStoreRepository {

    suspend fun<T> storeValue(preferencesKey: Preferences.Key<T>, value: T): String

    suspend fun getString(preferencesKey: Preferences.Key<String>): String

    suspend fun getInt(preferencesKey: Preferences.Key<Int>): Int

    suspend fun getBoolean(preferencesKey: Preferences.Key<Boolean>): Boolean

    suspend fun getLong(preferencesKey: Preferences.Key<Long>): Long

    suspend fun getFloat(preferencesKey: Preferences.Key<Float>): Float

    suspend fun getDouble(preferencesKey: Preferences.Key<Double>): Double

}