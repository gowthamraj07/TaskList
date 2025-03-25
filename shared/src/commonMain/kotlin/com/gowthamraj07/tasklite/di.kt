package com.gowthamraj07.tasklite

import com.gowthamraj07.tasklite.repository.SettingsTaskRepository
import com.gowthamraj07.tasklite.repository.TaskRepository
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val taskModule = module {
    single<TaskRepository> { SettingsTaskRepository(Settings()) }
}