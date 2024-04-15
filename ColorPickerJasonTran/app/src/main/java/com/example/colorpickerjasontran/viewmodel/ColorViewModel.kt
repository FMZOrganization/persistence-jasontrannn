package com.example.colorpickerjasontran.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorViewModel : ViewModel() {
    // LiveData for RGB values
    private val _red = MutableLiveData<Float>(0.5f)  // Initial value set to 0.5
    val red: LiveData<Float> = _red

    private val _green = MutableLiveData<Float>(0.5f)  // Initial value set to 0.5
    val green: LiveData<Float> = _green

    private val _blue = MutableLiveData<Float>(0.5f)  // Initial value set to 0.5
    val blue: LiveData<Float> = _blue

    // LiveData for the state of switches
    private val _redSwitchState = MutableLiveData<Boolean>(true)  // Initial switch state set to true
    val redSwitchState: LiveData<Boolean> = _redSwitchState

    private val _greenSwitchState = MutableLiveData<Boolean>(true)  // Initial switch state set to true
    val greenSwitchState: LiveData<Boolean> = _greenSwitchState

    private val _blueSwitchState = MutableLiveData<Boolean>(true)  // Initial switch state set to true
    val blueSwitchState: LiveData<Boolean> = _blueSwitchState

    // Methods to update the color values
    fun setRed(value: Float) {
        _red.value = value
    }

    fun setGreen(value: Float) {
        _green.value = value
    }

    fun setBlue(value: Float) {
        _blue.value = value
    }

    // Methods to update the switch states
    fun setRedSwitch(state: Boolean) {
        _redSwitchState.value = state
        if (!state) {
            setRed(0f)  // If the switch is turned off, set color value to 0
        }
    }

    fun setGreenSwitch(state: Boolean) {
        _greenSwitchState.value = state
        if (!state) {
            setGreen(0f)  // If the switch is turned off, set color value to 0
        }
    }

    fun setBlueSwitch(state: Boolean) {
        _blueSwitchState.value = state
        if (!state) {
            setBlue(0f)  // If the switch is turned off, set color value to 0
        }
    }

    // Method to reset colors and switches to their default values
    fun resetColorsAndSwitches() {
        setRed(0.5f)
        setGreen(0.5f)
        setBlue(0.5f)
        setRedSwitch(true)
        setGreenSwitch(true)
        setBlueSwitch(true)
    }
}
