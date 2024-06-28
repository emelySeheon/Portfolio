
#Program: SeheonHW4
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: To practice lists and tuples

coffee_types=['Cappuccino',['Wet', 'Dry'],'Latte',['Peppermint','Cinnamon'],'Iced',['Mocha','Coconut Milk'],'Frappuccino',['Vanilla','Caramel']];


#Exercise 1
print(coffee_types);
choice = input ("Enter Coffee Order: ");
print ("Coffee in list?", choice in coffee_types);
position = coffee_types.index(choice);
options = position + 2 ;
print(coffee_types[options-1:options]);

#Exercise 2
coffee_types.extend(['Bubble Coffee',['Clear Boba','Popping Boba']]);
print(coffee_types);

#Exercise 3
coffee_types.remove('Cappuccino');
coffee_types.remove(['Wet', 'Dry']);
print(coffee_types);