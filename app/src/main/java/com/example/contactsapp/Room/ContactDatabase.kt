package com.example.contactsapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact_details::class], version = 1)
 abstract class ContactDatabase: RoomDatabase() {
    abstract val conatactDAO: DAO

// companion object used to create singleton database, singleton database ensures integrity of data and does not allow duplication
     companion object{
         @Volatile
         private var INSTANCE: ContactDatabase?= null

         fun getInstance(context: Context) : ContactDatabase {
             synchronized(this){
                var instance = INSTANCE //Executes the given function block while holding the monitor of the given object lock.
                 if (instance == null){
                     //Creating database instance
                     instance = Room.databaseBuilder(  //A RoomDatabaseBuilder<T> which you can use to create the database.
                         context.applicationContext,
                         ContactDatabase ::class.java,
                         "contact_db"
                     ).build() //Creates the databases and initializes it. By default, all RoomDatabases use in memory storage for TEMP tables and enables recursive triggers.
                 }
                     INSTANCE = instance
                     return instance

             }
         }
     }
}