package seongjun.volunteer.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.core.widget.addTextChangedListener
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
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = viewLifecycleOwner

        mainAdapter = MainAdapter().apply {
            setOnItemClickListener(object: MainAdapter.OnItemClickListener{
                override fun onItemClick(v: View, item: VolunteerData) { // 화면 이동
                    startActivity(Intent(mainActivity, DetailActivity::class.java).apply { putExtra("volunteerData", item) })
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
                            viewModel.loadVolunteerList()
                        }
                }
            })
        }

        binding.snSido.apply {
            adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, LocationInfo.sidoName)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    viewModel.sidoCode.value = LocationInfo.sidoCode[position]

                    when(viewModel.sidoCode.value) {
                        "6110000" -> {
                            binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, LocationInfo.seoulName)
                            binding.snGugun.visibility = View.VISIBLE
                        }
                        "6410000" -> {
                            binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, LocationInfo.gyeonggiName)
                            binding.snGugun.visibility = View.VISIBLE
                        }
                        else -> {
                            binding.snGugun.visibility = View.GONE
                        }
                    }

                    viewModel.gugunCode.value = ""
                    viewModel.isSearching = false
                    viewModel.isEnd = false
                    viewModel.page = 1
                    viewModel.loadVolunteerList()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.snGugun.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                    when(viewModel.sidoCode.value) {
                        "6110000" -> viewModel.gugunCode.value = LocationInfo.seoulCode[position]
                        "6410000" -> viewModel.gugunCode.value = LocationInfo.gyeonggiCode[position]
                        else -> viewModel.gugunCode.value = ""
                    }

                    viewModel.isSearching = false
                    viewModel.isEnd = false
                    viewModel.page = 1
                    viewModel.loadVolunteerList()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.etSearch.addTextChangedListener {
            if (binding.etSearch.text.toString() != "") binding.ibCancel.visibility = View.VISIBLE
            else binding.ibCancel.visibility = View.GONE
        }

        binding.etSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (binding.etSearch.text.toString() != "") {
                        viewModel.isSearching = true
                        viewModel.searchText = binding.etSearch.text.toString()
                        viewModel.isEnd = false
                        viewModel.page = 1
                        viewModel.loadVolunteerList()

                        // 키보드 내리기
                        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
                    }
                    return true
                }
                return false
            }
        })

        binding.ibCancel.setOnClickListener {
            binding.etSearch.setText("")

            // 키보드 내리기
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)

            binding.rv.requestFocus()
        }
    }

    private fun setObserver() {
        viewModel.isComplete.observe(viewLifecycleOwner, {
            if (viewModel.isComplete.value!!) {
                viewModel.isComplete.value = false
                mainAdapter.setData(viewModel.volunteerList.value!!)
                if (viewModel.volunteerList.value!!.size > 0) binding.tvEmpty.visibility = View.GONE
                else binding.tvEmpty.visibility = View.VISIBLE
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.pb.visibility = if (viewModel.isLoading.value!!) View.VISIBLE else View.GONE
        })
    }
}