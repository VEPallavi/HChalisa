package com.ve.hchalisa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ve.hchalisa.R


class HanumanArtiAdapter(var mContext: Context): RecyclerView.Adapter<HanumanArtiAdapter.ArtiViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HanumanArtiAdapter.ArtiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_list_chalisa_aarti, parent, false)
        return ArtiViewHolder(view)
    }

    override fun onBindViewHolder(holder: HanumanArtiAdapter.ArtiViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ArtiViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_aarti_chalisa: TextView

        init {
            tv_aarti_chalisa = view.findViewById(R.id.tv_aarti_chalisa)
        }
    }
}