package com.example.donthangme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HangmanGameLogic {
    private String currentWord;
    private char[] displayedWord;
    private List<Character> incorrectLetters;
    private int wrongAttempts;
    private static final int MAX_ATTEMPTS = 6;
    private boolean gameOver;

    public HangmanGameLogic(String[] wordDictionary) {
        Random random = new Random();
        String allWords = wordDictionary[random.nextInt(wordDictionary.length)];
        String[] words = allWords.split(" ");
        currentWord = words[random.nextInt(words.length)].toUpperCase();
        displayedWord = new char[currentWord.length()];
        Arrays.fill(displayedWord, '_');
        incorrectLetters = new ArrayList<>();
        wrongAttempts = 0;
        gameOver = false;
    }

    public boolean processGuess(char letter) {
        if (gameOver) return false;

        letter = Character.toUpperCase(letter);
        boolean correctGuess = false;

        // Check if letter was already guessed
        if (incorrectLetters.contains(letter) ||
                String.valueOf(displayedWord).contains(String.valueOf(letter))) {
            return false;
        }

        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == letter) {
                displayedWord[i] = letter;
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            incorrectLetters.add(letter);
            wrongAttempts++;
            if (wrongAttempts >= MAX_ATTEMPTS) {
                gameOver = true;
            }
        } else if (!String.valueOf(displayedWord).contains("_")) {
            gameOver = true;
        }

        return correctGuess;
    }

    public boolean isGameWon() {
        return !String.valueOf(displayedWord).contains("_");
    }

    public boolean isGameLost() {
        return wrongAttempts >= MAX_ATTEMPTS;
    }

    public boolean isGameOver() {
        return gameOver || isGameWon() || isGameLost();
    }

    public String getDisplayedWord() {
        return String.valueOf(displayedWord);
    }

    public String getIncorrectLetters() {
        return incorrectLetters.toString();
    }

    public int getWrongAttempts() {
        return wrongAttempts;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void resetGame(String[] wordDictionary) {
        Random random = new Random();
        String allWords = wordDictionary[random.nextInt(wordDictionary.length)];
        String[] words = allWords.split(" ");
        currentWord = words[random.nextInt(words.length)].toUpperCase();
        displayedWord = new char[currentWord.length()];
        Arrays.fill(displayedWord, '_');
        incorrectLetters.clear();
        wrongAttempts = 0;
        gameOver = false;
    }
}