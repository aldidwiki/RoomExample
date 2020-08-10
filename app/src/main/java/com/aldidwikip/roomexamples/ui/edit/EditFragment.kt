package com.aldidwikip.roomexamples.ui.edit

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.RoomExample
import com.aldidwikip.roomexamples.utils.AppUtils.showErrorMessage
import com.aldidwikip.roomexamples.utils.Constant.ARG_CITY
import com.aldidwikip.roomexamples.utils.Constant.ARG_GENDER
import com.aldidwikip.roomexamples.utils.Constant.ARG_ID
import com.aldidwikip.roomexamples.utils.Constant.ARG_JOB
import com.aldidwikip.roomexamples.utils.Constant.ARG_NAME
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment() {
    private lateinit var editViewModel: EditViewModel
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editViewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        navController = Navigation.findNavController(view)

        val id = arguments?.getInt(ARG_ID)
        val gender = arguments?.getString(ARG_GENDER)
        edit_name.setText(arguments?.getString(ARG_NAME))
        rb_group_gender.check(if (gender == "Male") R.id.rb_male else R.id.rb_female)
        edit_job.setText(arguments?.getString(ARG_JOB))
        edit_city.setText(arguments?.getString(ARG_CITY))

        button_save.setOnClickListener {
            when {
                TextUtils.isEmpty(edit_name.text) -> edit_name.showErrorMessage()
                TextUtils.isEmpty(edit_job.text) -> edit_job.showErrorMessage()
                TextUtils.isEmpty(edit_city.text) -> edit_city.showErrorMessage()
                else -> {
                    editViewModel.update(
                            id!!,
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