package com.aldidwikip.roomexamples.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.repository.Word
import com.aldidwikip.roomexamples.utils.AppUtils.showErrorMessage
import com.aldidwikip.roomexamples.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.fragment_insert.*

class InsertFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var wordViewModel: WordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        navController = Navigation.findNavController(view)

        button_save.setOnClickListener {
            when {
                TextUtils.isEmpty(edit_name.text) -> edit_name.showErrorMessage()
                TextUtils.isEmpty(edit_job.text) -> edit_job.showErrorMessage()
                TextUtils.isEmpty(edit_city.text) -> edit_city.showErrorMessage()
                else -> {
                    val word = Word(0,
                            edit_name.text.toString(),
                            edit_job.text.toString(),
                            edit_city.text.toString()
                    )
                    wordViewModel.insert(word)
                    navController.navigate(R.id.action_insertFragment_to_homeFragment)
                }
            }
        }
    }
}