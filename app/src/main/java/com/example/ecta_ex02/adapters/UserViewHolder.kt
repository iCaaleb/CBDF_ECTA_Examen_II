package com.example.ecta_ex02.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecta_ex02.R
import com.example.ecta_ex02.entities.UserEntity

class UserViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val tvNombreUsuario = view.findViewById<TextView>(R.id.tvNombreUsuario)
    val tvUserName = view.findViewById<TextView>(R.id.tvUsername)
    val tvEmail = view.findViewById<TextView>(R.id.tvEmail)

    fun render(userModel: UserEntity) {
        tvNombreUsuario.text =userModel.name
        tvUserName.text = userModel.username
        tvEmail.text = userModel.email
    }
}