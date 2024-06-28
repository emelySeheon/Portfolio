
#Program: SeheonHW5
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: Provides users a choice of games

import webbrowser;

response = "y"

while (response == "y"):
    #Getting user input
    response = input ("Would you like to play a game (y/n)? ")
    #Displaying game options to user
    if response == "y":
        print("Chess")
        print("Tik Tac Toe")
        print("Tetris")
        print("Count")
        #Getting user input
        game = input ("Choose a game: ")
        #Playing the game
        #Chess
        if "chess" in game:
            webbrowser.open_new("http://www.pygame.org/tags/chess")
        #Tic Tac Toe
        elif "tik tac toe" in game:
            webbrowser.open_new("http://www.pygame.org/tags/tictactoe")
        #Tetris
        elif "tetris" in game:
            webbrowser.open_new("http://www.pygame.org/tags/tetris")
        #count
        elif "count" in game:
            maxNumber = int(input ("How high? "))
            num=0;
            while num <= maxNumber:
                print (num)
                num=num+1
        #Error
        else:
            print ("I did not understand that!")