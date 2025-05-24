package com.gowthamraj07.tasklite

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {

        modules(
            sharedModule
        )
    }
}

fun <T> StateFlow<T>.observe(onEach: (T) -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        this@observe.collect { value ->
            onEach(value)
        }
    }
}