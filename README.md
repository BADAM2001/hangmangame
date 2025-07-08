# Don't Hang Me! - Android Hangman Game

A classic word-guessing game where players must guess letters to reveal a hidden word before the hangman is fully drawn.

## Features
- 🎮 **Two Input Methods**: Type letters or use voice commands
- 💀 **Visual Hangman**: Body parts appear with each wrong guess
- 📖 **Word Dictionary**: Random words from categories (fruits, tech, etc.)
- 🏆 **Win/Lose Conditions**: 
  - Win: Guess all letters correctly
  - Lose: 6 wrong guesses complete the hangman
- 🔄 **Auto-Restart**: Returns to menu after game ends

## Game Flow
1. Splash Screen (3 sec) → Main Menu
2. Play → Game Screen:
   - Guess letters via keyboard/voice
   - Watch hangman progress
3. Win/Lose → Auto-return to menu

## Technical Details
- **Language**: Java
- **Components**:
  - Activities: Splash, Menu, Game
  - Vector drawables for hangman parts
  - Android SpeechRecognizer API
- **Minimum SDK**: 21 (Android 5.0+)

## Screenshots
### 1. Splash Screen
<img src="https://github.com/BADAM2001/hangmangame/raw/e26261fea477e045f59e42fafa05d6e9965629b1/splash.jpg" width="250">

### 2. Main Menu
<img src="https://github.com/BADAM2001/hangmangame/raw/e26261fea477e045f59e42fafa05d6e9965629b1/main.jpg" width="250">

### 3. Game Screen
<img src="https://github.com/BADAM2001/hangmangame/raw/e26261fea477e045f59e42fafa05d6e9965629b1/game.jpg" width="250">

### 4. Voice Input Feature
<img src="https://github.com/BADAM2001/hangmangame/raw/e26261fea477e045f59e42fafa05d6e9965629b1/voice%20input.jpg" width="250">

### 5. Game Completion (Win/Lose)
<img src="https://github.com/BADAM2001/hangmangame/raw/e26261fea477e045f59e42fafa05d6e9965629b1/last%20section.jpg" width="250">

## Installation
1. Clone this repository
2. Open in Android Studio
3. Build and run on emulator/device
