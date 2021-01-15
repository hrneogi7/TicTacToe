package com.example.tictactoe




import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import java.util.*
import kotlin.collections.ArrayList

import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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


    fun GamePlay(cellID: Int, buttonSelected: Button) {

        if (activeplayer == 1) {
            buttonSelected.text = "X"
            // buttonSelected.setBackgroundColor(R.color.yellow_ochre)
            player1.add(cellID)
            activeplayer = 2
            AutoPlay()
        } else {
            buttonSelected.text = "O"
            //buttonSelected.setBackgroundColor(R.color.yellow_ochre)
            player2.add(cellID)
            activeplayer = 1
        }
        buttonSelected.isEnabled = false

        CheckWinner(player1, player2)
    }

    fun CheckWinner(player1: ArrayList<Int>, player2: ArrayList<Int>) {

        var winner = -1

        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //column1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //column2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //column3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        if (winner != -1) {
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

        val randIndex = Random1.nextInt(emptyCells.size - 0) + 0
        val cellID = emptyCells[randIndex]

        var buSelect: Button?

        when (cellID) {
            1 -> buSelect = findViewById<Button>(R.id.bu1)
            2 -> buSelect = findViewById<Button>(R.id.bu2)
            3 -> buSelect = findViewById<Button>(R.id.bu3)
            4 -> buSelect = findViewById<Button>(R.id.bu4)
            5 -> buSelect = findViewById<Button>(R.id.bu5)
            6 -> buSelect = findViewById<Button>(R.id.bu6)
            7 -> buSelect = findViewById<Button>(R.id.bu7)
            8 -> buSelect = findViewById<Button>(R.id.bu8)
            9 -> buSelect = findViewById<Button>(R.id.bu9)
            else -> {
                buSelect = findViewById<Button>(R.id.bu1)
            }
        }

        GamePlay(cellID, buSelect)


    }
}