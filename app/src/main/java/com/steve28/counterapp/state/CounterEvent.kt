package com.steve28.counterapp.state

sealed class CounterEvent {
    class INCREASE(val value: Int): CounterEvent()
    class DECREASE(val value: Int) : CounterEvent()
    object CLEAR: CounterEvent()
}