
#Program: SeheonHW6
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: Provides user capability to find the number of fruits available in the inventory.

#Displaying options
fruit = dict( Apple=85, Pear= 42, Peach= 36, Tomato= 49, Nectarine= 63, Mango= 15, Orange=27)
for key in sorted (fruit):
    print (key)

#Getting user input
choice = input("\nPick a fruit: ")
print ("Quantity: ", fruit.get(choice))

#Setting a quantity to 0
fruit['Peach']=0

#Removing a fruit
fruit.pop('Apple');

#Displaying new Dictionary
print("\n")
for key in sorted (fruit):
    print (key)