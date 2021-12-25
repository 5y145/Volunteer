package seongjun.volunteer.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import seongjun.volunteer.MainActivity
import seongjun.volunteer.MainViewModel
import seongjun.volunteer.adapter.MainAdapter
import seongjun.volunteer.databinding.FragmentHomeBinding
import seongjun.volunteer.model.MainData


class HomeFragment : Fragment() {


    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var mainActivity: MainActivity
    private lateinit var mainAdapter: MainAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainAdapter = MainAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
            setOnItemClickListener(object: MainAdapter.OnItemClickListener{ // 이벤트 리스너
                override fun onItemClick(v: View, item: MainData) {
                    // 화면 이동
                }
//                override fun onItemLongClick(v: View, item: MainData) {}
            })
        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(mainActivity)
            adapter = mainAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.mainList.observe(viewLifecycleOwner, {
            mainAdapter.setData(viewModel.mainList.value!!)
        })
    }
}