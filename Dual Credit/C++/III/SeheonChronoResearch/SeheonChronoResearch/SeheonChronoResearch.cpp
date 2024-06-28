//Method from https://gist.github.com/niranjanmalviya/671bb0ca9dcc16093538f0cdf8cbc873

#include <chrono> // for system_clock
#include <iomanip> // for put_time
#include <iostream> // for cout
#include <sstream> // ostringstream
#include <ctime> // for time_t

using namespace std;

string CurrentTime_ctime_s() {
	char curTime[26];
	errno_t error;
	chrono::time_point<chrono::system_clock> timepoint = chrono::system_clock::now();
	time_t time_now_t = chrono::system_clock::to_time_t(timepoint);
	error = ctime_s(curTime, sizeof(curTime), &time_now_t);
	if (error != 0) {
		return "Error Code : " + error;
	}
	else {
		return curTime;
	}
}

string CurrentTime_strftime() {

	tm now_tm;
	errno_t error;
	chrono::time_point<chrono::system_clock> timepoint = chrono::system_clock::now();
	time_t time_now_t = chrono::system_clock::to_time_t(timepoint);
	error = localtime_s(&now_tm, &time_now_t);
	if (error != 0) {
		return "Error Code : " + error;
	}
	else {
		char buf[26];
		strftime(buf, 26, "%d-%B-%Y %H:%M:%S", &now_tm);
		return buf;
	}
}

string CurrentTime_put_time() {
	tm now_tm;
	errno_t error;
	chrono::time_point<chrono::system_clock> timepoint = chrono::system_clock::now();
	time_t time_now_t = chrono::system_clock::to_time_t(timepoint);
	error = localtime_s(&now_tm, &time_now_t);
	if (error != 0) {
		return "Error Code : " + error;
	}
	else {
		ostringstream ss;
		ss << put_time(&now_tm, "%FT%T%z"); // ISO 8601 format
		return ss.str();
	}
}

int main() {

	cout << CurrentTime_ctime_s() << endl;
	cout << CurrentTime_strftime() << endl;
	cout << CurrentTime_put_time() << endl;

	cin.get();
	return 0;
}
/* Result should look like below, to change the format refer respective format string.
Sat Jul  7 23:20:52 2018
07-July-2018 23:20:52
2018-07-07T23:20:52+0530
*/