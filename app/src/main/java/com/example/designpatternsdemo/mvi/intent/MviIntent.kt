package com.example.designpatternsdemo.mvi.intent

sealed class MviIntent {
    data object FetchProducts : MviIntent()
}