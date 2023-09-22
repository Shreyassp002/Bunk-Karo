package com.rey.bunkkaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rey.bunkkaro.adapters.HistoryItemAdapter
import com.rey.bunkkaro.database.ItemDatabaseHelper
import com.rey.bunkkaro.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHistoryBinding
    private lateinit var itemDatabaseHelper: ItemDatabaseHelper
    private lateinit var adapter: HistoryItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "History"

        itemDatabaseHelper = ItemDatabaseHelper(this)
        adapter = HistoryItemAdapter(this,itemDatabaseHelper.getAllHistory())
        binding.historyView.adapter = adapter
        binding.historyView.layoutManager = LinearLayoutManager(this)
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}