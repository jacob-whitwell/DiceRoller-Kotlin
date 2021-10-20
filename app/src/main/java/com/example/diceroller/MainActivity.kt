package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows a user to roll a dice and view
 * the result on the screen.
 */
class MainActivity : AppCompatActivity() {

    /** Setting this in the setOnClickListener
     * was resetting the random number every
     * click of the roll. We need the luckyNumber
     * to be one number for the whole game.
     */

    private val luckyNumber = (1..6).random()
    private val luckyHint = "Lucky number is ${luckyNumber.toString()}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /** Find the roll button id to use with setOnClickListener */
        val rollButton: Button = findViewById(R.id.roll1)
        rollButton.setOnClickListener {
            rollDice()
        }

        rollDice()

        /** Create the hint functionality */
        val toast = Toast.makeText(applicationContext, luckyHint, Toast.LENGTH_SHORT)
        val hintButton: Button = findViewById(R.id.hintButton)

        /** Cancelling the toast at the start of the listener
         * stops the toast being unreliable
         */
        hintButton.setOnClickListener {
            toast.cancel()
            toast.show()
        }
    }

    /** Roll the dice and update the screen with the result */
    private fun rollDice() {


        /**
         * Create a new Dice object with 6 sides and roll it
         */
        val dice = Dice(6)
        val diceTwo = Dice(6)
        val diceOneRoll = dice.roll()
        val diceTwoRoll = diceTwo.roll()


        /** Set both of the dice images to be used */
        val diceOneImage: ImageView = findViewById(R.id.diceOneImage)
        val diceTwoImage: ImageView = findViewById(R.id.diceTwoImage)


        /**
         * Using 'when' statement here must return a value, and as such we have to add an
         * else statement at the end. In essence, it's a concise form of 'if-else'
         * Note: Using this is a more concise way of using the 'when' statement.
         * Previous code looked like this:
         * 6 -> diceImage.setImageResource(R.drawable.dice_6)
         */
        val drawableDiceOne = when (diceOneRoll)
        {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val drawableDiceTwo = when (diceTwoRoll)
        {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceOneImage.setImageResource(drawableDiceOne)
        diceTwoImage.setImageResource(drawableDiceTwo)

        // Update the screen with the roll
        val resultSuccess: TextView = findViewById(R.id.success)

        if (diceOneRoll == luckyNumber || diceTwoRoll == luckyNumber)
        {
            resultSuccess.text = getString(R.string.luckyNumber)
        } else
        {
            resultSuccess.text = ""
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }

}