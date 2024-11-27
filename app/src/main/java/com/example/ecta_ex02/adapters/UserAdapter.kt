package com.example.ecta_ex02.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecta_ex02.R
import com.example.ecta_ex02.entities.User
import com.example.ecta_ex02.entities.UserEntity

class UserAdapter(private var usersList: List<UserEntity>): RecyclerView.Adapter<UserViewHolder>() {

    var onItemClick: ((UserEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(layoutInflater.inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = usersList[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    fun actualizarUsuarios(lisaUsuarios: List<UserEntity>) {
        usersList = lisaUsuarios
        notifyDataSetChanged()
    }
}