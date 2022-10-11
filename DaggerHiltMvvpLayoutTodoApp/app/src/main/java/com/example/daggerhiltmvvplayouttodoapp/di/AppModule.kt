package com.example.daggerhiltmvvplayouttodoapp.di

import android.app.Application
import androidx.room.Room
import com.example.daggerhiltmvvplayouttodoapp.MyApp
import com.example.daggerhiltmvvplayouttodoapp.R
import com.example.daggerhiltmvvplayouttodoapp.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(context: Application): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(database: TodoDatabase) : TodoRepository {
        return TodoRepositoryImpl(database.dao)
    }

}