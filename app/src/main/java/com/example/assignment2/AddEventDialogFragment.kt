package com.example.assignment2

import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AddEventDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_add_event, null)

        val eventNameEditText: EditText = view.findViewById(R.id.event_name)
        val eventDateEditText: EditText = view.findViewById(R.id.event_date)
        val eventTypeSpinner: Spinner = view.findViewById(R.id.event_type_spinner)
        val eventPriorityRadioGroup: RadioGroup = view.findViewById(R.id.event_priority_group)
        val addButton: Button = view.findViewById(R.id.add_button)


        val eventTypes = arrayOf("Meeting", "Birthday", "Anniversary")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, eventTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        eventTypeSpinner.adapter = adapter

        addButton.setOnClickListener {
            val name = eventNameEditText.text.toString()
            val date = eventDateEditText.text.toString()
            val type = eventTypeSpinner.selectedItem.toString()
            val priority = when (eventPriorityRadioGroup.checkedRadioButtonId) {
                R.id.high_priority -> "High"
                R.id.low_priority -> "Low"
                else -> "Low"
            }

            val event = Event(name, date, type, priority)
            EventManager.addEvent(event)
            Toast.makeText(requireContext(), "Event Added", Toast.LENGTH_SHORT).show()
dismiss()
        }

        builder.setView(view)
        return builder.create()
    }
}
