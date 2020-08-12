package com.aldidwikip.roomexamples.ui.home

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
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.aldidwikip.roomexamples.R
import com.aldidwikip.roomexamples.RoomExample
import com.aldidwikip.roomexamples.data.model.Word
import com.aldidwikip.roomexamples.ui.adapter.WordListAdapter
import com.aldidwikip.roomexamples.utils.Constant.ARG_ID
import kotlinx.android.synthetic.main.custom_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), WordListAdapter.OnItemClickCallback {
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val rvAdapter = WordListAdapter(RoomExample.context)
        rvAdapter.setOnItemClickCallback(this)
        recyclerview.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(RoomExample.context)
        }

        homeViewModel.allWords.observe(viewLifecycleOwner, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                rvAdapter.setWords(it)
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
                homeViewModel.delete(data.id)
                Toast.makeText(RoomExample.context, "${data.name} Deleted", Toast.LENGTH_SHORT).show()
                dismiss()
            }

            action_edit.setOnClickListener {
                val bundle = bundleOf(ARG_ID to data.id)
                navController.navigate(R.id.action_homeFragment_to_editFragment, bundle)
                dismiss()
            }
        }
    }
}