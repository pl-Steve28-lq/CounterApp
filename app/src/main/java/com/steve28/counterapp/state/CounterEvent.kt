package com.steve28.counterapp.state

sealed class CounterEvent {
    class INCREASE(val value: Int = 1): CounterEvent()
    class DECREASE(val value: Int = 1) : CounterEvent()
    object CLEAR: CounterEvent()
}