package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
     var _word = MutableLiveData<String>()
    val word :LiveData<String>
    get()= _word

    // The current score
     private var _score = MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val evenetGameFinish:LiveData<Boolean>
    get()= _eventGameFinish

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {

        _eventGameFinish.value=false
        resetList()
        nextWord()
        _score.value=0
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destruido")
    }

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            _eventGameFinish.value=true
            //gameFinished()
        } else {
            _word.value = wordList.removeAt(0)
        }

    }

   fun onSkip() {
        _score.value= (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value=(score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete(){
        _eventGameFinish.value=false
    }



}