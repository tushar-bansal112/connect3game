package com.example.connect3game

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

import android.widget.Button
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

     var gamestate = intArrayOf(2,2,2,2,2,2,2,2,2)
     var winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )
     var x: Int = 0
     var y: Boolean = true
    fun dropin(view: View) {
        val counter = view as ImageView
        val tappedcounter = counter.tag.toString().toInt()
        if (gamestate[tappedcounter] == 2 && y) {
            gamestate[tappedcounter] = x
            counter.translationY = -1500f
            x = if (x == 0) {
                counter.setImageResource(R.drawable.zero)
                1
            } else {
                counter.setImageResource(R.drawable.cross)
                0
            }
            counter.animate().translationYBy(1500f).rotation(3600f).duration = 300
            for (winningPosition in winningPositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[0]] != 2) {
                    y = false
                    val winner: String = if (x == 1) {
                        "Zero"
                    } else {
                        "Cross"
                    }
                    val playAgainButton = findViewById<View>(R.id.playagainbutton) as Button
                    val winnerTextView = findViewById<View>(R.id.winnerText) as TextView
                    winnerTextView.text = "$winner has won!"
                    playAgainButton.visibility = View.VISIBLE
                    winnerTextView.visibility = View.VISIBLE

                }
            }
        }
    }
    fun playAgain(view: View?){
        val playAgainButton = findViewById<View>(R.id.playagainbutton) as Button
        val winnerTextView = findViewById<View>(R.id.winnerText) as TextView
        playAgainButton.visibility = View.INVISIBLE
        winnerTextView.visibility = View.INVISIBLE
        val gridLayout = findViewById<View>(R.id.layout) as GridLayout
        for (i in 0 until gridLayout.childCount) {
            val counter = gridLayout.getChildAt(i) as ImageView
            counter.setImageDrawable(null)
        }
        for (i in gamestate.indices) {
            gamestate[i] = 2
        }
        x = 0
        y = true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}