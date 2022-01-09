package seongjun.volunteer.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import seongjun.volunteer.activity.MainActivity
import seongjun.volunteer.databinding.FragmentMoreBinding
import seongjun.volunteer.dialog.AnswerDialog
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
            mainActivity.sendEmail()
        }
    }
}