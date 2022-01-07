package seongjun.volunteer.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import seongjun.volunteer.activity.DetailActivity
import seongjun.volunteer.activity.MainActivity
import seongjun.volunteer.adapter.HomeAdapter
import seongjun.volunteer.databinding.FragmentMoreBinding
import seongjun.volunteer.dialog.AnswerDialog
import seongjun.volunteer.dialog.EmailDialog
import seongjun.volunteer.dialog.SearchDialog
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.viewmodel.MainViewModel

class MoreFragment : Fragment() {

    private val binding by lazy { FragmentMoreBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setView()
        return binding.root
    }

    private fun setView() {
        binding.cvAnswer.setOnClickListener {
            val dialog = AnswerDialog(mainActivity)
            dialog.showDialog(mainActivity)
        }

        binding.cvEmail.setOnClickListener {
            val dialog = EmailDialog(mainActivity).apply {
                setOnClickListener(object: EmailDialog.ButtonClickListener{
                    override fun onClick(contents: String) { // 화면 이동
                        mainActivity.sendEmail(contents)
                    }
                })
            }
            dialog.showDialog(mainActivity)
        }
    }
}