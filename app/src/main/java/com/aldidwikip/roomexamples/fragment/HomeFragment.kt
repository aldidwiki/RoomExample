package com.aldidwikip.roomexamples.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.RoomExample
import com.aldidwikip.roomexamples.adapter.WordListAdapter
import com.aldidwikip.roomexamples.repository.Word
import com.aldidwikip.roomexamples.utils.Constant.ARG_CITY
import com.aldidwikip.roomexamples.utils.Constant.ARG_ID
import com.aldidwikip.roomexamples.utils.Constant.ARG_JOB
import com.aldidwikip.roomexamples.utils.Constant.ARG_NAME
import com.aldidwikip.roomexamples.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.custom_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), WordListAdapter.OnItemClickCallback {
    private lateinit var wordViewModel: WordViewModel
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        navController = Navigation.findNavController(view)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(RoomExample.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(RoomExample.context)
        adapter.setOnItemClickCallback(this)

        wordViewModel.allWords.observe(viewLifecycleOwner, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                adapter.setWords(it)
            }
        })

        fab.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_insertFragment)
        }
    }

    override fun onItemClick(data: Word) {
        MaterialDialog(requireContext(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            title(text = "Choose Action : ")
            customView(R.layout.custom_bottom_sheet)

            action_delete.setOnClickListener {
                wordViewModel.delete(data.id)
                Toast.makeText(RoomExample.context, "${data.name} Deleted", Toast.LENGTH_SHORT).show()
                dismiss()
            }

            action_edit.setOnClickListener {
                val bundle = bundleOf(
                        ARG_ID to data.id,
                        ARG_NAME to data.name,
                        ARG_JOB to data.job,
                        ARG_CITY to data.city
                )
                navController.navigate(R.id.action_homeFragment_to_editFragment, bundle)
                dismiss()
            }
        }
    }
}