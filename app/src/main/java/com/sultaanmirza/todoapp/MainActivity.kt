package com.sultaanmirza.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sultaanmirza.todoapp.db.MyDBHelper
import com.sultaanmirza.todoapp.db.TodoTable

class MainActivity : AppCompatActivity() {
    lateinit var etTodo: EditText
    lateinit var btnAdd: Button
    lateinit var rvList: RecyclerView
    var todos = ArrayList<Todo>()
    var NUMBER = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etTodo = findViewById(R.id.etTodo)
        btnAdd = findViewById(R.id.btnAdd)
        rvList = findViewById(R.id.rvList)

        rvList.layoutManager = LinearLayoutManager(this)
        val myAdapter = MyAdapter(todos)
        rvList.adapter = myAdapter

        val db = MyDBHelper(this).writableDatabase

        fun refreshTodoList(){
            val todoList = TodoTable.getAllTodos(db)
            todos.clear()
            todos.addAll(todoList)
            myAdapter.notifyDataSetChanged()
        }
        refreshTodoList()

        btnAdd.setOnClickListener {
            val todo = Todo(etTodo.text.toString(),false)
            TodoTable.insertTodo(db,todo)
            etTodo.text.clear()
            refreshTodoList()
        }
    }
}