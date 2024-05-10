package com.example.contactsapp.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

//Each instance of this class represents a row in the table
@Entity(tableName = "Contacts_Table") //@entity shows that the name of the table, if not mentioned it will take the name of the class e.g. Contact_details
data class Contact_details(
    @PrimaryKey(autoGenerate = true) //autogenerate allows the system to provide a unique id automatically
    val contact_no : Int,
    var contact_name: String,
    var contact_email: String
)
