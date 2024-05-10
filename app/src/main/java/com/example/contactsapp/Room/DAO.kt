package com.example.contactsapp.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Defines the methods to interact with the database
@Dao
interface DAO {
    @Insert
    suspend fun insertContact(contact: Contact_details): Long

    @Update
    suspend fun updateContact(contact: Contact_details)

    @Delete
    suspend fun deleteContact(contact: Contact_details)

    @Query("Delete from Contacts_Table")
    suspend fun deleteAll()

    @Query("select * from Contacts_Table")
    fun getAllContacts():LiveData<List<Contact_details>>


}