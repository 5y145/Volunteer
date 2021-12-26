package seongjun.volunteer.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import seongjun.volunteer.DetailActivity
import seongjun.volunteer.MainActivity
import seongjun.volunteer.adapter.MainAdapter
import seongjun.volunteer.databinding.FragmentHomeBinding
import seongjun.volunteer.model.LocationInfo
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.viewmodel.HomeViewModel
import android.widget.ArrayAdapter as ArrayAdapter1


class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel by activityViewModels<HomeViewModel>()

    private lateinit var mainActivity: MainActivity
    private lateinit var mainAdapter: MainAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObserver()
    }

    private fun setView() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        mainAdapter = MainAdapter().apply {
            setHasStableIds(true)
            setOnItemClickListener(object: MainAdapter.OnItemClickListener{
                override fun onItemClick(v: View, item: VolunteerData) { // 화면 이동
                    startActivity(Intent(mainActivity, DetailActivity::class.java).apply { putExtra("program_id", item.progrmRegistNo) })
                }
            })
        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(mainActivity)
            adapter = mainAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) { // 끝에 도달했는지 확인
                    super.onScrolled(recyclerView, dx, dy)
                        if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == mainAdapter.itemCount - 1){
//                            viewModel.getVolunteerList()
                        }
                }
            })
        }

        binding.snSido.apply {
            adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, LocationInfo.sidoName)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    viewModel.isSearching.value = false
                    viewModel.sidoCode.value = LocationInfo.sidoCode[position]

                    when(viewModel.sidoCode.value) {
                        "6110000" -> {
                            viewModel.isDetailSpinner.value = true
                            binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, LocationInfo.seoulName)
                        }
                        "6410000" -> {
                            viewModel.isDetailSpinner.value = true
                            binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, LocationInfo.gyeonggiName)
                        }
                        else -> {
                            viewModel.isDetailSpinner.value = false
                        }
                    }

                    viewModel.isEnd.value = false
                    viewModel.gugunCode.value = ""
                    viewModel.pageNum.value = 1
//                    viewModel.getVolunteerList()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.snGugun.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    viewModel.isSearching.value = false

                    when(viewModel.sidoCode.value) {
                        "6110000" -> viewModel.gugunCode.value = LocationInfo.seoulCode[position]
                        "6410000" -> viewModel.gugunCode.value = LocationInfo.gyeonggiCode[position]
                        else -> viewModel.gugunCode.value = ""
                    }

                    viewModel.isEnd.value = false
                    viewModel.pageNum.value = 1
//                    viewModel.getVolunteerList()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.etSearch.setOnEditorActionListener { textView, i, keyEvent ->
            val searchText = textView.text.toString()
            if (searchText.isEmpty()) {
                textView.clearFocus()
                textView.isFocusable = false
                textView.isFocusableInTouchMode = true
                return@setOnEditorActionListener true
            }

            viewModel.pageNum.value = 1
            viewModel.isSearching.value = true
            viewModel.searchText.value = searchText
//            viewModel.getVolunteerList()
            return@setOnEditorActionListener false
        }

        binding.ibCancel.setOnClickListener {
            binding.etSearch.setText("")
        }
    }

    private fun setObserver() {
        viewModel.isComplete.observe(viewLifecycleOwner, {
//            mainAdapter.setData(viewModel.volunteerList.value!!, viewModel.bookMarkList.value!!)
//            Log.d("@@@", "observe ${viewModel.volunteerList.value!!.size}")
            if (viewModel.isComplete.value!!) {
                Log.d("@@@", "1111111111")

                viewModel.isComplete.value = false
                if (viewModel.pageNum.value == 1) {
                    mainAdapter.setClear()
                }
                mainAdapter.setData(viewModel.volunteerList)
                viewModel.pageNum.value = viewModel.pageNum.value!! + 1
                Log.d("@@@", "222222222222")
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.pb.visibility = if (viewModel.isLoading.value!!) View.VISIBLE else View.GONE
        })
    }
}