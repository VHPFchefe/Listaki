package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import presentation.home.domain.viewmodel.HomeViewModel
import data.local.ShoppingListDatabase
import navigation.AppNavigation
import presentation.home.domain.event.HomeState

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ShoppingListDatabase::class.java,
            "shopping-list-database.db"
        ).build()
    }

    private val viewModel by viewModels<HomeViewModel>(
        factoryProducer = {
            viewModelFactory {
                initializer {
                    HomeViewModel(db.shoppingListDao())
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()
            AppNavigation(state = state, onEvent = viewModel::onEvent)
        }
    }
}