package com.ourgdx.game.screens.testGame1;

import com.ourgdx.game.GameMain;
import com.ourgdx.game.text.Printout;

public class Player {
    // VARIABLES
    private int score;
    private String playerName;
    private Printout scoreText;

    // Constructor
    public Player(String name, int xCoord){
        score = 0;
        playerName = name;
        scoreText = new Printout("", xCoord, GameMain.SCREEN_HEIGHT - 25);
        scoreText.text = playerName + ": " + score;
    }

    // Getters and Setters
    public int getScore() {
        return score;
    }
    public String getPlayerName() {
        return playerName;
    }

    public Printout getScoreText() {
        return scoreText;
    }

    public void scoredPoint() {
        // Increment the score, and update the printout
        score++;
        scoreText.text = playerName + ": " + score;
    }

}
