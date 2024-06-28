#pragma once
#include "Simplecalc.h"

namespace SeheonP4 {

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
	SimpleCalc calc;

	public ref class MyForm : public System::Windows::Forms::Form
	{
	public:
		MyForm(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
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
	private: System::Windows::Forms::Label^ label1;
	protected:
	private: System::Windows::Forms::TextBox^ txtNum1;
	private: System::Windows::Forms::TextBox^ txtNum2;
	private: System::Windows::Forms::TextBox^ txtResults;
	private: System::Windows::Forms::Label^ label2;
	private: System::Windows::Forms::Label^ lblOps;

	private: System::Windows::Forms::Button^ btnAdd;
	private: System::Windows::Forms::Button^ btnSubtract;
	private: System::Windows::Forms::Button^ btnMultiply;
	private: System::Windows::Forms::Button^ btnDivide;
	private: System::Windows::Forms::Button^ btnClear;



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
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->txtNum1 = (gcnew System::Windows::Forms::TextBox());
			this->txtNum2 = (gcnew System::Windows::Forms::TextBox());
			this->txtResults = (gcnew System::Windows::Forms::TextBox());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->lblOps = (gcnew System::Windows::Forms::Label());
			this->btnAdd = (gcnew System::Windows::Forms::Button());
			this->btnSubtract = (gcnew System::Windows::Forms::Button());
			this->btnMultiply = (gcnew System::Windows::Forms::Button());
			this->btnDivide = (gcnew System::Windows::Forms::Button());
			this->btnClear = (gcnew System::Windows::Forms::Button());
			this->SuspendLayout();
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Location = System::Drawing::Point(41, 9);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(482, 44);
			this->label1->TabIndex = 0;
			this->label1->Text = L"                Welcome to the C++ Calculator\r\nEnter two numbers and press the op"
				L"eration button.";
			// 
			// txtNum1
			// 
			this->txtNum1->Location = System::Drawing::Point(12, 84);
			this->txtNum1->Name = L"txtNum1";
			this->txtNum1->Size = System::Drawing::Size(100, 29);
			this->txtNum1->TabIndex = 1;
			// 
			// txtNum2
			// 
			this->txtNum2->Location = System::Drawing::Point(171, 84);
			this->txtNum2->Name = L"txtNum2";
			this->txtNum2->Size = System::Drawing::Size(100, 29);
			this->txtNum2->TabIndex = 2;
			// 
			// txtResults
			// 
			this->txtResults->Location = System::Drawing::Point(324, 84);
			this->txtResults->Name = L"txtResults";
			this->txtResults->Size = System::Drawing::Size(217, 29);
			this->txtResults->TabIndex = 3;
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Location = System::Drawing::Point(287, 87);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(21, 22);
			this->label2->TabIndex = 4;
			this->label2->Text = L"=";
			// 
			// lblOps
			// 
			this->lblOps->AutoSize = true;
			this->lblOps->Location = System::Drawing::Point(133, 87);
			this->lblOps->Name = L"lblOps";
			this->lblOps->Size = System::Drawing::Size(0, 22);
			this->lblOps->TabIndex = 5;
			// 
			// btnAdd
			// 
			this->btnAdd->Location = System::Drawing::Point(12, 139);
			this->btnAdd->Name = L"btnAdd";
			this->btnAdd->Size = System::Drawing::Size(100, 46);
			this->btnAdd->TabIndex = 6;
			this->btnAdd->Text = L"+";
			this->btnAdd->UseVisualStyleBackColor = true;
			this->btnAdd->Click += gcnew System::EventHandler(this, &MyForm::btnAdd_Click);
			// 
			// btnSubtract
			// 
			this->btnSubtract->Location = System::Drawing::Point(171, 139);
			this->btnSubtract->Name = L"btnSubtract";
			this->btnSubtract->Size = System::Drawing::Size(100, 46);
			this->btnSubtract->TabIndex = 7;
			this->btnSubtract->Text = L"-";
			this->btnSubtract->UseVisualStyleBackColor = true;
			this->btnSubtract->Click += gcnew System::EventHandler(this, &MyForm::btnSubtract_Click);
			// 
			// btnMultiply
			// 
			this->btnMultiply->Location = System::Drawing::Point(12, 196);
			this->btnMultiply->Name = L"btnMultiply";
			this->btnMultiply->Size = System::Drawing::Size(100, 46);
			this->btnMultiply->TabIndex = 8;
			this->btnMultiply->Text = L"*";
			this->btnMultiply->UseVisualStyleBackColor = true;
			this->btnMultiply->Click += gcnew System::EventHandler(this, &MyForm::btnMultiply_Click);
			// 
			// btnDivide
			// 
			this->btnDivide->Location = System::Drawing::Point(171, 196);
			this->btnDivide->Name = L"btnDivide";
			this->btnDivide->Size = System::Drawing::Size(100, 46);
			this->btnDivide->TabIndex = 9;
			this->btnDivide->Text = L"/";
			this->btnDivide->UseVisualStyleBackColor = true;
			this->btnDivide->Click += gcnew System::EventHandler(this, &MyForm::btnDivide_Click);
			// 
			// btnClear
			// 
			this->btnClear->Location = System::Drawing::Point(375, 161);
			this->btnClear->Name = L"btnClear";
			this->btnClear->Size = System::Drawing::Size(100, 46);
			this->btnClear->TabIndex = 10;
			this->btnClear->Text = L"CLEAR";
			this->btnClear->UseVisualStyleBackColor = true;
			this->btnClear->Click += gcnew System::EventHandler(this, &MyForm::btnClear_Click);
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(12, 22);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(545, 273);
			this->Controls->Add(this->btnClear);
			this->Controls->Add(this->btnDivide);
			this->Controls->Add(this->btnMultiply);
			this->Controls->Add(this->btnSubtract);
			this->Controls->Add(this->btnAdd);
			this->Controls->Add(this->lblOps);
			this->Controls->Add(this->label2);
			this->Controls->Add(this->txtResults);
			this->Controls->Add(this->txtNum2);
			this->Controls->Add(this->txtNum1);
			this->Controls->Add(this->label1);
			this->Font = (gcnew System::Drawing::Font(L"Arial", 14.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->Margin = System::Windows::Forms::Padding(6, 5, 6, 5);
			this->Name = L"MyForm";
			this->Text = L"MyForm";
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion

private: void Calculate(char operation) {
	double n1 = Convert::ToDouble(txtNum1->Text);
	double n2 = Convert::ToDouble(txtNum2->Text);

	calc.SetOperation(operation, n1, n2);
	txtResults->Text = gcnew String(calc.GetResults().c_str());
	lblOps->Text = Convert::ToChar(operation).ToString();
}

private: System::Void btnAdd_Click(System::Object^ sender, System::EventArgs^ e) {
	Calculate('+');
}
private: System::Void btnSubtract_Click(System::Object^ sender, System::EventArgs^ e) {
	Calculate('-');
}
private: System::Void btnMultiply_Click(System::Object^ sender, System::EventArgs^ e) {
	Calculate('*');
}
private: System::Void btnDivide_Click(System::Object^ sender, System::EventArgs^ e) {
	Calculate('/');
}
private: System::Void btnClear_Click(System::Object^ sender, System::EventArgs^ e) {	
	txtNum1->Clear();
	txtNum2->Clear();
	txtResults->Clear();
	lblOps->Text = "";
}
};
}
