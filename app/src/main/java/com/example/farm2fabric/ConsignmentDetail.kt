package com.example.farm2fabric

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Locale

class ConsignmentDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consignment_detail)

        val consignmentId = intent.getStringExtra("consignmentId") ?: "N/A"
        val amount = intent.getStringExtra("amount") ?: "N/A"
        val status = intent.getStringExtra("status") ?: "N/A"
        val date = intent.getStringExtra("date") ?: "N/A"
        val paymentId = intent.getStringExtra("payment_id") ?: "N/A"
        val quantity = intent.getStringExtra("quantity") ?: "N/A"

        val inputFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        val outputFormatD = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val outputFormatT = SimpleDateFormat("hh:mm a", Locale.getDefault())

        val datet = inputFormat.parse(date)
        val formattedDateOnly = outputFormatD.format(datet!!)
        val formattedTimeOnly = outputFormatT.format(datet!!)

        findViewById<LinearLayout>(R.id.back4).setOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.valueConsignmentId).text = consignmentId
        findViewById<TextView>(R.id.valueWeight).text = "${quantity} Kg"
        findViewById<TextView>(R.id.valuePrice).text = "Rs. ${amount}"
        findViewById<TextView>(R.id.valuePaymentId).text = paymentId
        findViewById<TextView>(R.id.Ctitle).text = consignmentId
        findViewById<TextView>(R.id.valueDate).text = formattedDateOnly
        findViewById<TextView>(R.id.valueTime).text = formattedTimeOnly


        // TextViews
        val stepCreating = findViewById<TextView>(R.id.stepCreating)
        val stepAccepted = findViewById<TextView>(R.id.stepAccepted)
        val stepInTransit = findViewById<TextView>(R.id.stepInTransit)
        val stepFinished = findViewById<TextView>(R.id.stepFinished)
        val steps = listOf(stepCreating, stepAccepted, stepInTransit, stepFinished)

        // ImageViews (checkmark icons)
        val iconCreating = findViewById<ImageView>(R.id.iconCreating)
        val iconAccepted = findViewById<ImageView>(R.id.iconAccepted)
        val iconInTransit = findViewById<ImageView>(R.id.iconInTransit)
        val iconFinished = findViewById<ImageView>(R.id.iconFinished)
        val icons = listOf(iconCreating, iconAccepted, iconInTransit, iconFinished)

        // Current status (can be dynamically fetched)

        updateOrderStatus(status, steps, icons)
    }

    private fun updateOrderStatus(currentStatus: String, steps: List<TextView>, icons: List<ImageView>) {
        val statusList = listOf("step0", "step1", "step2", "step3")
        val currentIndex = statusList.indexOf(currentStatus)

        for (i in steps.indices) {
            if (i <= currentIndex) {
                steps[i].setTextColor(ContextCompat.getColor(this, R.color.primary))
                steps[i].setTypeface(null, Typeface.BOLD)
                icons[i].setImageResource(R.drawable.circle_check_green)
            } else {
                steps[i].setTextColor(Color.GRAY)
                steps[i].setTypeface(null, Typeface.NORMAL)
                icons[i].setImageResource(R.drawable.circle_check_gray)
            }
        }
    }
}
