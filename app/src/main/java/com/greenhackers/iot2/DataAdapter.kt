package com.greenhackers.iot2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.Exception

class DataAdapter(val list: ArrayList<Response>,val onclick:(vh:ViewHolder,position:Int)->Unit):RecyclerView.Adapter<DataAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount()= list.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val r = list[position]
        if (r.temperature.`$numberDouble`.toString()<="30") {
            holder.itemView.ivMain.setImageResource(R.drawable.cold)
        }else if (r.temperature.`$numberDouble`.toString()>"30" && r.temperature.`$numberDouble`.toString()<"40"){
            holder.itemView.ivMain.setImageResource(R.drawable.cold30)
        }else holder.itemView.ivMain.setImageResource(R.drawable.cold40)
        try {
            holder.title.text = "Temperature ${r.temperature.`$numberDouble`.toString().substring(0,7)}"
        }catch (e:Exception){}

        holder.des.text = "Humidity ${r.humidity.`$numberDouble`}"
        //holder.time.text = r.time.toString()
        holder.ibtDelete.setOnClickListener{onclick(holder,position)}
    }

    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {
        val title =v.tvTitle
        val des = v.tvDesc
        val time = v.tvTime
        val ibtDelete = v.ibtDelete
    }

}