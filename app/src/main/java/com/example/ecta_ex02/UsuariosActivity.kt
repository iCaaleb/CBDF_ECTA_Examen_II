package com.example.ecta_ex02

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecta_ex02.adapters.UserAdapter
import com.example.ecta_ex02.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuariosActivity : AppCompatActivity() {

    private val userRepository = UserRepository()

    companion object {
        val userAdapter: UserAdapter = UserAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_usuarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getUsers()
        initRecyclerView()

        userAdapter.onItemClick = {
            val intent = Intent(this, PostsActivity::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
        }
    }

    private fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val users = userRepository.getAllUsers()
                withContext(Dispatchers.Main) {
                    userAdapter.actualizarUsuarios(users)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UsuariosActivity, "Error al conectar al item", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewUsuarios)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter
    }
}