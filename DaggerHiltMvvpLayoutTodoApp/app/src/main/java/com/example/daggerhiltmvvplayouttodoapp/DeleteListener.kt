package com.example.daggerhiltmvvplayouttodoapp

import com.example.daggerhiltmvvplayouttodoapp.data.Todo

interface DeleteListener {
    fun onDeleteIconClicked(todo: Todo)
}