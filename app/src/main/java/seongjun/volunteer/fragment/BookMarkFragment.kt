package seongjun.volunteer.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import seongjun.volunteer.activity.DetailActivity
import seongjun.volunteer.activity.MainActivity
import seongjun.volunteer.adapter.BookMarkAdapter
import seongjun.volunteer.databinding.FragmentBookMarkBinding
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.viewmodel.MainViewModel

class BookMarkFragment : Fragment() {

    private val binding by lazy { FragmentBookMarkBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var mainActivity: MainActivity
    private lateinit var volunteerAdapter: BookMarkAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObserver()
    }

    private fun setView() {
        volunteerAdapter = BookMarkAdapter().apply {
            setOnItemClickListener(object: BookMarkAdapter.OnItemClickListener{
                override fun onItemClick(v: View, item: BookMarkData, isBookMark: Boolean) { // 화면 이동
                    viewModel.isVolunteerExist(item.programId, item.url, isBookMark, true)
                }
            })
        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(mainActivity)
            adapter = volunteerAdapter
        }
    }

    private fun setObserver() {
        viewModel.bookMarkList.observe(viewLifecycleOwner, {
            if (viewModel.bookMarkList.value!!.isNotEmpty()) {
                binding.container.visibility = View.VISIBLE
                binding.noResult.visibility = View.GONE
            } else {
                binding.container.visibility = View.GONE
                binding.noResult.visibility = View.VISIBLE
            }
            volunteerAdapter.setData(viewModel.bookMarkList.value!!)
        })

        viewModel.isVolunteerExist.observe(viewLifecycleOwner, {
            if (viewModel.isVolunteerExist.value!!) {
                viewModel.isVolunteerExist.value = false
                startActivity(Intent(mainActivity, DetailActivity::class.java).apply {
                    putExtra("programId", viewModel.programId)
                    putExtra("url", viewModel.url)
                    putExtra("isBookMark", viewModel.isBookMark)
                    putExtra("fromBookMark", viewModel.fromBookMark)
                })
            }
        })
    }
}