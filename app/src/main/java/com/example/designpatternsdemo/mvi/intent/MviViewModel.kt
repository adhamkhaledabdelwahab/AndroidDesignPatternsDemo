package com.example.designpatternsdemo.mvi.intent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.designpatternsdemo.core.data_source.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MviViewModel @Inject constructor(private var remoteDataSource: RemoteDataSource) :
    ViewModel() {
    private val userIntent = Channel<MviIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MviState>(MviState.Idle)
    val state: StateFlow<MviState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MviIntent.FetchProducts -> fetchProducts()
                }
            }
        }
    }

    fun addIntent(mviIntent: MviIntent){
        viewModelScope.launch {
            userIntent.send(mviIntent)
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                _state.value = MviState.Loading
                _state.value = MviState.Success(remoteDataSource.getProducts())
            } catch (e: Exception) {
                _state.value = MviState.Error(e.localizedMessage)
                Log.e(
                    "MVP Presenter",
                    e.localizedMessage?.toString() ?: "Error Occurred"
                )
            }
        }
    }
}