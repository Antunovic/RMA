package com.example.lv4

import com.google.firebase.firestore.FirebaseFirestore

class MeasurementRepository {
    private val db = FirebaseFirestore.getInstance()
    private val docRef = db.collection("BMI").document("9NlYwzeyhFzUp5RAluAp")

    fun saveMeasurement(measurement: Measurement) {
        val data = mapOf(
            "Visina" to measurement.height,
            "Tezina" to measurement.weight
        )
        docRef.update(data)
    }
}