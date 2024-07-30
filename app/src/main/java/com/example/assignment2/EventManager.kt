package com.example.assignment2

object EventManager {
    private val events = mutableListOf<Event>()

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun getEvents(): List<Event> {
        return events
    }
}
