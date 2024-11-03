package com.example.guesssamenumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _score = MutableLiveData(50)
    val score: LiveData<Int> get() = _score

    private val _range = MutableLiveData(Range(1,5))
    val range: LiveData<Range> get() = _range

    fun setScore(score: Int) {
        _score.value = score
    }

    fun setRange(range: Range) {
        _range.value = range
    }
}