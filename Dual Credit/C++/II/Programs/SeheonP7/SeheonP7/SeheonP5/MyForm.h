//Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Date: 11/24/20
//Program: SeheonP7 is a program to schedule appointments for a spa.
//File: MyForm.h

#pragma once
#include "HotelRes.h"
#include "Date.h"
#include "UtilityFunction.h"


namespace SeheonP7 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
	using namespace std;

	/// <summary>
	/// Summary for MyForm
	/// </summary>
	public ref class MyForm : public System::Windows::Forms::Form
	{
	public:
		MyForm(void)
		{
			InitializeComponent();
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~MyForm()
		{
			if (components)
			{
				delete components;
			}
		}

	protected:
	private: System::Windows::Forms::Label^ label2;
	private: System::Windows::Forms::Label^ label3;
	private: System::Windows::Forms::Label^ label4;



	private: System::Windows::Forms::Button^ btnMakeRes;
	private: System::Windows::Forms::Button^ btnNewRes;
	private: System::Windows::Forms::TextBox^ txtResName;






	private: System::Windows::Forms::Label^ label9;




	private: System::Windows::Forms::NumericUpDown^ nudGuestAmount;
	private: System::Windows::Forms::TextBox^ txtResDesc;
	private: System::Windows::Forms::DateTimePicker^ dtpArrive;
	private: System::Windows::Forms::DateTimePicker^ dtpDepart;
	private: System::Windows::Forms::GroupBox^ groupBox1;
	private: System::Windows::Forms::TextBox^ txtMenu;








	protected:

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->label4 = (gcnew System::Windows::Forms::Label());
			this->btnMakeRes = (gcnew System::Windows::Forms::Button());
			this->btnNewRes = (gcnew System::Windows::Forms::Button());
			this->txtResName = (gcnew System::Windows::Forms::TextBox());
			this->label9 = (gcnew System::Windows::Forms::Label());
			this->nudGuestAmount = (gcnew System::Windows::Forms::NumericUpDown());
			this->txtResDesc = (gcnew System::Windows::Forms::TextBox());
			this->dtpArrive = (gcnew System::Windows::Forms::DateTimePicker());
			this->dtpDepart = (gcnew System::Windows::Forms::DateTimePicker());
			this->groupBox1 = (gcnew System::Windows::Forms::GroupBox());
			this->txtMenu = (gcnew System::Windows::Forms::TextBox());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudGuestAmount))->BeginInit();
			this->groupBox1->SuspendLayout();
			this->SuspendLayout();
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9.75F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label2->Location = System::Drawing::Point(124, 148);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(162, 16);
			this->label2->TabIndex = 1;
			this->label2->Text = L"Name on Reservation:";
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9.75F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label3->Location = System::Drawing::Point(124, 213);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(176, 16);
			this->label3->TabIndex = 2;
			this->label3->Text = L"How many in your party\?";
			// 
			// label4
			// 
			this->label4->AutoSize = true;
			this->label4->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 10, System::Drawing::FontStyle::Bold));
			this->label4->Location = System::Drawing::Point(53, 24);
			this->label4->Name = L"label4";
			this->label4->Size = System::Drawing::Size(123, 17);
			this->label4->TabIndex = 3;
			this->label4->Text = L"Date of Arrival: ";
			// 
			// btnMakeRes
			// 
			this->btnMakeRes->BackColor = System::Drawing::Color::LightGreen;
			this->btnMakeRes->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold));
			this->btnMakeRes->Location = System::Drawing::Point(704, 157);
			this->btnMakeRes->Name = L"btnMakeRes";
			this->btnMakeRes->Size = System::Drawing::Size(125, 85);
			this->btnMakeRes->TabIndex = 7;
			this->btnMakeRes->Text = L"Make My Reservation";
			this->btnMakeRes->UseVisualStyleBackColor = false;
			this->btnMakeRes->Click += gcnew System::EventHandler(this, &MyForm::btnMakeRes_Click);
			// 
			// btnNewRes
			// 
			this->btnNewRes->BackColor = System::Drawing::Color::LightGreen;
			this->btnNewRes->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold));
			this->btnNewRes->Location = System::Drawing::Point(704, 248);
			this->btnNewRes->Name = L"btnNewRes";
			this->btnNewRes->Size = System::Drawing::Size(125, 88);
			this->btnNewRes->TabIndex = 8;
			this->btnNewRes->Text = L"Make Another Reservation\?";
			this->btnNewRes->UseVisualStyleBackColor = false;
			this->btnNewRes->Click += gcnew System::EventHandler(this, &MyForm::btnNewRes_Click);
			// 
			// txtResName
			// 
			this->txtResName->Location = System::Drawing::Point(127, 179);
			this->txtResName->Name = L"txtResName";
			this->txtResName->Size = System::Drawing::Size(159, 20);
			this->txtResName->TabIndex = 9;
			// 
			// label9
			// 
			this->label9->AutoSize = true;
			this->label9->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 10, System::Drawing::FontStyle::Bold));
			this->label9->Location = System::Drawing::Point(53, 90);
			this->label9->Name = L"label9";
			this->label9->Size = System::Drawing::Size(139, 17);
			this->label9->TabIndex = 11;
			this->label9->Text = L"Date of Departure";
			// 
			// nudGuestAmount
			// 
			this->nudGuestAmount->Location = System::Drawing::Point(177, 238);
			this->nudGuestAmount->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 2, 0, 0, 0 });
			this->nudGuestAmount->Minimum = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			this->nudGuestAmount->Name = L"nudGuestAmount";
			this->nudGuestAmount->Size = System::Drawing::Size(58, 20);
			this->nudGuestAmount->TabIndex = 16;
			this->nudGuestAmount->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) { 1, 0, 0, 0 });
			// 
			// txtResDesc
			// 
			this->txtResDesc->BackColor = System::Drawing::Color::LightGreen;
			this->txtResDesc->Location = System::Drawing::Point(83, 284);
			this->txtResDesc->Multiline = true;
			this->txtResDesc->Name = L"txtResDesc";
			this->txtResDesc->Size = System::Drawing::Size(583, 187);
			this->txtResDesc->TabIndex = 22;
			// 
			// dtpArrive
			// 
			this->dtpArrive->Location = System::Drawing::Point(11, 53);
			this->dtpArrive->MaxDate = System::DateTime(2021, 12, 31, 0, 0, 0, 0);
			this->dtpArrive->MinDate = System::DateTime(2020, 10, 13, 0, 0, 0, 0);
			this->dtpArrive->Name = L"dtpArrive";
			this->dtpArrive->Size = System::Drawing::Size(261, 23);
			this->dtpArrive->TabIndex = 23;
			// 
			// dtpDepart
			// 
			this->dtpDepart->Location = System::Drawing::Point(11, 111);
			this->dtpDepart->MaxDate = System::DateTime(2021, 12, 31, 0, 0, 0, 0);
			this->dtpDepart->MinDate = System::DateTime(2020, 10, 13, 0, 0, 0, 0);
			this->dtpDepart->Name = L"dtpDepart";
			this->dtpDepart->Size = System::Drawing::Size(261, 23);
			this->dtpDepart->TabIndex = 24;
			// 
			// groupBox1
			// 
			this->groupBox1->Controls->Add(this->dtpDepart);
			this->groupBox1->Controls->Add(this->dtpArrive);
			this->groupBox1->Controls->Add(this->label9);
			this->groupBox1->Controls->Add(this->label4);
			this->groupBox1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 10, System::Drawing::FontStyle::Bold));
			this->groupBox1->Location = System::Drawing::Point(357, 122);
			this->groupBox1->Name = L"groupBox1";
			this->groupBox1->Size = System::Drawing::Size(282, 147);
			this->groupBox1->TabIndex = 25;
			this->groupBox1->TabStop = false;
			this->groupBox1->Text = L"Set Duration of Stay:";
			// 
			// txtMenu
			// 
			this->txtMenu->BackColor = System::Drawing::Color::LightGreen;
			this->txtMenu->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 10, System::Drawing::FontStyle::Bold));
			this->txtMenu->Location = System::Drawing::Point(132, 3);
			this->txtMenu->Multiline = true;
			this->txtMenu->Name = L"txtMenu";
			this->txtMenu->Size = System::Drawing::Size(606, 113);
			this->txtMenu->TabIndex = 26;
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(7, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->BackColor = System::Drawing::Color::MediumTurquoise;
			this->ClientSize = System::Drawing::Size(887, 483);
			this->Controls->Add(this->txtMenu);
			this->Controls->Add(this->groupBox1);
			this->Controls->Add(this->txtResDesc);
			this->Controls->Add(this->nudGuestAmount);
			this->Controls->Add(this->txtResName);
			this->Controls->Add(this->btnNewRes);
			this->Controls->Add(this->btnMakeRes);
			this->Controls->Add(this->label3);
			this->Controls->Add(this->label2);
			this->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 8.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->Name = L"MyForm";
			this->Text = L"Emely Seheon   Program 5   Spa Reservations";
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nudGuestAmount))->EndInit();
			this->groupBox1->ResumeLayout(false);
			this->groupBox1->PerformLayout();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
		int num = 1;
	private: System::Void MyForm_Load(System::Object^ sender, System::EventArgs^ e) {
		HotelRes res;
		txtMenu->Text = gcnew String(res.PresentRoomMenu().c_str());
	}
	private: System::Void btnMakeRes_Click(System::Object^ sender, System::EventArgs^ e) {
		string name;
		To_string(txtResName->Text, name);


		int guests = Decimal::ToInt32(nudGuestAmount->Value);
		int dayArr = dtpArrive->Value.Day;
		int monthArr = dtpArrive->Value.Month;
		int yearArr = dtpArrive->Value.Year;

		Date arr{ monthArr, dayArr, yearArr, "Arrival" };


		int dayDep = dtpDepart->Value.Day;
		int monthDep = dtpDepart->Value.Month;
		int yearDep = dtpDepart->Value.Year;

		Date dpt{ monthDep, dayDep, yearDep, "Departure" };


		Date today;

		HotelRes res{ name,guests, arr, dpt, today, num};
		txtResDesc->Text = gcnew String(res.GetReservationDescription().c_str());
		
	}
	private: System::Void btnNewRes_Click(System::Object^ sender, System::EventArgs^ e) {
		txtResName->Clear();
		nudGuestAmount->Value = 1;
		dtpArrive->Value = DateTime::Now;
		dtpDepart->Value = DateTime::Now;
		++num;
	}
};
}
