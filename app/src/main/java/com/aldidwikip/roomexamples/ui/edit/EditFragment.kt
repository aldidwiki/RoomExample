package com.aldidwikip.roomexamples.ui.edit

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.RoomExample
import com.aldidwikip.roomexamples.databinding.FragmentEditBinding
import com.aldidwikip.roomexamples.utils.AppUtils.showErrorMessage
import com.aldidwikip.roomexamples.utils.Constant.ARG_ID
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment() {
    private val editViewModel: EditViewModel by lazy {
        ViewModelProvider(this).get(EditViewModel::class.java)
    }
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentEditBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = editViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val id = arguments?.getInt(ARG_ID)
        editViewModel.setId(id!!).observe(viewLifecycleOwner, Observer { selectedWord ->
            selectedWord?.let {
                editViewModel.setWord(it)
            }
        })

        button_save.setOnClickListener {
            when {
                TextUtils.isEmpty(edit_name.text) -> edit_name.showErrorMessage()
                TextUtils.isEmpty(edit_job.text) -> edit_job.showErrorMessage()
                TextUtils.isEmpty(edit_city.text) -> edit_city.showErrorMessage()
                else -> {
                    editViewModel.update(
                            id,
                            edit_name.text.toString(),
                            if (rb_male.isChecked) rb_male.text.toString() else rb_female.text.toString(),
                            edit_job.text.toString(),
                            edit_city.text.toString()
                    )
                    Toast.makeText(RoomExample.context, "$id Updated", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_editFragment_to_homeFragment)
                }
            }
        }
    }
}