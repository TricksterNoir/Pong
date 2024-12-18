# Pong, Ping Pong

This is a simple Pong game written in Java. The game features a classic Pong gameplay where two paddles (one controlled by the player and the other controlled by AI) try to keep the ball in play and score points.

## Features
- **Difficulty Selection**: Choose between three difficulty levels (Easy, Medium, Hard) which control the speed of the AI paddle.
- **AI Controller**: The AI paddle moves based on the ball's position, with its speed adjustable through difficulty settings.
- **End Game Screen**: Displays the final message and allows the user to press the spacebar to return to the main menu.

## Requirements
- Java 8 or later.

## Setup
1. Clone the repository or download the source code.
2. Compile the Java files using your preferred IDE or from the command line:
   ```bash
   javac *.java
   ```
3. Run the game:
    ```bash
    java main
    ```

## Game Controls
- Mouse: Use the mouse to navigate through the difficulty selection screen.
- Keyboard: Press the Spacebar to restart the game from the end screen.

## Code Structure
- **Main.java:** Main entry point of the game.
- **SelectDifficulty.java:** Handles the difficulty selection screen.
- **EndGameScreen.java:** Displays the end game screen with a message and an option to restart.
- **Rect.java:** Represents the paddles and ball in the game.
- **Text.java:** Used to render text on the screen.
- **AiController.java:** Controls the AI paddle's movement.
- **KL.java:** KeyListener for handling keyboard inputs.
- **ML.java:** MouseListener for handling mouse inputs.
- **Constants.java:** Contains constant values for the game, such as screen size, paddle speed, and difficulty settings.
- **Time.java:** Utility class for tracking time.

## License
This project is licensed under the MIT License
  ```bash
  Feel free to adjust any section to fit the specific needs of your project.
  ```

## Author
This game was created by **Sylvio Labriola**.

Feel free to contact me for any questions or suggestions.
