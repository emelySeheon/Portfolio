
#Program: Seheon_FinalProject
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: To create a hangman game.

#readFile
def readFile():
    solutionFile = open("solution.txt");
    code = solutionFile.readline();
    return code

#checkInput
def checkInput (solutionList, userGuess):
    if userGuess in solutionList:
        indicies = []
        for i in range(len(solutionList)):
            if solutionList[i] == userGuess:
                indicies.append(i)
        return indicies
    else:
        return False

#correctInput
def correctInput (index, userGuess, correctList):
    correctList[index] = userGuess

#incorrectInput
def incorrectInput (userGuess, incorrectList, cyclopsDistance):
    incorrectList.append(userGuess)
    return cyclopsDistance-1

#displayProgress
def displayProgress (correctList, incorrectList, cyclopsDistance):
    print ("Correct Guesses: ", correctList, 
           "\nIncorrect Gusses: ", incorrectList, 
           "\nThe cyclops is ", cyclopsDistance, " steps away.\n")

#spliting the solution into an array of chars
def split (code):
    return [char.lower() for char in code]

#main function
print ("You are a treasure hunter. You have been searching for the long lost treasure of Jackson Kennedy.\n"
       "You’ve fought every obstacle in your path, and now there is just one thing standing between you and\n"
       "the treasure: a computer. You walk closer to the computer screen and you see what looks like a set\n"
       "of instructions. It says: “Congrats! You’ve reached the treasure. Now, all that stands between you\n"
       "and riches is a game of hangman. Two words, both five letters long. There is a blind cyclops 6 steps\n"
       "to your left. Every time you guess a letter wrong this computer will make a loud noise and the cyclops\n"
       "will take a step towards you. You don’t want to know what happens when he reaches you. Enjoy!”\n\n")

#Creating the solution list
code = readFile()
SolutionList = []
SolutionList.extend(split(code))

#creating the correct guess list
CorrectGuess = []
for char in SolutionList:
    if char == ' ':
        CorrectGuess.extend(' ')
    else:
        CorrectGuess.extend('.')
print (CorrectGuess)

#creating the incorrect guess list
IncorrectGuess = []

goAgain = 'y';
while (goAgain == 'y'):

    goAgain = 'n'
    cyclopsDistance = 6

    while (cyclopsDistance > 0):
        index = []
        userGuess = input("What is your guess? ")
        check = checkInput(SolutionList, userGuess)
        #If guess is wrong
        if check == False:
            cyclopsDistance = incorrectInput(userGuess, IncorrectGuess, cyclopsDistance)
            displayProgress(CorrectGuess, IncorrectGuess, cyclopsDistance)
        #If guess is right
        else:
            indicies = checkInput(SolutionList, userGuess)
            for i in range(len(indicies)):
                correctInput(indicies[i], userGuess, CorrectGuess)
            displayProgress(CorrectGuess, IncorrectGuess, cyclopsDistance)

            #If user won
        if (CorrectGuess == SolutionList):
            break

    #Losing story
    if cyclopsDistance == 0:
        print ("\nThe cyclops holds out his hand to feel in front of him and grabs your head. He says, \"Friend?\""
               "\nYou look up at him, confused. You ask, \"Do you have no friends?\" He responds, \"Only you\""
               "\nHe smiles and you look back at the computer screen. It says \"The true treasure is a true friendship\""
               "\nYou shake the cyclops' hand and say, \"Friends.\" And you lived happily ever after.")
        goAgain = input("\nWould you like to try again? (y/n) ")

    #Winner story
    else:
        print ("\nA door opens in front of you. You walk in the room and see a wall with writing on it. It says: \"LOL"
               "\nyou just got pranked. You really think I would give up some of my riches for a stranger? You silly"
               "\nfool. Now, I realize you might be angry, but you may notice that to your right is a sword. I've"
               "\nalways loved a good sword fight. If you want to kill me, all you have to is find me. Then you can"
               "\nhave all my riches. Signed, Jackson Kennedy.\" You grab the sword and get ready to embark on a new adventure.")