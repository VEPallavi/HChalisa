package com.ve.hchalisa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ve.hchalisa.R
import com.ve.hchalisa.modal.HanumanChalisaContentModel
import java.util.ArrayList


class HanumanChalisaAdapter(var mContext: Context, var arrayListChalistContent: ArrayList<HanumanChalisaContentModel>): RecyclerView.Adapter<HanumanChalisaAdapter.ChalisaViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HanumanChalisaAdapter.ChalisaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_list_chalisa_aarti, parent, false)
        return ChalisaViewHolder(view)
    }

    override fun onBindViewHolder(holder: HanumanChalisaAdapter.ChalisaViewHolder, position: Int) {
        var dataList = arrayListChalistContent.get(position)


        holder.tv_aarti_chalisa.setText(dataList.content)


    }

    override fun getItemCount(): Int {
        return arrayListChalistContent.size
    }



    inner class ChalisaViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_aarti_chalisa: TextView

        init {
            tv_aarti_chalisa = view.findViewById(R.id.tv_aarti_chalisa)
        }

    }


}