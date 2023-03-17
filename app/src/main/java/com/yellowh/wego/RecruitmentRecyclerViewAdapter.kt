package com.yellowh.wego

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yellowh.wego.databinding.ItemRecruitmentBinding
import java.text.SimpleDateFormat

class RecruitmentRecyclerViewAdapter(private val infoList: Array<Recruitment>?) :
    RecyclerView.Adapter<RecruitmentRecyclerViewAdapter.RecruitmentViewHolder>() {

    inner class RecruitmentViewHolder(binding: ItemRecruitmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tv_username = binding.username
        val tv_sanName = binding.mountainName
        val iv_img = binding.userUploadImg
        val tv_title = binding.title
        val tv_date = binding.date
        val tv_time = binding.time
        val tv_memberCount = binding.memberCount
        val tv_favoriteCnt = binding.favoriteCount
        val tv_createDate = binding.createDate
    } // InfoViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecruitmentViewHolder {
        val binding: ItemRecruitmentBinding =
            ItemRecruitmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecruitmentViewHolder(binding)
    } // onCreateViewHolder


    override fun onBindViewHolder(holder: RecruitmentViewHolder, position: Int) {
        val data = infoList?.get(position)

        holder.tv_username.text = data?.nickName
        holder.tv_sanName.text = data?.sanName
//        holder.iv_img.setImageURI() // img 주소가 DB에 없음
        holder.tv_title.text = data?.title
        holder.tv_date.text = "날짜: " + data?.partyYMD
        holder.tv_time.text = "시간: " + data?.partyTime
        holder.tv_memberCount.text = "참여인원: " + data?.userCnt.toString() + " / " + data?.partyMax
        holder.tv_favoriteCnt.text = data?.likeCnt.toString()
        holder.tv_createDate.text = data?.createdDT

        println("DataFormat: " + data?.partyYMD)
        println("DataFormat: " + data?.partyTime)
    } // onBindViewHolder

    override fun getItemCount(): Int {
        return infoList?.size ?: 0
    } // getItemCount
}