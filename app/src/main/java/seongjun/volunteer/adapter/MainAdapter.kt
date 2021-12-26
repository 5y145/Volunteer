package seongjun.volunteer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import seongjun.volunteer.ApplicationClass
import seongjun.volunteer.DetailActivity
import seongjun.volunteer.databinding.ItemVolunteerBinding
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerData

class MainAdapter: RecyclerView.Adapter<MainAdapter.Holder>() {

    private var list: MutableList<VolunteerData> = ArrayList()
//    private var bookMarkList: List<BookMarkData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemVolunteerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            listener?.onItemClick(holder.itemView, list[position])
        }

//        holder.itemView.setOnLongClickListener {
//            listener?.onItemLongClick(holder.itemView, list[position])
//            return@setOnLongClickListener false
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: MutableList<VolunteerData>) {
        list = newList
        notifyDataSetChanged()
    }

    // ClickListener
    interface OnItemClickListener {
        fun onItemClick(v: View, item: VolunteerData)
//        fun onItemLongClick(v: View, item: MainData)
    }

    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) { this.listener = listener }

    inner class Holder(val binding: ItemVolunteerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(volunteerData: VolunteerData) {
            binding.tvHost.text = volunteerData.nanmmbyNm
            when(volunteerData.progrmSttusSe) {
                1 -> binding.tvState.text = "모집대기"
                2 -> binding.tvState.text = "모집중"
                3 -> binding.tvState.text = "모집완료"
            }
            binding.tvDate.text = getDate(volunteerData.progrmBgnde, volunteerData.progrmEndde)
            binding.tvTitle.text = volunteerData.progrmSj
//            if(bookMarkList.any { it.program_id == volunteerData.progrmRegistNo }) binding.ibBookMark.visibility = View.VISIBLE
//            else binding.ibBookMark.visibility = View.INVISIBLE
        }

        private fun getDate(startDate: Int, endDate: Int): String {
            return "${startDate % 10000 / 100}/${startDate % 100} ~ ${endDate % 10000 / 100}/${endDate % 100}"
        }
    }
}