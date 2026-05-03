package com.example.lifehackormyth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.concurrent.ThreadLocalRandom.current

data class Question( //Stores data for lists that have multiple pieces of information, so every question has a statement, a true/false answer, and an explanation.
    val statement: String,
    val isHack: Boolean,
    val explanation: String
)
class FlashcardActivity : AppCompatActivity() {

    private val questions = listOf(
        Question("Lemon juice can be used as a natural deodorant", isHack = true, "The acidity in lemon juice kills odour causing bacteria making it a natural deodorant"),
        Question("Shaving makes hair grow back thicker", false, "Shaving only cuts the hair at the surface, it does not affect the root or thickness"),
        Question("Bananas can improve your mood", true, "Bananas contain tryptophan which helps produce serotonin."),
        Question("Drinking 8 glasses of water a day is a must.", false, "Water intake vary per person."),
        Question("Putting a wooden spoon over a boiling pot stops it from boiling over", true, "The wooden spoon breaks the surface tension of the bubbles preventing overflow"),
        Question("Sleeping after learning helps memory retention", true, "Sleep consolidates memories ad improves recall.")
    )
    private var currentIndex = 0 //Tracks which question we are on
    private var score = 0 //Tracks correct answers and starts at 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flashcard)

        val hackButton = findViewById<Button>(R.id.hackButton)
        val mythButton = findViewById<Button>(R.id.mythButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)

        fun displayQuestion() {

            val current = questions[currentIndex] //Get the question at the current position in the list, so if currentIndex is 0, it gets the first question.

            val tvQuestion = findViewById<TextView>(R.id.tvQuestion) //Shows the question on the screen
            val tvFeedback = findViewById<TextView>(R.id.tvFeedback) //Clears the feedback from the previous question
            val tvCounter = findViewById<TextView>(R.id.tvCounter)

            tvQuestion.text = current.statement
            tvFeedback.text = ""
            tvCounter.text = "Question ${currentIndex + 1} of ${questions.size}"

            Log.d("FlashcardActivity", "Displaying question ${currentIndex + 1}: ${current.statement}")
        }

        displayQuestion()

        hackButton.setOnClickListener { //When hack button is clicked, do what's inside the curly brackets.
            val current = questions[currentIndex]
            if (current.isHack) {
                score++
                tvFeedback.text = "Correct! That's a real time-saver!"
            } else {
                tvFeedback.text = "Wrong! That's an urban myth."
            }
        }
        mythButton.setOnClickListener { //When myth button is clicked,do what's inside the curly brackets
            val current = questions[currentIndex]
            if (!current.isHack) {
                score++
                tvFeedback.text = "Correct! That's a real time-saver!"
            } else {
                tvFeedback.text = "Wrong! That's an urban myth."
            }
        }
        nextButton.setOnClickListener { //When next button is clicked, do what's inside the curly brackets
            currentIndex++ //Moves to the next question in the list
            if (currentIndex < questions.size) {
                displayQuestion()
            } else {
                Log.d("FlashcardActivity", "All questions answered. Final score: $score")
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score) //Sends the score to the Score screen
                intent.putExtra("TOTAL", questions.size)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}