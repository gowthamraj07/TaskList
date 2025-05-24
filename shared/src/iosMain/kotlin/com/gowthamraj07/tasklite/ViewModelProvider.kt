package com.gowthamraj07.tasklite

import com.gowthamraj07.tasklite.viewmodel.TaskViewModel
import org.koin.core.component.KoinComponent

object ViewModelProvider : KoinComponent {
    fun getTaskViewModel(): TaskViewModel {
        return getKoin().get()
    }
}