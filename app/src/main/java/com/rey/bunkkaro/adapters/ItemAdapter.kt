package com.rey.bunkkaro.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.rey.bunkkaro.database.Item
import com.rey.bunkkaro.ItemActivity
import com.rey.bunkkaro.database.ItemDatabaseHelper
import com.rey.bunkkaro.R
import com.rey.bunkkaro.databinding.ItemLayoutBinding
import kotlin.math.roundToInt

class ItemAdapter(private val context:Context):RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    private var itemDatabaseHelper = ItemDatabaseHelper(context)
    class ViewHolder(itemView: ItemLayoutBinding) :RecyclerView.ViewHolder(itemView.root){
        val item:MaterialCardView = itemView.itemView
        val title:TextView = itemView.title
        val percentage:TextView =itemView.percentage
        val cancelButton:FloatingActionButton = itemView.buttonCancel
        val markButton:FloatingActionButton = itemView.buttonMark
    }
    lateinit var items:ArrayList<Item>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = items[position].title
        holder.percentage.text = context.getString(R.string.percent_attendance,items[position].attendance.roundToInt())
        holder.markButton.setOnClickListener {
            itemDatabaseHelper.markAttendance(items[position].id,true)
            items = itemDatabaseHelper.getArrayList()
            Snackbar.make(holder.item,"${items[position].title} Marked as Attended",Snackbar.LENGTH_SHORT).show()
            notifyItemChanged(position)
        }
        holder.cancelButton.setOnClickListener {
            itemDatabaseHelper.markAttendance(items[position].id,false)
            items = itemDatabaseHelper.getArrayList()
            Snackbar.make(holder.item,"${items[position].title} Marked as Missed",Snackbar.LENGTH_SHORT).show()
            notifyItemChanged(position)
        }
        holder.item.setOnClickListener {
            val itemIntent = Intent(context, ItemActivity::class.java)
            itemIntent.putExtra("id",items[position].id)
            context.startActivity(itemIntent)
        }
    }
}