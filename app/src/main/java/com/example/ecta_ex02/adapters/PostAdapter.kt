package com.example.ecta_ex02.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecta_ex02.R
import com.example.ecta_ex02.entities.PostEntity
import com.example.ecta_ex02.entities.UserEntity

class PostAdapter(private var postsList: List<PostEntity>): RecyclerView.Adapter<PostAdapter.PostsViewHolder>() {

    var onItemClick: ((PostEntity) -> Unit)? = null

    inner class PostsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitulo = itemView.findViewById<TextView>(R.id.tvTituloPost)
        val tvBody = itemView.findViewById<TextView>(R.id.tvBodyPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostsViewHolder(view )
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val posts = postsList[position]
        holder.tvTitulo.text = posts.title
        holder.tvBody.text = posts.body

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(posts)
        }
    }

    fun actualizarLista(listaPosts: List<PostEntity>) {
        postsList = listaPosts
        notifyDataSetChanged()
    }
}