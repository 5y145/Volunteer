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
import seongjun.volunteer.activity.DetailActivity
import seongjun.volunteer.activity.MainActivity
import seongjun.volunteer.viewmodel.MainViewModel
import seongjun.volunteer.adapter.MainAdapter
import seongjun.volunteer.databinding.FragmentHomeBinding
import seongjun.volunteer.model.AreaData
import seongjun.volunteer.model.VolunteerData
import android.widget.ArrayAdapter as ArrayAdapter1


class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var mainActivity: MainActivity
    private lateinit var volunteerAdapter: MainAdapter

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
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = viewLifecycleOwner

        volunteerAdapter = MainAdapter().apply {
            setOnItemClickListener(object: MainAdapter.OnItemClickListener{
                override fun onItemClick(v: View, item: VolunteerData) { // 화면 이동
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
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) { // 끝에 도달했는지 확인
                    super.onScrolled(recyclerView, dx, dy)
                        if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == volunteerAdapter.itemCount - 1){
                            if (viewModel.isSearching) viewModel.getVolunteerListWithSearch()
                            else viewModel.getVolunteerListWithArea()
                        }
                }
            })
        }

        binding.snSido.apply {
            adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.sidoName)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) { clickSido(position) }
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.snGugun.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) { clickGugun(position) }
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

//        binding.etSearch.addTextChangedListener {
//            if (binding.etSearch.text.toString() != "") binding.ibCancel.visibility = View.VISIBLE else binding.ibCancel.visibility = View.GONE
//        }
//
//        binding.etSearch.setOnKeyListener(object : View.OnKeyListener {
//            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
//                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//                    viewModel.clickSearch(binding.etSearch.text.toString())
//                    hideKeyBoard()
//                    return true
//                }
//                return false
//            }
//        })
//
//        binding.ibCancel.setOnClickListener {
//            binding.etSearch.setText("")
//            hideKeyBoard()
//        }
    }

    private fun setObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (viewModel.isLoading.value!!) binding.pb.visibility = View.VISIBLE
            else binding.pb.visibility = View.GONE
        })

        viewModel.isComplete.observe(viewLifecycleOwner, {
            if (viewModel.isComplete.value!!) {
                volunteerAdapter.setData(viewModel.volunteerList.value!!)
                if (viewModel.volunteerList.value!!.size == 0) binding.llNoResult.visibility = View.VISIBLE
                else binding.llNoResult.visibility = View.GONE
                viewModel.isComplete.postValue(false)
            }
        })
    }

    private fun clickSido(position: Int) {
        viewModel.clickSido(AreaData.sidoCode[position])

        when(AreaData.sidoCode[position]) {
            "6110000" -> { // 서울
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.seoulName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6410000" -> { // 경기
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.gyeonggiName)
                binding.snGugun.visibility = View.VISIBLE
            }
            else -> { binding.snGugun.visibility = View.GONE }
        }
    }

    private fun clickGugun(position: Int) {
        when(viewModel.sidoCode) {
            "6110000" -> viewModel.clickGugun(AreaData.seoulCode[position])
            "6410000" -> viewModel.clickGugun(AreaData.gyeonggiCode[position])
            else -> viewModel.clickGugun("")
        }
    }

//    private fun hideKeyBoard() {
//        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
//        binding.rv.requestFocus()
//    }
}