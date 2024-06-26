package com.example.colorpickerjasontran.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ColorViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("color_prefs", Context.MODE_PRIVATE)

    private val _red = MutableLiveData(sharedPreferences.getFloat("red", 0.5f))
    val red: LiveData<Float> = _red

    private val _green = MutableLiveData(sharedPreferences.getFloat("green", 0.5f))
    val green: LiveData<Float> = _green

    private val _blue = MutableLiveData(sharedPreferences.getFloat("blue", 0.5f))
    val blue: LiveData<Float> = _blue

    private val _redSwitchState = MutableLiveData(sharedPreferences.getBoolean("redSwitchState", true))
    val redSwitchState: LiveData<Boolean> = _redSwitchState

    private val _greenSwitchState = MutableLiveData(sharedPreferences.getBoolean("greenSwitchState", true))
    val greenSwitchState: LiveData<Boolean> = _greenSwitchState

    private val _blueSwitchState = MutableLiveData(sharedPreferences.getBoolean("blueSwitchState", true))
    val blueSwitchState: LiveData<Boolean> = _blueSwitchState

    private var previousRed = _red.value ?: 0.5f
    private var previousGreen = _green.value ?: 0.5f
    private var previousBlue = _blue.value ?: 0.5f

    fun setRed(value: Float) {
        _red.value = value
        sharedPreferences.edit().putFloat("red", value).apply()
    }

    fun setGreen(value: Float) {
        _green.value = value
        sharedPreferences.edit().putFloat("green", value).apply()
    }

    fun setBlue(value: Float) {
        _blue.value = value
        sharedPreferences.edit().putFloat("blue", value).apply()
    }

    fun setRedSwitch(state: Boolean) {
        if (state) {
            _red.value = previousRed
        } else {
            previousRed = _red.value ?: 0.5f
            _red.value = 0f
        }
        _redSwitchState.value = state
        sharedPreferences.edit().putBoolean("redSwitchState", state).apply()
    }

    fun setGreenSwitch(state: Boolean) {
        if (state) {
            _green.value = previousGreen
        } else {
            previousGreen = _green.value ?: 0.5f
            _green.value = 0f
        }
        _greenSwitchState.value = state
        sharedPreferences.edit().putBoolean("greenSwitchState", state).apply()
    }

    fun setBlueSwitch(state: Boolean) {
        if (state) {
            _blue.value = previousBlue
        } else {
            previousBlue = _blue.value ?: 0.5f
            _blue.value = 0f
        }
        _blueSwitchState.value = state
        sharedPreferences.edit().putBoolean("blueSwitchState", state).apply()
    }

    fun resetColorsAndSwitches() {
        setRed(0.5f)
        setGreen(0.5f)
        setBlue(0.5f)
        setRedSwitch(true)
        setGreenSwitch(true)
        setBlueSwitch(true)
    }
}
