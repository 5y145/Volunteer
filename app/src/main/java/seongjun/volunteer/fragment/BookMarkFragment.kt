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
import androidx.recyclerview.widget.RecyclerView
import seongjun.volunteer.ApplicationClass
import seongjun.volunteer.R
import seongjun.volunteer.activity.DetailActivity
import seongjun.volunteer.activity.MainActivity
import seongjun.volunteer.adapter.BookMarkAdapter
import seongjun.volunteer.adapter.MainAdapter
import seongjun.volunteer.databinding.FragmentBookMarkBinding
import seongjun.volunteer.databinding.FragmentHomeBinding
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerData
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
                override fun onItemClick(v: View, item: BookMarkData) { // 화면 이동
                    startActivity(Intent(mainActivity, DetailActivity::class.java).apply {
                        putExtra("programId", item.programId)
                        putExtra("url", item.url)
                    })
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
            volunteerAdapter.setData(viewModel.bookMarkList.value!!)
        })
    }
}