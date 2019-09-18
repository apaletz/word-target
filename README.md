Class files included so you don't even have to compile.
Just download, and run with: java WordTarget

___________________________________________________

Word Target is a simple game that runs on the command line.
it produces a grid of letter in the format:

A|B|C
-----
D|E|F
-----
G|H|I
-----


The grid is generated from a random 9-letter word pulled from words.txt found at: https://github.com/dwyl/english-words
An ArrayList containing all word of length 9 is created, then a radon number between one and the length of this ArrayList is generated to pick a word.
A HashSet is created from the entire list of words, to verify user input.
The goal is to make words from the letters in the grid. The words must be at least four characters long
and contain the middle letter.
The program uses various tests to check if the the word is valid. These are:
Confirm it is a real word.
Confirm is is at least four characters long.
Confirm it does not uses a letter more frequently than it appears on the grid.
Confirm it does not contain letters not on the grid
Confirm it Contains the middle letter.
Confirm it has not already been submitted (user answers are added to another list and can be viewed by the user.)

A message is then generated depending on the validity of the word:
If the word is invalid, the user will be notified why.
If the word is valid a congratulatory message is printed.

The grid will be reprinted based on the number of lines of input/output that occur during play, so that the user should always have a visible grid.

To start a new game, the user types "new" and then "yes" to confirm, or anything else to resume the current game.
To quit the user enters "*".
