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
| Splash Screen | Main Menu | Game Screen |
|--------------|----------|------------|
| <img src="screenshots/splash.jpg" width=200> | <img src="screenshots/menu.jpg" width=200> | <img src="screenshots/game.jpg" width=200> |

## Installation
1. Clone this repository
2. Open in Android Studio
3. Build and run on emulator/device
