package seongjun.volunteer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import seongjun.volunteer.databinding.ItemVolunteerBinding
import seongjun.volunteer.model.BookMarkData


class BookMarkAdapter: RecyclerView.Adapter<BookMarkAdapter.Holder>() {

    private var list: List<BookMarkData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemVolunteerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener?.onItemClick(holder.itemView, list[position], true)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<BookMarkData>) {
        list = newList
        notifyDataSetChanged()
    }

    fun isBookMark(programId: String): Boolean {
        var result = false
        for (item in list) {
            if (item.programId == programId) {
                result = true
                break
            }
        }
        return result
    }

    // ClickListener
    interface OnItemClickListener {
        fun onItemClick(v: View, item: BookMarkData, isBookMark: Boolean)
    }

    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) { this.listener = listener }

    inner class Holder(private val binding: ItemVolunteerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookMarkData) {
            binding.tvHost.text = item.host
            when(item.state) {
                1 -> binding.tvState.text = "모집대기"
                2 -> binding.tvState.text = "모집중"
                3 -> binding.tvState.text = "모집완료"
            }
            binding.tvDay.text = getDate(item.startDay, item.endDay)
            binding.tvTitle.text = item.title
            binding.ibBookMark.visibility = View.INVISIBLE
//            if (isBookMark(item.programId)) binding.ibBookMark.visibility = View.VISIBLE
//            else binding.ibBookMark.visibility = View.INVISIBLE
        }

        private fun getDate(startDate: Int, endDate: Int): String {
            return "${startDate % 10000 / 100}/${startDate % 100} ~ ${endDate % 10000 / 100}/${endDate % 100}"
        }
    }
}