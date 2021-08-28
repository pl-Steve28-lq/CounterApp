package com.steve28.counterapp.state

data class CounterState(val value: Int = 0) {
    fun applyEvent(event: CounterEvent) = when (event) {
        is CounterEvent.INCREASE -> CounterState(value + event.value)
        is CounterEvent.DECREASE -> CounterState(value - event.value)
        CounterEvent.CLEAR -> CounterState()
    }
}