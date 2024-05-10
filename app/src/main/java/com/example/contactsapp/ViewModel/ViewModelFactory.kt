package com.example.contactsapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.Repository.Contact_repo

class ViewModelFactory(private val repository: Contact_repo)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Conatct_ViewModel::class.java)) {
            return Conatct_ViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}