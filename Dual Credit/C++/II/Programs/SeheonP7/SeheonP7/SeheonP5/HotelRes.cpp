//File: HotelRes.cpp

#include "HotelRes.h"
#include<fstream>

const string FILE_NAME{ "ResData.txt" };

void HotelRes::ReadReservationData()
{
	ifstream input; 
	input.open(FILE_NAME);
	//Check if file is open
	if (!input) {
		bReadIn = false;
	}
	else {
		//read the data
		input >> roomRate >> lodgingTax >> discountRate;

		bReadIn = true;
		input.close();
	}
}

void HotelRes::CalculateCostOfVisit()
{
	if (departure.GetYear() == arrival.GetYear()) {
		numberOfNights = departure - arrival;
	}
	else {
		if (arrival.isLeapYear()) {
			numberOfNights = (366 - arrival.GetDayOfYear()) + departure.GetDayOfYear();
		}
		else {
			numberOfNights = (365 - arrival.GetDayOfYear()) + departure.GetDayOfYear();
		}
	}

	roomCost = roomRate * guests * numberOfNights;
	tax = roomCost * (lodgingTax * 0.01);
	if (numberOfNights >= 5) {
		totalCost = (roomCost + tax) * (1- discountRate * 0.01);
	}
	else {
		totalCost = roomCost + tax;
	}
}

void HotelRes::WriteConfirmationFile()
{
	string months[12] = { "January", "February", "March", "April", "May" , "June", "July" , "August" , "September" , "October" , "November", "December" };

	stringstream findConNum;
	findConNum << confirmationNum;
	for (int i = 0; i < 2;++i) {
		findConNum << name[i];
	}

	confirmationNumber = findConNum.str();

	stringstream resDesc;
	resDesc << "Welcome to the C++ Spa and Resort " << months[booking.GetMonth()-1] << " " << booking.GetDay() << ", " << booking.GetYear()
		<< "\r\nGuest: " << name << "     Confirmation Number: " << confirmationNumber
		<< "\r\n\r\nArrival: " << months[arrival.GetMonth()-1] << " " << arrival.GetDay() << ", " << arrival.GetYear()
		<< "     Departure: " << months[departure.GetMonth()-1] << " " << departure.GetDay() << ", " << departure.GetYear()
		<< "\r\nNumber of nights: " << numberOfNights << "     Number of Guests: " << guests
		<< "\r\n\r\nRoom Cost: ";

	if (numberOfNights >= 5) {
		resDesc << "(with 15% discount) $";
	}

	resDesc << roomCost
		<< "\r\nLodging Tax: $" << tax
		<< "\r\nTotal Cost: $" << totalCost
		<< "\r\n\r\nWe look forward to seeing you in " << months[arrival.GetMonth()-1] << "!";

	reservationDescription = resDesc.str();

	ofstream output;
	output.open(confirmationNumber);
	output << reservationDescription;
}

void HotelRes::ValidateReservation()
{
	
	if (departure<arrival)
	{
		errorstring = "Error: Departure date can not be before the arrival date.";
	}
	else if (booking>arrival)
	{
		errorstring = "Error: Arrival date can not be before the booking date.";
	}
	else {
		errorstring = "OK";
	}
}

void HotelRes::MakeReservation()
{
	ValidateReservation();
	if (errorstring == "OK") {
		CalculateCostOfVisit();
		WriteConfirmationFile();
	}
	else {
		reservationDescription = errorstring;
	}
}

HotelRes::HotelRes()
{
	ReadReservationData();
}

HotelRes::HotelRes(string resName, int guest, Date& arr, Date& dpt, Date& book, int num)
{

	name = resName;
	guests = guest;
	arrival = arr;
	departure = dpt;
	booking = book;
	confirmationNum = confirmationNum + num;
	ReadReservationData();
	MakeReservation();
}

void HotelRes::SetDates(Date& arr, Date& dpt, Date& book)
{
	arrival = arr;
	departure = dpt;
	booking = book;
	MakeReservation();
}

string HotelRes::PresentRoomMenu()
{
	string menu;
	if (bReadIn == false) {
		return "Room Rates not available.";
	}
	else {
		stringstream ss;
		ss << "\r\n Welcome to the C++ Spa and Resort! "
			<< "\r\n The room rate is $" << roomRate << " per person per night"
			<< " single or double occupancy"
			<< "\r\n Lodging Tax is " << lodgingTax << "% "
			<< "\r\n There is a " << discountRate << "% discount for vacations longer "
			<< "than 5 days.\r\n";
		menu = ss.str();
	}
	return menu;
}