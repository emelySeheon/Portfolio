#File Name: SeheonHW2
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: Provides user capability to calculate the diameter, circumference, surface area and volume of a sphere.

import math 

#Getting the radius from the user
radius=float(input("Enter Radius: "));

#Calculations
#Diameter=d, circumference=c, surface area=s, volume=v
d= radius*2;
c= math.pi*d;
s=(d*d)*math.pi;
v=(d*d*d)*math.pi*(1/6);

#Displaying results to user
print('Diameter: ', d);
print('Circumference: ', c);
print('Surface Area: ', s);
print('Volume: ', v);
