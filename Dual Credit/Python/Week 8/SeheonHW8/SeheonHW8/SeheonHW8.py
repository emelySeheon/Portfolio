
#Program: SeheonHW8
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: To add exceptions to the calculations made in HW7

#Greeting Function

print("Hello!")

def greet ():
    print("\nMiles to Kilometers")
    print("Yards to Meters")
    print("Inches to Centimeters")
    print("Fahrenheit to Celsius")
    print("Pounds to Kilograms")
    choice=input("Please choose an option of what to would like to convert : ")
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
    choice = choice.lower()

    #printing the answer based on the choice (if elif)
    if (choice == "miles to kilometers") :
        try:
            initial = input("What number would you like to convert? ")
            imperial = float(initial)
            m=miles(imperial)
            print(m)
        except ValueError:
            print ("Please enter a valid number.")
            go_again=input ("\nWould you like to go again? (yes/no) ")

    elif (choice =="yards to meters"):
        try:
            initial = input("What number would you like to convert? ")
            imperial = float(initial)
            y=yard(imperial)
            print(y)
        except ValueError:
            print ("Please enter a valid number.")
            go_again=input ("\nWould you like to go again? (yes/no) ")

    elif (choice =="inches to centimeters"):
        try:
            initial = input("What number would you like to convert? ")
            imperial = float(initial)
            i=inch(imperial)
            print(i)
        except ValueError:
            print ("Please enter a valid number.")
            go_again=input ("\nWould you like to go again? (yes/no) ")

    elif (choice =="fahrenheit to celsius"):
        try:
            initial = input("What number would you like to convert? ")
            imperial = float(initial)
            f=fahrenheit(imperial)
            print(f)
        except ValueError:
            print ("Please enter a valid number.")
            go_again=input ("\nWould you like to go again? (yes/no) ")

    elif (choice =="pounds to kilograms"):
        try:
            initial = input("What number would you like to convert? ")
            imperial = float(initial)
            p=pound(imperial)
            print(p)
        except ValueError:
            print ("Please enter a valid number.")
            go_again=input ("\nWould you like to go again? (yes/no) ")

    else:
        print("Error: Input is not an option!")
        go_again=input ("\nWould you like tco go again? (yes/no) ")