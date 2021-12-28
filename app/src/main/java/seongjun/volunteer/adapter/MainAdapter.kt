package seongjun.volunteer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import seongjun.volunteer.databinding.ItemVolunteerBinding
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerData

class MainAdapter: RecyclerView.Adapter<MainAdapter.Holder>() {

    private var list: List<VolunteerData> = ArrayList()
    private var bookMarkList: List<BookMarkData> = ArrayList()

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
    fun setData(newList: List<VolunteerData>) {
        list = newList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBookMarkData(newBookMarkList: List<BookMarkData>) {
        bookMarkList = newBookMarkList
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
        fun bind(item: VolunteerData) {
            binding.tvPlace.text = item.place
            when(item.state) {
                1 -> binding.tvState.text = "모집대기"
                2 -> binding.tvState.text = "모집중"
                3 -> binding.tvState.text = "모집완료"
            }
            binding.tvDay.text = getDate(item.startDay, item.endDay)
            binding.tvTitle.text = item.title
            if(bookMarkList.any { it.programId == item.programId }) binding.ibBookMark.visibility = View.VISIBLE
            else binding.ibBookMark.visibility = View.INVISIBLE
        }

        private fun getDate(startDate: Int, endDate: Int): String {
            return "${startDate % 10000 / 100}/${startDate % 100} ~ ${endDate % 10000 / 100}/${endDate % 100}"
        }
    }
}