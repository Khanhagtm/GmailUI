package com.example.gmail

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.github.javafaker.Faker
import java.util.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gmail.ui.theme.GmailTheme
import java.text.SimpleDateFormat
import android.graphics.Color

class MainActivity : ComponentActivity() {

    private val faker = Faker(Locale("en-GB"))
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout: LinearLayout = findViewById(R.id.scroll_linear_layout)

        for (i in 1..20) {
            val newLayout = layoutInflater.inflate(R.layout.content_item, null) as LinearLayout

            val randomName = faker.name().fullName()
            val randomContent = faker.lorem().sentence()
            val randomTime = timeFormat.format(faker.date().birthday())

            val senderTextView = newLayout.findViewById<TextView>(R.id.sender_name)
            val senderTimeTextView = newLayout.findViewById<TextView>(R.id.sender_time)
            val mailContentTextView = newLayout.findViewById<TextView>(R.id.mail_content)
            val senderCircleTextView = newLayout.findViewById<TextView>(R.id.sender_circle)
            val starTextView = newLayout.findViewById<TextView>(R.id.star)
            val contentLayout = newLayout.findViewById<LinearLayout>(R.id.contentlayout)

            senderTextView.text = randomName
            senderTimeTextView.text = randomTime
            mailContentTextView.text = randomContent
            mailContentTextView.maxLines = 1
            mailContentTextView.ellipsize = android.text.TextUtils.TruncateAt.END
            senderCircleTextView.text = randomName.first().toString()


            var isClicked = false
            newLayout.setOnClickListener {
                if (isClicked) {
                    senderCircleTextView.background = getDrawable(R.drawable.circle_shape)
                    senderCircleTextView.text = randomName.first().toString()
                    senderCircleTextView.setTextColor(Color.WHITE)
                    newLayout.setBackgroundColor(Color.WHITE) // Change the background color back to white
                    senderTextView.text = randomName
                    mailContentTextView.text = randomContent
                    senderTimeTextView.text = randomTime
                    senderTimeTextView.background = null
                    starTextView.background = getDrawable(R.drawable.star_outline)
                } else {
                    senderCircleTextView.background = getDrawable(R.drawable.circle_shape_cyan)
                    senderCircleTextView.text = ""
                    senderCircleTextView.setTextColor(Color.WHITE)
                    newLayout.setBackgroundColor(Color.parseColor("#D3D3D3")) // Change the background color to gray
                    senderTextView.text = "Touch a sender image to select that conversation."
                    mailContentTextView.text = ""
                    senderTimeTextView.background = getDrawable(R.drawable.cancel_icon_use)
                    senderTimeTextView.text = ""
                    val params = senderTimeTextView.layoutParams as LinearLayout.LayoutParams
                    params.width = 100 // Set the width you desire
                    params.height = 50 // Set the height you desire
                    senderTimeTextView.layoutParams = params
                    senderTimeTextView.setPadding(10, 10, 10, 10) //
                    starTextView.background = null
                }
                isClicked = !isClicked
            }

            linearLayout.addView(newLayout)
        }
    }
}
