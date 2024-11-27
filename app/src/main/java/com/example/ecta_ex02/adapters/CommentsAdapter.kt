package com.example.ecta_ex02.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecta_ex02.R
import com.example.ecta_ex02.entities.CommentEntity
import com.example.ecta_ex02.entities.PostEntity
import com.example.ecta_ex02.entities.UserEntity

class CommentsAdapter(private var commentsList: List<CommentEntity>): RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    var onItemClick: ((CommentEntity) -> Unit)? = null

    inner class CommentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNombre = itemView.findViewById<TextView>(R.id.tvNameComment)
        val tvEmail = itemView.findViewById<TextView>(R.id.tvEmailComment)
        val tvBody = itemView.findViewById<TextView>(R.id.tvBodyComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = commentsList[position]
        holder.tvNombre.text = comment.name
        holder.tvEmail.text = comment.email
        holder.tvBody.text = comment.body

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(comment)
        }
    }

    fun actualizarLista(listaComentarios: List<CommentEntity>) {
        commentsList = listaComentarios
        notifyDataSetChanged()
    }
}