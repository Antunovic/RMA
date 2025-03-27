package com.example.lv4

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {
    private val _height = mutableStateOf(1.91f)
    private val _weight = mutableStateOf(60f)
    private val _bmi = mutableStateOf("0.00")
    val height: State<Float> = _height
    val weight: State<Float> = _weight
    val bmi: State<String> = _bmi

    private val repository = MeasurementRepository()

    fun updateMeasurements(newHeight: Float?, newWeight: Float?) {
        if (newWeight != null && newWeight >= 0) {
            _weight.value = newWeight
        }
        if (newHeight != null && newHeight >= 0) {
            _height.value = newHeight
        }
        if (_height.value > 0) {
            _bmi.value = String.format("%.2f", _weight.value / (_height.value * _height.value))
        }
        repository.saveMeasurement(
            Measurement(
                height = _height.value,
                weight = _weight.value,
            )
        )
    }
}