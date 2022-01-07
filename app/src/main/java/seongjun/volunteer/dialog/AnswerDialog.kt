package seongjun.volunteer.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageButton
import seongjun.volunteer.R

class AnswerDialog(context : Context) {

    private val dialog = Dialog(context)

    @SuppressLint("SetTextI18n")
    fun showDialog(context : Context) {
        dialog.setContentView(R.layout.dialog_answer)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val ibClose = dialog.findViewById<ImageButton>(R.id.ibClose)

        ibClose.setOnClickListener {
            dialog.dismiss()
        }
    }
}