package com.example.bai3


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var edtSearch: EditText
    private var studentList: List<Student> = listOf(
        Student("Nguyen Van A", "123456"),
        Student("Tran Thi B", "654321"),
        Student("Le Van C", "111222"),
        Student("Pham Van D", "333444"),
        Student("Hoang Thi E", "555666")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        edtSearch = findViewById(R.id.edtSearch)

        adapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                filter(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filter(query: String) {
        val filteredList = if (query.length > 2) {
            studentList.filter {
                it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
            }
        } else {
            studentList
        }
        adapter.updateList(filteredList)
    }
}
