package com.example.ecta_ex02

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecta_ex02.UsuariosActivity.Companion.userAdapter
import com.example.ecta_ex02.adapters.PostAdapter
import com.example.ecta_ex02.adapters.UserAdapter
import com.example.ecta_ex02.entities.UserEntity
import com.example.ecta_ex02.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsActivity : AppCompatActivity() {

    private val userRepository = UserRepository()

    companion object {
        val postAdapter: PostAdapter = PostAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_posts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = intent.getParcelableExtra<UserEntity>("user")


        if (user != null) {
            getPostsById(user.id)
            val tvUsername = findViewById<TextView>(R.id.tvUsername_posts)
            tvUsername.text = "Posts de " + user.username
        }

        initRecyclerView()

        postAdapter.onItemClick = {
            val intent = Intent(this, ComentariosActivity::class.java)
            intent.putExtra("post", it)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPosts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postAdapter
    }

    private fun getPostsById(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val posts = userRepository.getPostsByUserId(id)
                withContext(Dispatchers.Main) {
                    postAdapter.actualizarLista(posts)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@PostsActivity, "Error al conectar al item", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}