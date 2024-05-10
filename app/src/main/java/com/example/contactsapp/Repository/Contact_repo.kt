package com.example.contactsapp.Repository

import com.example.contactsapp.Room.Contact_details
import com.example.contactsapp.Room.DAO

class Contact_repo(private val contactDAO: DAO){
    val contacts = contactDAO.getAllContacts()

    suspend fun insert(contact: Contact_details):Long {
        return contactDAO.insertContact(contact)
    }
    suspend fun delete(contact: Contact_details){
        return contactDAO.deleteContact(contact)
    }
    suspend fun update(contact: Contact_details){
        return contactDAO.updateContact(contact)
    }
    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }


}