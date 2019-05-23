Word Target is a simple game that runs in Terminal.
it produces a grid of letter in the following form:

A|B|C
-----
D|E|F
-----
G|H|I

The grid is generated from a random 9-letter word pulled from words.txt found at: https://github.com/dwyl/english-words
The goal is to make words from the letters in the grid. The words must be at least four characters long
and contain the middle letter.
The program uses various tests to confirm if the the word is valid.
A message is then generated depending on the validity of the word:
If the word is invalid, the user will be notified why(eg not a word, doesn't use the middle letter, uses letters not on grid)
If the word is valid a congragulatory message is printed("good job")
The users entries are stored in an ArrayList
to confirm the existence of the word in words.txt, a HashSet is created to store the entries of words.txt
This takes place in the ReadFile class.
ReadFile also creates an ArrayList of all words in words.txt that are 9 characters long.
This is the list from which grids are generated when the player starts a new game.
