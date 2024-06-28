#Program: SeheonHW3
#Programmer: Emely Seheon
#Email: eseheon1@cnm.edu
#Purpose: To learn how to use strings in python.

string1 = "Sesquipedalian";
string2 = ''' All the world's a stage, and all the men and women merely players,
They have their exits and their entrances,
And one man in his time plays many parts'''

#Exercise 1
print(string1[7]);
print(string1[-2]);
print(string1[:11]);
print(string1[0:13:2]);
print(len(string1));

#Exercise 2
print(string2.count('and'));
print(string2.replace('all','none'));

a="A#l#l# #t#h#e# #w#o#r#l#d#'#s# #a# #s#t#a#g#e";
b="a#n#d# #a#l#l# #t#h#e# #m#e#n# #a#n#d# #w#o#m#e#n# #m#e#r#e#l#y# #p#l#a#y#e#r#s";
c="They have their exits and their entrances";
d="And one man in his time plays many parts";

#Exercise 3
print(''.join([a,b]));