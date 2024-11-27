package com.example.ecta_ex02

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecta_ex02.PostsActivity.Companion.postAdapter
import com.example.ecta_ex02.adapters.CommentsAdapter
import com.example.ecta_ex02.entities.PostEntity
import com.example.ecta_ex02.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComentariosActivity : AppCompatActivity() {

    private val userRepository = UserRepository()

    companion object{
        val commentsAdapter: CommentsAdapter = CommentsAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comentarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val post = intent.getParcelableExtra<PostEntity>("post")

        if (post!=null) {
            obtenerComentarios(post.id)
        }

        initRecyclerView()
    }

    private fun obtenerComentarios(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val comentarios = userRepository.getCommentsByPostId(id)
                withContext(Dispatchers.Main) {
                    commentsAdapter.actualizarLista(comentarios)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ComentariosActivity, "Error al conectar al item", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyuclerViewComentarios)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = commentsAdapter
    }
}