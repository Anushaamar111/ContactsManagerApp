package com.example.contactsapp.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R
import com.example.contactsapp.Room.Contact_details
import com.example.contactsapp.databinding.CardItemBinding

class MyRecyclerView(private val contactsList: List<Contact_details>,
    private val clickListener: (Contact_details)->Unit ): RecyclerView.Adapter<MyRecyclerView.MyViewHolder>() {

        class MyViewHolder (val binding: CardItemBinding)
            : RecyclerView.ViewHolder(binding.root){
                fun bind(contact: Contact_details, clickListener: (Contact_details) -> Unit){

                    binding.nameTextView.text = contact.contact_name
                    binding.emailTextView.text = contact.contact_email

                    binding.listItemLayout.setOnClickListener {
                        clickListener(contact)
                    }
                }
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerView.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.card_item,
            parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRecyclerView.MyViewHolder, position: Int) {
       holder.bind(contactsList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

}