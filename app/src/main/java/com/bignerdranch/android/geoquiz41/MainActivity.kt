package com.bignerdranch.android.geoquiz41

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEAT = 0
class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var cheatButton: Button

    private val quizViewModel: QuizViewModel by
    lazy { ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex


        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->  //НАЖАТИЕ TRUE BUTTON
// Что-то выполнить после нажатия
            checkAnswer(true)
           //falseButton.isEnabled = false
           // trueButton.isEnabled = false
            trueButton.setVisibility(View.INVISIBLE);
            falseButton.setVisibility(View.INVISIBLE);
            }

        falseButton.setOnClickListener { view: View ->  //НАЖАТИЕ FALSE BUTTON
// Что-то выполнить после нажатия
            checkAnswer(false);
           // falseButton.isEnabled = false;
            //trueButton.isEnabled = false;
            falseButton.setVisibility(View.INVISIBLE);
            trueButton.setVisibility(View.INVISIBLE);
            }

        nextButton.setOnClickListener {     //НАЖАТИЕ NEXT BUTTON
            quizViewModel.moveToNext()
            trueButton.setVisibility(View.VISIBLE);
            falseButton.setVisibility(View.VISIBLE);
            if(quizViewModel.currentIndex==5)
            {
                nextButton.isEnabled = false
                //nextButton.setVisibility(View.INVISIBLE);
            }
            updateQuestion() }

        cheatButton.setOnClickListener {
        // Начало CheatActivity
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }

        updateQuestion()
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun
            onSaveInstanceState(savedInstanceState: Bundle)
    {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG,
            "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX,
            quizViewModel.currentIndex)
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

        private fun updateQuestion() {
            val questionTextResId = quizViewModel.currentQuestionText
            questionTextView.setText(questionTextResId)
        }

    var results = 0;
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer;
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else { R.string.incorrect_toast }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        if (userAnswer == correctAnswer)
        {
            results++
        }
        if(quizViewModel.currentIndex==5)
        {
            Toast.makeText(this, "Correct ansewers = " + results, Toast.LENGTH_SHORT).show()
        }



    }


        }



