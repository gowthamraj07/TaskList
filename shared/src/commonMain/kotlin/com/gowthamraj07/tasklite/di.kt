package com.gowthamraj07.tasklite

import com.gowthamraj07.tasklite.repository.SettingsTaskRepository
import com.gowthamraj07.tasklite.repository.TaskRepository
import com.gowthamraj07.tasklite.viewmodel.TaskViewModel
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val sharedModule = module {
    single<TaskRepository> { SettingsTaskRepository(Settings()) }
    single { TaskViewModel(get()) }
}