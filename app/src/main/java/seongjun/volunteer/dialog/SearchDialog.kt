package seongjun.volunteer.dialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.widget.addTextChangedListener
import seongjun.volunteer.R
import seongjun.volunteer.model.AreaData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SearchDialog(context : Context) {

    private val dialog = Dialog(context)
    private var sidoCode = ""
    private var gugunCode = ""
    private var startDay = ""
    private var endDay = ""
//    private var startDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
//    private var endDay = LocalDate.now().plusDays(14).format(DateTimeFormatter.ofPattern("yyyyMMdd"))

    @SuppressLint("SetTextI18n")
    fun showDialog(context : Context) {
        dialog.setContentView(R.layout.dialog_search)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val ibClose = dialog.findViewById<ImageButton>(R.id.ibClose)
        val etSearch = dialog.findViewById<EditText>(R.id.etSearch)
        val ibCancel = dialog.findViewById<ImageButton>(R.id.ibCancel)
        val snSido = dialog.findViewById<Spinner>(R.id.snSido)
        val snGugun = dialog.findViewById<Spinner>(R.id.snGugun)
        val btnSearch = dialog.findViewById<Button>(R.id.btnSearch)

        val tvStartDay = dialog.findViewById<TextView>(R.id.tvStartDay)
        val ibStartDay = dialog.findViewById<ImageButton>(R.id.ibStartDay)
        val tvEndDay = dialog.findViewById<TextView>(R.id.tvEndDay)
        val ibEndDay = dialog.findViewById<ImageButton>(R.id.ibEndDay)

        snSido.apply {
            adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.sidoName)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    if (position >= 0) {
                        sidoCode = AreaData.sidoCode[position]

                        when(sidoCode) {
                            "6110000" -> { // ??????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.seoulName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6410000" -> { // ??????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.gyeonggiName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6260000" -> { // ??????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.busanName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6270000" -> { // ??????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.daeguName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6280000" -> { // ??????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.incheonName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6290000" -> { // ??????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.gangwondoName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6300000" -> { // ??????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.daejeonName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6310000" -> { // ??????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.ulsanName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6420000" -> { // ?????????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.gangwondoName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6430000" -> { // ????????????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.chungcheongbukdoName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6440000" -> { // ????????????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.chungcheongnamdoName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6450000" -> { // ????????????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.jeonlabugdoName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6460000" -> { // ????????????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.jeonlanamdoName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6470000" -> { // ????????????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.gyeongsangbugdoName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6480000" -> { // ????????????
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.gyeongsangnamdoName)
                                snGugun.visibility = View.VISIBLE
                            }
                            else -> { snGugun.visibility = View.GONE }
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        snGugun.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    if (position >= 0) {
                        when(sidoCode) {
                            "6110000" -> gugunCode = AreaData.seoulCode[position] // ??????
                            "6410000" -> gugunCode = AreaData.gyeonggiCode[position] // ??????
                            "6260000" -> gugunCode = AreaData.busanCode[position] // ??????
                            "6270000" -> gugunCode = AreaData.daeguCode[position] // ??????
                            "6280000" -> gugunCode = AreaData.incheonCode[position] // ??????
                            "6290000" -> gugunCode = AreaData.gwangjuCode[position] // ??????
                            "6300000" -> gugunCode = AreaData.daejeonCode[position] // ??????
                            "6310000" -> gugunCode = AreaData.ulsanCode[position] // ??????
                            "6420000" -> gugunCode = AreaData.gangwondoCode[position] // ?????????
                            "6430000" -> gugunCode = AreaData.chungcheongbukdoCode[position] // ????????????
                            "6440000" -> gugunCode = AreaData.chungcheongnamdoCode[position] // ????????????
                            "6450000" -> gugunCode = AreaData.jeonlabugdoCode[position] // ????????????
                            "6460000" -> gugunCode = AreaData.jeonlanamdoCode[position] // ????????????
                            "6470000" -> gugunCode = AreaData.gyeongsangbugdoCode[position] // ????????????
                            "6480000" -> gugunCode = AreaData.gyeongsangnamdoCode[position] // ????????????
                            else -> gugunCode = ""
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        ibClose.setOnClickListener {
            dialog.dismiss()
        }

        etSearch.addTextChangedListener {
            if (etSearch.text.toString() != "") ibCancel.visibility = View.VISIBLE else ibCancel.visibility = View.GONE
        }

        etSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(etSearch.windowToken, 0)
                    return true
                }
                return false
            }
        })

        ibCancel.setOnClickListener {
            etSearch.setText("")
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(etSearch.windowToken, 0)
        }

        ibStartDay.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)
            val dlg = DatePickerDialog(context, { view, year, month, dayOfMonth ->
                    val m = if (month + 1 < 10) "0${month + 1}" else "${month + 1}"
                    val d = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
                    startDay = "${year}${m}${d}"
                    tvStartDay.text = dayToText(startDay)
                }, year, month, date)
            dlg.show()
        }

        ibEndDay.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH).plus(1)
            val date: Int = today.get(Calendar.DATE)
            val dlg = DatePickerDialog(context, { view, year, month, dayOfMonth ->
                    val m = if (month + 1 < 10) "0${month + 1}" else "${month + 1}"
                    val d = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
                    endDay = "${year}${m}${d}"
                    tvEndDay.text = dayToText(endDay)
                }, year, month, date)
            dlg.show()
        }

        btnSearch.setOnClickListener {
            onClickListener.onClick(etSearch.text.toString(), sidoCode, gugunCode, startDay, endDay)
            dialog.dismiss()
        }
    }

    private fun dayToText(day: String): String {
        return "${day.toInt() % 1000000 / 10000}??? ${day.toInt() % 10000 / 100}??? ${day.toInt() % 100}???"
    }

    interface ButtonClickListener {
        fun onClick(searchText: String, sidoCode: String, gugunCode: String, startDay: String, endDay: String)
    }

    private lateinit var onClickListener: ButtonClickListener
    fun setOnClickListener(listener : ButtonClickListener){ onClickListener = listener }
}