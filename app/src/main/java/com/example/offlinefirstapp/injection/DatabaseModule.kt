package com.example.offlinefirstapp.injection

import android.content.Context
import androidx.room.Room
import com.example.offlinefirstapp.dao.TodoItemDAO
import com.example.offlinefirstapp.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton

    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase =
        Room.databaseBuilder(
            context,
            klass = TodoDatabase::class.java,
            name = "todo_database"
        ).build()


    @Provides
    fun provideDAO(db: TodoDatabase) : TodoItemDAO = db.todoItemDao()
}