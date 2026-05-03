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
        Question("Lemon juice can be used as a natural deodorant", isHack = true, "The acidity in lemon juice kills odour causing bacteria making it a natural deodorant"),
        Question("Shaving makes hair grow back thicker", false, "Shaving only cuts the hair at the surface, it does not affect the root or thickness"),
        Question("Bananas can improve your mood", true, "Bananas contain tryptophan which helps produce serotonin."),
        Question("Drinking 8 glasses of water a day is a must.", false, "Water intake vary per person."),
        Question("Putting a wooden spoon over a boiling pot stoops it from boiling over", true, "The wooden spoon breaks the surface tension of the bubbles preventing overflow"),
        Question("Sleeping after leaning helps memory retention", true, "Sleep consolidates memories ad improves recall.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        val tvReview = findViewById<TextView>(R.id.tvReview)

        val reviewText = StringBuilder()

        reviewText.append("Review All Questions;\n\n")

        for (question in questions) { //Show the questions with the correct answer and explanation
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