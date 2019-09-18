To run word target, download the repo, go to the folder in you terminal, and type: java WordTarget

Word Target is a simple game that runs in Terminal.
it produces a grid of letter in the following format:

A|B|C
-----
D|E|F
-----
G|H|I
-----


The grid is generated from a random 9-letter word pulled from words.txt found at: https://github.com/dwyl/english-words
The goal is to make words from the letters in the grid. The words must be at least four characters long
and contain the middle letter.
The program uses various tests to confirm if the the word is valid.
A message is then generated depending on the validity of the word:
If the word is invalid, the user will be notified why (not a word, doesn't use the middle letter, uses letters not on the grid)
If the word is valid a congragulatory message is printed ("good job")
The users entries are stored in an ArrayList.
To confirm the validity of the word in the english language, a HashSet is created from the words.txt file, against which all user entires are compared.
This takes place in the ReadFile class.
ReadFile also creates an ArrayList of all words in words.txt that are 9 characters long.
This is the list from which grids are generated when the player starts a new game.
The grid will be reprinted based on the number of lines of input/output that occur during play, so that the user should always have a visible grid.
To start a new game, the user types "new" and then "yes" to confirm, or anyything else to resume the current game.
To quit the user enters "*".
