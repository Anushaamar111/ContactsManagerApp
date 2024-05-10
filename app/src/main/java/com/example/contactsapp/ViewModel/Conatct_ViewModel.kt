package com.example.contactsapp.ViewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Update
import com.example.contactsapp.Repository.Contact_repo
import com.example.contactsapp.Room.Contact_details
import kotlinx.coroutines.launch

class Conatct_ViewModel(private val repository: Contact_repo) : ViewModel(), Observable {
      val contacts = repository.contacts
      private var isDeleteorUpdate = false
      private lateinit var ContactToUpdateorDelete : Contact_details

      @Bindable
      val inputName = MutableLiveData<String?>()

      @Bindable
      val inputEmail = MutableLiveData<String?>()

      @Bindable
      val saveOrupdateButton = MutableLiveData<String?>()

      @Bindable
      val clearAllorDeleteButton = MutableLiveData<String?>()

      init {
          saveOrupdateButton.value = "Save"
            clearAllorDeleteButton.value = "Clear All"
      }

      fun insert(contactDetails: Contact_details)= viewModelScope.launch{
                  repository.insert(contactDetails)
      }

      fun delete(contactDetails: Contact_details)= viewModelScope.launch {
            repository.delete(contactDetails)

            inputName.value = null
            inputEmail.value = null
            isDeleteorUpdate = false
            saveOrupdateButton.value = "save"
            clearAllorDeleteButton.value = "Clear ALl"
      }

      fun Update(contactDetails: Contact_details)= viewModelScope.launch {
            repository.update(contactDetails)

            inputName.value = null
            inputEmail.value = null
            isDeleteorUpdate = false
            saveOrupdateButton.value = "save"
            clearAllorDeleteButton.value = "Clear ALl"
      }

      fun clearAll()= viewModelScope.launch {
            repository.deleteAll()
      }

      fun SaveOrUpdate() {
            if (isDeleteorUpdate) {
                  //make an update
                  ContactToUpdateorDelete.contact_name = inputName.value!!
                  ContactToUpdateorDelete.contact_email = inputEmail.value!!
                  Update(ContactToUpdateorDelete)
            }
            else{
                  val name = inputName.value!!
                  val email = inputEmail.value!!

                  insert(Contact_details(0, name, email))
                  //Reset the name and email
                  inputName.value = null
                  inputEmail.value = null
            }
      }
     fun clearAllorDelete(){
           if(isDeleteorUpdate){
                 delete(ContactToUpdateorDelete)
           }
           else{
                 clearAll()
           }
     }

      fun initDeleteAndUpdate(contactDetails: Contact_details){
            inputName.value = contactDetails.contact_name
            inputEmail.value = contactDetails.contact_email
            isDeleteorUpdate = true
            ContactToUpdateorDelete = contactDetails
            saveOrupdateButton.value = "Update"
            clearAllorDeleteButton.value = "Delete"
      }

      override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
            TODO("Not yet implemented")
      }

      override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
            TODO("Not yet implemented")
      }

}