package com.vmcorp.foodisu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmcorp.foodisu.R
import com.vmcorp.foodisu.adapter.DogListAdapter
import com.vmcorp.foodisu.viewmodel.DogsViewModel
import kotlinx.android.synthetic.main.fragment_lists.view.*

class ListsFragment : Fragment() {
    private lateinit var viewModel: DogsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(DogsViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lists, container, false)
        viewModel.dogListData.observe(this, Observer {
            it?.let {
                viewManager = LinearLayoutManager(activity)
                viewAdapter = DogListAdapter(it)

                recyclerView = view.findViewById<RecyclerView>(R.id.rv_dogList).apply {
                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    setHasFixedSize(true)

                    // use a linear layout manager
                    layoutManager = viewManager

                    // specify an viewAdapter (see also next example)
                    adapter = viewAdapter

                }
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        view.textview.setOnClickListener {
//            Navigation.createNavigateOnClickListener(R.id.action_listsFragment, null)
//        }
    }

}
