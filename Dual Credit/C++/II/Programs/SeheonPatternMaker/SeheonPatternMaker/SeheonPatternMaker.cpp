//Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Date: 11/24/20
//Program: SeheonPatternMaker is a program to make a game board for the Game of Life

#include <iostream>
#include <fstream>
using namespace std;

int main()
{
    //Butterfly

    //Making the array
    string butterlyFilename = "butterfly.txt";
    string butterfly[45][78];

    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            butterfly[i][j] = '.';
        }
    }

    //Filling up the array
    int a = 17;
    int b = 30;
    while (a < 29) {
        butterfly[a][b] = "X";
        butterfly[a][b + 14] = "X";
        ++a;
    }

    a = 29;
    b = 31;
    int c = 12;
    int d = 13;
    while (a < 32 && b < 34) {
        butterfly[a][b] = "X"; 
        butterfly[a][b + c] = "X";
        butterfly[a - d][b] = "X";
        butterfly[a - d][b + c] = "X";
        ++a;
        ++b;
        c = c - 2;
        d = d + 2;
    }

    a = 30;
    b = 34;
    c = 6;
    d = 15;
    while (a > 26 && b < 38) {
        butterfly[a][b] = "X";
        butterfly[a][b + c] = "X";
        butterfly[a - d][b] = "X";
        butterfly[a - d][b + c] = "X";
        --a;
        ++b;
        c = c - 2;
        d = d - 2;
    }

    butterfly[19][37] = "X";
    butterfly[18][37] = "X";
    butterfly[25][37] = "X";
    butterfly[26][37] = "X";


    //Writing to the file
    ofstream butterflyFile;
    butterflyFile.open(butterlyFilename);
    butterflyFile << "45 78" << endl;
    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            butterflyFile << butterfly[i][j];
        }
        butterflyFile << endl;
    }
    butterflyFile.close();



    //Fish
    
    //Making the array
    string fishFilename = "fish.txt";
    string fish[45][78];

    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            fish[i][j] = '.';
        }
    }

    //Filling up the array
    a = 27;
    b = 35;
    while (b < 46) {
        fish[a][b] = "X";
        ++b;
    }

    a = 18;
    b = 35;
    while (a < 28) {
        fish[a][b] = "X";
        ++a;
    }

    //Writing to the file
    ofstream fishFile;
    fishFile.open(fishFilename);
    fishFile << "45 78" << endl;
    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            fishFile << fish[i][j];
        }
        fishFile << endl;
    }
    fishFile.close();



    //Face

    //Making the array
    string faceFilename = "face.txt";
    string face[45][78];

    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            face[i][j] = '.';
        }
    }

    //Filling up the array
    a = 20;
    b = 32;
    c = 4;
    d = 12;
    while (a < 23) {
        face[a][b] = "X";
        face[a][b + d] = "X";
        face[a + c][b] = "X";
        face[a + c][b + d] = "X";
        a++;
    }

    a = 21;
    b = 35;
    c = 4;
    while (b < 38) {
        face[a][b] = "X";
        face[a][b + c] = "X";
        face[a + c][b] = "X";
        face[a + c][b + c] = "X";
        b++;
    }

    face[23][31] = "X";
    face[23][33] = "X";
    face[23][43] = "X";
    face[23][45] = "X";
    face[20][38] = "X";
    face[22][38] = "X";
    face[24][38] = "X";
    face[26][38] = "X";

    //Writing to the file
    ofstream faceFile;
    faceFile.open(faceFilename);
    faceFile << "45 78" << endl;
    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            faceFile << face[i][j];
        }
        faceFile << endl;
    }
    faceFile.close();



    //Smile

    //Making the array
    string smileFilename = "smile.txt";
    string smile[45][78];

    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            smile[i][j] = '.';
        }
    }

    //Filling up the array
    a = 21;
    b = 35;
    c = 6;
    while (a < 25 && b < 39) {
        smile[a][b] = "X";
        smile[a][b + c] = "X";
        a++;
        b++;
        c = c - 2;
    }

    a = 20;
    b = 38;
    c = 3;
    while (a < 22) {
        smile[a][b] = "X";
        smile[a + c][b] = "X";
        a++;
    }

    smile[19][37] = "X";
    smile[19][39] = "X";

    //Writing to the file
    ofstream smileFile;
    smileFile.open(smileFilename);
    smileFile << "45 78" << endl;
    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            smileFile << smile[i][j];
        }
        smileFile << endl;
    }
    smileFile.close();



    //Ice Cream

    //Making the array
    string icecreamFilename = "icecream.txt";
    string icecream[45][78];

    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            icecream[i][j] = '.';
        }
    }

    //Filling up the array
    a = 21;
    b = 35;
    while (b < 42) {
        icecream[a][b] = "X";
        ++b;
    }

    a = 23;
    b = 36;
    c = 4;
    while (a < 27) {
        icecream[a][b] = "X";
        icecream[a][b + c] = "X";
        ++a;
    }

    a = 20;
    b = 35;
    c = 6;
    while (a > 16 && b < 39) {
        icecream[a][b] = "X";
        icecream[a][b + c] = "X";
        --a;
        ++b;
        c = c - 2;
    }

    icecream[27][37] = "X";
    icecream[28][38] = "X";
    icecream[27][39] = "X";

    //Writing to the file
    ofstream icecreamFile;
    icecreamFile.open(icecreamFilename);
    icecreamFile << "45 78" << endl;
    for (int i = 0; i < 45; ++i) {
        for (int j = 0; j < 78; ++j) {
            icecreamFile << icecream[i][j];
        }
        icecreamFile << endl;
    }
    icecreamFile.close();
}
