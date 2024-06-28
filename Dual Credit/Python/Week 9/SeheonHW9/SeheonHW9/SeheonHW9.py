
#Program: SeheonH9
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: File Management

import csv

#Reading the file
with open('natparks_list.csv') as csvfile:
    file = csv.reader(csvfile)

    #Printing the header
    header = next(file)
    print(header)

    #Creating the empty list and putting in the header
    natParks=[]
    name = header.index("Name")
    date = header.index("Date_est")
    natParks.append([header[1],header[2]])

    #Adding the names and dates to the list
    for row in file:
        x = row[name]
        y= row[date]
        natParks.append([x,y])

    #Printing the list
    print("\n")
    i = 0
    while i < len(natParks):
        print(natParks[i])
        i+=1