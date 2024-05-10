package com.example.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.Repository.Contact_repo
import com.example.contactsapp.Room.ContactDatabase
import com.example.contactsapp.Room.Contact_details
import com.example.contactsapp.View.MyRecyclerView
import com.example.contactsapp.ViewModel.Conatct_ViewModel
import com.example.contactsapp.ViewModel.ViewModelFactory
import com.example.contactsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var conatctViewmodel: Conatct_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //ROOM Database
        val dao = ContactDatabase.getInstance(applicationContext).conatactDAO
        val repository = Contact_repo(dao)
        val factory = ViewModelFactory(repository)


        //View Model
        conatctViewmodel = ViewModelProvider(this, factory)
            .get(Conatct_ViewModel::class.java)

        binding.conatctViewModel = conatctViewmodel
         //use this:: data binding integration and LiveData

        binding.lifecycleOwner = this
        initRecyclerView()


    }

    private fun  initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        conatctViewmodel.contacts.observe(this, {
            binding.recyclerView.adapter = MyRecyclerView(
                it, {
                    selectedItem: Contact_details->listItemClicked(selectedItem)

                }
            )

        })
    }
    private fun listItemClicked(selectedItem: Contact_details){
        Toast.makeText(this, "Selected item is ${selectedItem.contact_name}", Toast.LENGTH_LONG).show()

        conatctViewmodel.initDeleteAndUpdate(selectedItem)
    }
}