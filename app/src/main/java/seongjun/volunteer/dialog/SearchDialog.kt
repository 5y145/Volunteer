package seongjun.volunteer.dialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
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

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    fun showDialog(context : Context) {
        dialog.setContentView(R.layout.dialog_search)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
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
                            "6110000" -> { // 서울
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.seoulName)
                                snGugun.visibility = View.VISIBLE
                            }
                            "6410000" -> { // 경기
                                snGugun.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, AreaData.gyeonggiName)
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
                            "6110000" -> gugunCode = AreaData.seoulCode[position] // 서울
                            "6410000" -> gugunCode = AreaData.gyeonggiCode[position] // 경기
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

        val nextDay = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        val nextWeek = LocalDate.now().plusDays(8).format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        tvStartDay.text = nextDay

        ibStartDay.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE).plus(1)
            val dlg = DatePickerDialog(context, { view, year, month, dayOfMonth ->
                    val m = if (month + 1 < 10) "0${month + 1}" else "${month + 1}"
                    val d = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
                    tvStartDay.text = "${year}${m}${d}"
                }, year, month, date)
            dlg.show()
        }

        tvEndDay.text = nextWeek

        ibEndDay.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE).plus(8)
            val dlg = DatePickerDialog(context, { view, year, month, dayOfMonth ->
                    val m = if (month + 1 < 10) "0${month + 1}" else "${month + 1}"
                    val d = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
                    tvEndDay.text = "${year}${m}${d}"
                }, year, month, date)
            dlg.show()
        }

        btnSearch.setOnClickListener {
            onClickListener.onClick(etSearch.text.toString(), sidoCode, gugunCode, tvStartDay.text.toString(), tvEndDay.text.toString())
            dialog.dismiss()
        }
    }

    interface ButtonClickListener {
        fun onClick(searchText: String, sidoCode: String, gugunCode: String, startDay: String, endDay: String)
    }

    private lateinit var onClickListener: ButtonClickListener
    fun setOnClickListener(listener : ButtonClickListener){ onClickListener = listener }
}