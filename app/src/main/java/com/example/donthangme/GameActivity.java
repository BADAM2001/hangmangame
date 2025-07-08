package com.example.donthangme;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    private static final int VOICE_INPUT_REQUEST_CODE = 100;
    private HangmanGameLogic gameLogic;
    private FrameLayout hangmanDrawing;
    private TextView tvWordDisplay, tvIncorrectLetters, tvGameStatus;
    private EditText etLetterInput;
    private int[] hangmanParts = {
            R.drawable.hangman_head,
            R.drawable.hangman_body,
            R.drawable.hangman_left_arm,
            R.drawable.hangman_right_arm,
            R.drawable.hangman_left_leg,
            R.drawable.hangman_right_leg
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialize views
        hangmanDrawing = findViewById(R.id.hangman_drawing);
        tvWordDisplay = findViewById(R.id.tv_word_display);
        tvIncorrectLetters = findViewById(R.id.tv_incorrect_letters);
        tvGameStatus = findViewById(R.id.tv_game_status);
        etLetterInput = findViewById(R.id.et_letter_input);
        Button btnSubmit = findViewById(R.id.btn_submit);
        Button btnVoiceInput = findViewById(R.id.btn_voice_input);

        // Initialize game
        String[] wordDictionary = getResources().getStringArray(R.array.game_words);
        gameLogic = new HangmanGameLogic(wordDictionary);
        updateGameUI();

        // Submit button click
        btnSubmit.setOnClickListener(v -> {
            String input = etLetterInput.getText().toString().toUpperCase();
            if (!input.isEmpty() && Character.isLetter(input.charAt(0))) {
                processGuess(input.charAt(0));
                etLetterInput.setText("");
            }
        });

        // Voice input button
        btnVoiceInput.setOnClickListener(v -> startVoiceInput());
    }

    private void processGuess(char letter) {
        if (gameLogic.isGameOver()) return;

        boolean correct = gameLogic.processGuess(letter);
        updateGameUI();

        if (gameLogic.isGameWon()) {
            endGame(true);
        } else if (gameLogic.isGameLost()) {
            endGame(false);
        }
    }

    private void updateGameUI() {
        // Update word display
        tvWordDisplay.setText(getSpacedWord(gameLogic.getDisplayedWord()));

        // Update incorrect letters
        tvIncorrectLetters.setText("Wrong letters: " + gameLogic.getIncorrectLetters());

        // Update hangman drawing
        updateHangmanDrawing();
    }

    private String getSpacedWord(String word) {
        return word.replace("", " ").trim();
    }
    private void updateHangmanDrawing() {
        hangmanDrawing.removeAllViews();

        // 1. Add Gallows (fixed position)
        ImageView gallows = new ImageView(this);
        gallows.setImageResource(R.drawable.hangman_gallows);
        gallows.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));
        hangmanDrawing.addView(gallows);

        // 2. Calculate base positions (all values in dp)
        int headTopMargin = 35;  // Below rope
        int headSize = 60;
        int bodyHeight = 80;
        int armWidth = 60;
        int legHeight = 60;

        // 3. Add Parts Sequentially
        int wrongAttempts = gameLogic.getWrongAttempts();
        FrameLayout.LayoutParams params;

        if (wrongAttempts >= 1) { // HEAD (centered below rope)
            ImageView head = new ImageView(this);
            head.setImageResource(R.drawable.hangman_head);
            params = new FrameLayout.LayoutParams(dpToPx(headSize), dpToPx(headSize));
            params.setMargins(dpToPx(70), dpToPx(headTopMargin), 0, 0);
            head.setLayoutParams(params);
            hangmanDrawing.addView(head);
        }

        if (wrongAttempts >= 2) { // BODY (below head)
            ImageView body = new ImageView(this);
            body.setImageResource(R.drawable.hangman_body);
            params = new FrameLayout.LayoutParams(dpToPx(24), dpToPx(bodyHeight));
            params.setMargins(dpToPx(78), dpToPx(headTopMargin + headSize), 0, 0);
            body.setLayoutParams(params);
            hangmanDrawing.addView(body);
        }

        if (wrongAttempts >= 3) { // LEFT ARM (attached to upper body)
            ImageView leftArm = new ImageView(this);
            leftArm.setImageResource(R.drawable.hangman_left_arm);
            params = new FrameLayout.LayoutParams(dpToPx(armWidth), dpToPx(30));
            params.setMargins(dpToPx(78 - armWidth/2), dpToPx(headTopMargin + headSize + 10), 0, 0);
            leftArm.setLayoutParams(params);
            hangmanDrawing.addView(leftArm);
        }

        if (wrongAttempts >= 4) { // RIGHT ARM (mirror of left)
            ImageView rightArm = new ImageView(this);
            rightArm.setImageResource(R.drawable.hangman_right_arm);
            params = new FrameLayout.LayoutParams(dpToPx(armWidth), dpToPx(30));
            params.setMargins(dpToPx(78 + 12), dpToPx(headTopMargin + headSize + 10), 0, 0);
            rightArm.setLayoutParams(params);
            hangmanDrawing.addView(rightArm);
        }

        if (wrongAttempts >= 5) { // LEFT LEG (attached to lower body)
            ImageView leftLeg = new ImageView(this);
            leftLeg.setImageResource(R.drawable.hangman_left_leg);
            params = new FrameLayout.LayoutParams(dpToPx(30), dpToPx(legHeight));
            params.setMargins(dpToPx(78), dpToPx(headTopMargin + headSize + bodyHeight), 0, 0);
            leftLeg.setLayoutParams(params);
            hangmanDrawing.addView(leftLeg);
        }

        if (wrongAttempts >= 6) { // RIGHT LEG
            ImageView rightLeg = new ImageView(this);
            rightLeg.setImageResource(R.drawable.hangman_right_leg);
            params = new FrameLayout.LayoutParams(dpToPx(30), dpToPx(legHeight));
            params.setMargins(dpToPx(78 + 12), dpToPx(headTopMargin + headSize + bodyHeight), 0, 0);
            rightLeg.setLayoutParams(params);
            hangmanDrawing.addView(rightLeg);
        }
    }

    // Helper to convert dp to pixels
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a letter");
        startActivityForResult(intent, VOICE_INPUT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_INPUT_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && !results.isEmpty()) {
                String spokenText = results.get(0).toUpperCase();
                if (!spokenText.isEmpty() && Character.isLetter(spokenText.charAt(0))) {
                    processGuess(spokenText.charAt(0));
                }
            }
        }
    }

    private void endGame(boolean won) {
        String message = won ? "You won! The word was " + gameLogic.getCurrentWord()
                : "You lost! The word was " + gameLogic.getCurrentWord();

        tvGameStatus.setText(message);
        tvGameStatus.setVisibility(View.VISIBLE);

        // Disable input
        findViewById(R.id.et_letter_input).setEnabled(false);
        findViewById(R.id.btn_submit).setEnabled(false);
        findViewById(R.id.btn_voice_input).setEnabled(false);

        // Return to main menu after delay
        new android.os.Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, 3000);
    }
}