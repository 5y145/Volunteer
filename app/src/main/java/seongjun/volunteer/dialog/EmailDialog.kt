package seongjun.volunteer.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import seongjun.volunteer.R

class EmailDialog(context : Context) {

    private val dialog = Dialog(context)

    @SuppressLint("SetTextI18n")
    fun showDialog(context : Context) {
        dialog.setContentView(R.layout.dialog_email)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val ibClose = dialog.findViewById<ImageButton>(R.id.ibClose)
        val et = dialog.findViewById<EditText>(R.id.et)
        val btnSend = dialog.findViewById<Button>(R.id.btnSend)

        ibClose.setOnClickListener {
            dialog.dismiss()
        }

        btnSend.setOnClickListener {
            if (et.text.toString() != "") onClickListener.onClick(et.text.toString())
            dialog.dismiss()
        }
    }

    interface ButtonClickListener {
        fun onClick(contents: String)
    }

    private lateinit var onClickListener: ButtonClickListener
    fun setOnClickListener(listener : ButtonClickListener){ onClickListener = listener }
}