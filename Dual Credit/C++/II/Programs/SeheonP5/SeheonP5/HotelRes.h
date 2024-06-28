#ifndef HOTEL_RES_H
#define HOTEL_RES_H

#include <string>
#include <sstream>
#include "Date.h"

using namespace std;

class HotelRes
{
private:
	string name;	 //name of reservation party
	int guests{ 1 };	//number of guests in a room, 1 or 2
	Date arrival, departure, booking;	 //three date objects for guest data
	int numberOfNights{ 1 };	 //length of stay at the spa
	double roomRate{ 0.0 };	 //basic rate of room, not including potential discount
	double lodgingTax{ 0.0 };	 //tax rate percentage based on the city/county/state laws
	double discountRate{ 0.0 };	 //percentage discount for staying 5 nights or more
	string confirmationNumber;    //confirmation number based on 10001+lastname 
	string errorstring; 	//string reporting validation error
	double roomCost{ 0.0 }, tax{ 0.0 }, totalCost{ 0.0 };
	bool bReadIn{ true }, bWrittenOut{ true };	//shows input/output files read/written status
	string reservationDescription;
	int confirmationNum{ 10001 };

	static int resNumber;	//the number of the reservation made, declaration only

	void ReadReservationData();	//reads file with hotel reservation data
	void CalculateCostOfVisit();	 //calculates total cost to guest(s)
	void WriteConfirmationFile();	 //writes the complete confirmation file
	void ValidateReservation(); //validates arrival/departure dates
	void MakeReservation();


public:
	HotelRes(); 	//default constructor—calls ReadReservationData 
	HotelRes(string resName, int guest, Date& arr, Date& dpt, Date& book, int conNum);

	void SetName(string n) { name = n; }	//reservation name	
	void SetDates(Date& arr, Date& dpt, Date& book);
	void SetNumberOfGuests(int g) { guests = g; }	//number of guests in a room

	string PresentRoomMenu();
	string GetReservationDescription() { return reservationDescription; }

};

#endif