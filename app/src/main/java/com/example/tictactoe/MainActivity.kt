package com.example.tictactoe


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

import java.util.*
import kotlin.collections.ArrayList

import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonList = arrayListOf<Button>(bu1, bu2, bu3, bu4, bu5, bu6, bu7, bu8, bu9)
        btn_restart.setOnClickListener {
            resetGameAndEnableButtons()
        }
    }

    fun buClick(view: View) {
        val buttonSelected = view as Button
        var cellID = 0
        when (buttonSelected.id) {
            R.id.bu1 -> cellID = 1
            R.id.bu2 -> cellID = 2
            R.id.bu3 -> cellID = 3
            R.id.bu4 -> cellID = 4
            R.id.bu5 -> cellID = 5
            R.id.bu6 -> cellID = 6
            R.id.bu7 -> cellID = 7
            R.id.bu8 -> cellID = 8
            R.id.bu9 -> cellID = 9
        }
        //Toast.makeText(this,"ID:$cellID", Toast.LENGTH_SHORT).show()
        GamePlay(cellID, buttonSelected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activeplayer = 1
    var winner = -1
    var hasGameEnded: Boolean = false; // keeps track if the game has ended. Game ends with eith X or O winning or a draw

    lateinit var buttonList: ArrayList<Button>

    fun resetGameAndEnableButtons() { // resetting the entire game
        player1 = ArrayList<Int>()
        player2 = ArrayList<Int>()
        activeplayer = 1
        winner = -1
        hasGameEnded = false
        for (aButton in buttonList) {
            aButton.isEnabled = true
            aButton.text = "".toString()
        }
    }

    fun disableAllButtons() { // disabling all the buttons
        for (aButton in buttonList) {
            aButton.isEnabled = false
        }
    }

    fun GamePlay(cellID: Int, buttonSelected: Button) {

        if (!hasGameEnded && cellID != -1) { // if the game has not ended and the game is also not a draw (if the game is draw, the value of cellID will be -1) then only we can play furthur rounds
            if (activeplayer == 1) {
                buttonSelected.text = "X"
                // buttonSelected.setBackgroundColor(R.color.yellow_ochre)
                player1.add(cellID)
                activeplayer = 2
                CheckWinner(player1, player2) // after the user's move we are checking if she/he has already won
                if(!hasGameEnded) { // if the game has not ended then AutoPlay can be performed
                    AutoPlay()
                }
            } else {
                buttonSelected.text = "O"
                //buttonSelected.setBackgroundColor(R.color.yellow_ochre)
                player2.add(cellID)
                activeplayer = 1
                CheckWinner(player1, player2)
            }
            buttonSelected.isEnabled = false
        } else { // if the game has ended as a draw
            disableAllButtons()
            if (winner == -1 && !hasGameEnded) { // if winner has not been decided as well as game has ended, then the game has ended in a draw
                Toast.makeText(this, "This is a draw", Toast.LENGTH_LONG).show()
            }
            hasGameEnded = true
        }
    }

    fun CheckWinner(player1: ArrayList<Int>, player2: ArrayList<Int>) {
        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row2
        else if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        } else if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row3
        else if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //column1
        else if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //column2
        else if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        } else if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //column3
        else if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        // primary diagonal
        else if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        // secondary diagonal
        else if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }
        if (winner != -1) {
            // if the winner is decided, then we can disable all the other buttons as well as mark the game as complete
            disableAllButtons()
            hasGameEnded = true
            if (winner == 1) {
                Toast.makeText(this, "Player 1 Wins the game", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Player 2 Wins the Game", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun AutoPlay() {

        var emptyCells = ArrayList<Int>()
        for (cellID in 1..9) {

            if (!(player1.contains(cellID) || player2.contains(cellID))) {
                emptyCells.add(cellID)
            }
        }
        var buSelect: Button = buttonList[0]
        if (emptyCells.size > 0) { // which means that there are still empty cell left
            val randIndex = Random1.nextInt(emptyCells.size - 0) + 0 // picking a random integer value in the range of 0, length of emptyCells
            val cellID = emptyCells[randIndex]

            when (cellID) {
                1 -> buSelect = buttonList[0]
                2 -> buSelect = buttonList[1]
                3 -> buSelect = buttonList[2]
                4 -> buSelect = buttonList[3]
                5 -> buSelect = buttonList[4]
                6 -> buSelect = buttonList[5]
                7 -> buSelect = buttonList[6]
                8 -> buSelect = buttonList[7]
                9 -> buSelect = buttonList[8]
                else -> {
                    buSelect = buttonList[0]
                }
            }
            GamePlay(cellID, buSelect)
        } else { // if there are no empty cell left and yet we do not have a winner suggests that the game ended in a draw
            GamePlay(-1, buSelect) // if there is a draw we still call GamePlay but now passing cellID as -1, which doesn't exist
        }
    }
}