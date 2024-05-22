package com.example.colorpickerjasontran

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.colorpickerjasontran.viewmodel.ColorViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ColorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ColorViewModel::class.java)

        val colorDisplay: FrameLayout = findViewById(R.id.colorDisplay)
        val redSlider: SeekBar = findViewById(R.id.redSlider)
        val greenSlider: SeekBar = findViewById(R.id.greenSlider)
        val blueSlider: SeekBar = findViewById(R.id.blueSlider)
        val redSwitch: Switch = findViewById(R.id.redSwitch)
        val greenSwitch: Switch = findViewById(R.id.greenSwitch)
        val blueSwitch: Switch = findViewById(R.id.blueSwitch)
        val resetButton: Button = findViewById(R.id.resetButton)

        viewModel.red.observe(this, Observer { value ->
            redSlider.progress = (value * 100).toInt()
            updateColorDisplay(colorDisplay, viewModel.red.value!!, viewModel.green.value!!, viewModel.blue.value!!)
        })

        viewModel.green.observe(this, Observer { value ->
            greenSlider.progress = (value * 100).toInt()
            updateColorDisplay(colorDisplay, viewModel.red.value!!, viewModel.green.value!!, viewModel.blue.value!!)
        })

        viewModel.blue.observe(this, Observer { value ->
            blueSlider.progress = (value * 100).toInt()
            updateColorDisplay(colorDisplay, viewModel.red.value!!, viewModel.green.value!!, viewModel.blue.value!!)
        })

        viewModel.redSwitchState.observe(this, Observer { state ->
            redSwitch.isChecked = state
            redSlider.isEnabled = state
        })

        viewModel.greenSwitchState.observe(this, Observer { state ->
            greenSwitch.isChecked = state
            greenSlider.isEnabled = state
        })

        viewModel.blueSwitchState.observe(this, Observer { state ->
            blueSwitch.isChecked = state
            blueSlider.isEnabled = state
        })

        redSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    viewModel.setRed(progress / 100f)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        greenSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    viewModel.setGreen(progress / 100f)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        blueSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    viewModel.setBlue(progress / 100f)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        redSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setRedSwitch(isChecked)
        }

        greenSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setGreenSwitch(isChecked)
        }

        blueSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setBlueSwitch(isChecked)
        }

        resetButton.setOnClickListener {
            viewModel.resetColorsAndSwitches()
        }

        // Restore saved instance state if available
        if (savedInstanceState != null) {
            val red = savedInstanceState.getFloat("red", 0.5f)
            val green = savedInstanceState.getFloat("green", 0.5f)
            val blue = savedInstanceState.getFloat("blue", 0.5f)
            val redSwitchState = savedInstanceState.getBoolean("redSwitchState", true)
            val greenSwitchState = savedInstanceState.getBoolean("greenSwitchState", true)
            val blueSwitchState = savedInstanceState.getBoolean("blueSwitchState", true)

            viewModel.setRed(red)
            viewModel.setGreen(green)
            viewModel.setBlue(blue)
            viewModel.setRedSwitch(redSwitchState)
            viewModel.setGreenSwitch(greenSwitchState)
            viewModel.setBlueSwitch(blueSwitchState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putFloat("red", viewModel.red.value ?: 0.5f)
        outState.putFloat("green", viewModel.green.value ?: 0.5f)
        outState.putFloat("blue", viewModel.blue.value ?: 0.5f)
        outState.putBoolean("redSwitchState", viewModel.redSwitchState.value ?: true)
        outState.putBoolean("greenSwitchState", viewModel.greenSwitchState.value ?: true)
        outState.putBoolean("blueSwitchState", viewModel.blueSwitchState.value ?: true)
    }

    private fun updateColorDisplay(view: FrameLayout, red: Float, green: Float, blue: Float) {
        view.setBackgroundColor(Color.rgb((red * 255).toInt(), (green * 255).toInt(), (blue * 255).toInt()))
    }
}
