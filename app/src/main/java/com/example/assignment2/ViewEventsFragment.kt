package com.example.assignment2

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ViewEventsFragment : Fragment() {

    private lateinit var eventsListView: ListView
    private lateinit var eventsAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_events, container, false)

        eventsListView = view.findViewById(R.id.events_list_view)
        updateEventsList()

        // Enable context menu for the ListView
        registerForContextMenu(eventsListView)

        return view
    }

    // Update the events list
    private fun updateEventsList() {
        val events = EventManager.getEvents()
        val eventNames = events.map { it.name }
        eventsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, eventNames)
        eventsListView.adapter = eventsAdapter
    }



}
