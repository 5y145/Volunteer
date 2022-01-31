package seongjun.volunteer.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import seongjun.volunteer.adapter.HomeAdapter
import seongjun.volunteer.databinding.FragmentHomeBinding
import seongjun.volunteer.dialog.SearchDialog
import seongjun.volunteer.model.AreaData
import seongjun.volunteer.model.VolunteerData
import android.widget.ArrayAdapter as ArrayAdapter1


class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var mainActivity: MainActivity
    private lateinit var volunteerAdapter: HomeAdapter

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
        volunteerAdapter = HomeAdapter().apply {
            setOnItemClickListener(object: HomeAdapter.OnItemClickListener{
                override fun onItemClick(v: View, item: VolunteerData, isBookMark: Boolean) { // 화면 이동
                    startActivity(Intent(mainActivity, DetailActivity::class.java).apply {
                        putExtra("programId", item.programId)
                        putExtra("url", item.url)
                        putExtra("isBookMark", isBookMark)
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
                        if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == volunteerAdapter.itemCount - 10){
                            if (volunteerAdapter.itemCount > 39) viewModel.getVolunteerList()
                        }
                }
            })
        }

        binding.snSido.apply {
            adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.sidoName)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    if (position >= 0) clickSido(position)
                }
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.snGugun.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    if (position >= 0) clickGugun(position)
                }
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.ibSearch.setOnClickListener {
            val dialog = SearchDialog(mainActivity)
            dialog.showDialog(mainActivity)
            dialog.setOnClickListener(object : SearchDialog.ButtonClickListener {
                override fun onClick(searchText: String, sidoCode: String, gugunCode: String, startDay: String, endDay: String) {
                    clickSearch(searchText, sidoCode, gugunCode, startDay, endDay)
                }
            })
        }

        binding.fabUp.setOnClickListener {
            binding.rv.smoothScrollToPosition(0)
        }

        binding.fabDown.setOnClickListener {
            binding.rv.smoothScrollToPosition(volunteerAdapter.itemCount - 1)
        }
    }

    private fun setObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (viewModel.isLoading.value!!) binding.pb.visibility = View.VISIBLE
            else binding.pb.visibility = View.GONE
        })

        viewModel.isComplete.observe(viewLifecycleOwner, {
            if (viewModel.isComplete.value!!) {
                if (viewModel.volunteerList.value!!.size > 0) {
                    binding.container.visibility = View.VISIBLE
                    binding.noResult.visibility = View.GONE
                } else {
                    binding.container.visibility = View.GONE
                    binding.noResult.visibility = View.VISIBLE
                }
                volunteerAdapter.setData(viewModel.volunteerList.value!!)
                viewModel.isComplete.value = false
            }
        })

        viewModel.volunteerList.observe(viewLifecycleOwner, {
            if (viewModel.volunteerList.value!!.size > 40) {
                binding.fabUp.visibility = View.VISIBLE
                binding.fabDown.visibility = View.VISIBLE
            } else {
                binding.fabUp.visibility = View.GONE
                binding.fabDown.visibility = View.GONE
            }
        })

        viewModel.bookMarkList.observe(viewLifecycleOwner, {
            volunteerAdapter.setBookMarkData(viewModel.bookMarkList.value!!)
        })
    }

    private fun clickSido(position: Int) {
        binding.container.visibility = View.GONE
        when(AreaData.sidoCode[position]) {
            "6110000" -> { // 서울
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.seoulName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6410000" -> { // 경기
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.gyeonggiName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6260000" -> { // 부산
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.busanName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6270000" -> { // 대구
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.daeguName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6280000" -> { // 인천
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.incheonName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6290000" -> { // 광주
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.gangwondoName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6300000" -> { // 대전
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.daejeonName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6310000" -> { // 울산
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.ulsanName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6420000" -> { // 강원도
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.gangwondoName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6430000" -> { // 충청북도
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.chungcheongbukdoName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6440000" -> { // 충청남도
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.chungcheongnamdoName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6450000" -> { // 전라북도
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.jeonlabugdoName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6460000" -> { // 전라남도
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.jeonlanamdoName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6470000" -> { // 경상북도
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.gyeongsangbugdoName)
                binding.snGugun.visibility = View.VISIBLE
            }
            "6480000" -> { // 경상남도
                binding.snGugun.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.gyeongsangnamdoName)
                binding.snGugun.visibility = View.VISIBLE
            }
            else -> { binding.snGugun.visibility = View.GONE }
        }
        viewModel.clickSido(AreaData.sidoCode[position])
    }

    private fun clickGugun(position: Int) {
        binding.container.visibility = View.GONE
        when(viewModel.sidoCode) {
            "6110000" -> viewModel.clickGugun(AreaData.seoulCode[position]) // 서울
            "6410000" -> viewModel.clickGugun(AreaData.gyeonggiCode[position]) // 경기
            "6260000" -> viewModel.clickGugun(AreaData.busanCode[position]) // 부산
            "6270000" -> viewModel.clickGugun(AreaData.daeguCode[position]) // 대구
            "6280000" -> viewModel.clickGugun(AreaData.incheonCode[position]) // 인천
            "6290000" -> viewModel.clickGugun(AreaData.gwangjuCode[position]) // 광주
            "6300000" -> viewModel.clickGugun(AreaData.daejeonCode[position]) // 대전
            "6310000" -> viewModel.clickGugun(AreaData.ulsanCode[position]) // 울산
            "6420000" -> viewModel.clickGugun(AreaData.gangwondoCode[position]) // 강원도
            "6430000" -> viewModel.clickGugun(AreaData.chungcheongbukdoCode[position]) // 충청북도
            "6440000" -> viewModel.clickGugun(AreaData.chungcheongnamdoCode[position]) // 충청남도
            "6450000" -> viewModel.clickGugun(AreaData.jeonlabugdoCode[position]) // 전라북도
            "6460000" -> viewModel.clickGugun(AreaData.jeonlanamdoCode[position]) // 전라남도
            "6470000" -> viewModel.clickGugun(AreaData.gyeongsangbugdoCode[position]) // 경상북도
            "6480000" -> viewModel.clickGugun(AreaData.gyeongsangnamdoCode[position]) // 경상남도
            else -> viewModel.clickGugun("")
        }
    }

    private fun clickSearch(searchText: String, sidoCode: String, gugunCode: String, startDay: String, endDay: String) {
        binding.container.visibility = View.GONE
        binding.snSido.adapter = ArrayAdapter1(mainActivity, android.R.layout.simple_spinner_dropdown_item, AreaData.sidoName)
        binding.snGugun.visibility = View.GONE
        viewModel.clickSearch(searchText, sidoCode, gugunCode, startDay, endDay)
    }
}