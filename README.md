# Chess <img src=https://raw.githubusercontent.com/tristankitto/Chess/main/src/chessicon.png height = "50" align = "center">

## Chess made in java! by Tristan Kitto

A personal project of mine combining 2 of my hobbies, coding and chess.

## Features

- Play chess locally with friends
- Support for standard chess rules including castling and en passant
- Visual representation of the chess board using Java Swing library
- Mouse inputs for making moves

## Getting Started

1. Clone the repository:
```shell
git clone https://github.com/tristankitto/chess.git
```

2. Navigate to the directory and compile the Java source code:
```shell
javac *.java
```

3. Run the game:
```shell
java Chess.java
```

## How to Play

1. The game starts with a standard chess board displayed in a new window.

2. Each player takes turn moving a piece, with the white pieces moving first.

3. Moves are made by clicking and dragging a piece to the desired square. Invalid moves will result in the piece moving back to where it started.

4. Checkmate occurs when a player's king is in a position to be captured (in "check") and there is no legal move that can prevent its capture, resulting in the end of the game.

## Planned Features
- Promoting pawns when they reach the back rank.
- Clicking on a piece to show all valid moves.
- Confirming when a king has been checked or checkmated and by extension ensuring no illegal moves can be made.
- Undo/Redo moves.

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## License
This project is licensed under the [CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/) license
