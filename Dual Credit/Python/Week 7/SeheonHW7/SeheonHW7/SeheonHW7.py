
#Program: SeheonHW7
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: To convert imperial units to metric.

#Greeting Function
def greet ():
    print("Hello!")
    print("\nMiles to Kilometers")
    print("Yards to Meters")
    print("Inches to Centimeters")
    print("Fahrenheit to Celsius")
    print("Pounds to Kilograms")
    choice=input("Please choose an option of what to would like to convert (type all letters lowercase): ")
    return choice

#Miles to Kilometers Function
def miles (imperial):
    return imperial*1.609

#Yards to Meters
def yard (imperial):
    return imperial/1.094

#Inches to Centimeters
def inch (imperial):
    return imperial*2.54

#Fahrenheit to Calsius
def fahrenheit (imperial):
    return ((32*imperial)-32)*(5/9)

#Pounds to Kilograms
def pound (imperial):
    return imperial/2.205

#While Loop
go_again="yes"
while(go_again=="yes"):
    
    choice=greet()

    #printing the answer based on the choice (if elif)
    if (choice == "miles to kilometers") :
        initial = input("What number would you like to convert? ")
        imperial = float(initial)
        m=miles(imperial)
        print(m)
        go_again=input ("\n Would you like to go again? (yes/no) ")
        print("\n")

    elif (choice =="yards to meters"):
        initial = input("What number would you like to convert? ")
        imperial = float(initial)
        y=yard(imperial)
        print(y)
        go_again=input ("\n Would you like to go again? (yes/no) ")
        print("\n")

    elif (choice =="inches to centimeters"):
        initial = input("What number would you like to convert? ")
        imperial = float(initial)
        i=inch(imperial)
        print(i)
        go_again=input ("\n Would you like to go again? (yes/no) ")
        print("\n")

    elif (choice =="fahrenheit to celsius"):
        initial = input("What number would you like to convert? ")
        imperial = float(initial)
        f=fahrenheit(imperial)
        print(f)
        go_again=input ("\n Would you like to go again? (yes/no) ")
        print("\n")

    elif (choice =="pounds to kilograms"):
        initial = input("What number would you like to convert? ")
        imperial = float(initial)
        p=pound(imperial)
        print(p)
        go_again=input ("\n Would you like to go again? (yes/no) ")
        print("\n")

    else:
        print("Error: Input is not an option!")
        go_again=input ("\n Would you like to go again? (yes/no) ")
        print("\n")