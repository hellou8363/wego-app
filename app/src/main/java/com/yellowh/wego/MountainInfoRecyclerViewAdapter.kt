package com.yellowh.wego

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yellowh.wego.databinding.ItemInfoBinding

class MountainInfoRecyclerViewAdapter(private val infoList: Array<MountainInfo>?) :
    RecyclerView.Adapter<MountainInfoRecyclerViewAdapter.InfoViewHolder>() { // end class

    inner class InfoViewHolder(binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        val tv_sanName = binding.mountainName
        val tv_text = binding.mountainText
//        val iv_img = binding.mountainImg
        val tv_favoriteCnt = binding.favoriteCount
    } // InfoViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding: ItemInfoBinding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(binding)
    } // onCreateViewHolder



    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val data = infoList?.get(position)
        if (data != null) {
            holder.tv_sanName.text = data.sanName
        }
        if (data != null) {
            holder.tv_text.text = data.details
        }
//        holder.iv_img.background = data.img
        if (data != null) {
            holder.tv_favoriteCnt.text = data.likeCNT.toString()
        }

    } // onBindViewHolder

    override fun getItemCount(): Int {
        return infoList?.size ?: 0
    } // getItemCount
}