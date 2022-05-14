package allanksr.com.datastore_example.di

import allanksr.com.datastore_example.DatastoreExample
import allanksr.com.datastore_example.common.Constants.dataProtoName
import allanksr.com.datastore_example.common.Constants.dataStorageName
import allanksr.com.datastore_example.data.DataProtoRepositoryImp
import allanksr.com.datastore_example.data.DataStoreRepositoryImp
import allanksr.com.datastore_example.data.SettingsSerializer
import allanksr.com.datastore_example.domain.DataStoreProtoRepository
import allanksr.com.datastore_example.domain.DataStoreRepository
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(
                    context, dataStorageName
                )
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {
                context.preferencesDataStoreFile(name = dataStorageName)
            }
        )
    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreRepositoryImp(dataStore)
    }


    @Provides
    @Singleton
    fun provideProtoStore(@ApplicationContext context: Context): DataStore<DatastoreExample> =
        DataStoreFactory.create(
            serializer = SettingsSerializer,
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {
                context.dataStoreFile(dataProtoName)
            }
        )
    @Provides
    @Singleton
    fun provideProtoStoreRepository(dataStore: DataStore<DatastoreExample>): DataStoreProtoRepository {
        return DataProtoRepositoryImp(dataStore)
    }

}