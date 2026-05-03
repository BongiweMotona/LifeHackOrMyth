package com.example.lifehackormyth

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {

    private val questions = listOf(
        Question("You can only use 10% of your brain", false, "Brain scans show we use virtually all of our brain."),
        Question("Placing a phone in rice fixes water damage", false, "Rice is actually ineffective. Silica gel works better."),
        Question("Bananas can improve your mood", true, "Bananas contain tryptophan which helps produce serotonin."),
        Question("Drinking 8 glasses of water a day is a must.", false, "Water intake vary per person."),
        Question("Yawning is contagious even when reading about it", true, "Studies show just reading or thinking about yawning triggers it."),
        Question("Sleeping after leaning helps memory retention", true, "Sleep consolidates memories ad improves recall.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        val tvReview = findViewById<TextView>(R.id.tvReview)

        val reviewText = StringBuilder()

        reviewText.append("Review All Questions;\n\n")

        for (question in questions) {
            val answer = if (question.isHack) "HACK" else "MYTH"
            reviewText.append("${question.statement}\n")
            reviewText.append("Answer: $answer\n")
            reviewText.append("${question.explanation}\n\n")
        }

        Log.d("ReviewActivity", "Review screen loaded")

        tvReview.text = reviewText.toString()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}