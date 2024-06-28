//Coded by Emely Seheon

#pragma once
#include "Game.h"
#include <sstream>

namespace NavarroSeheonDyeP6 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for MyForm
	/// </summary>
	/// 
	Game myGame;

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
	private: System::Windows::Forms::Label^ lblBet;

	private: System::Windows::Forms::TextBox^ txtBet;

	private: System::Windows::Forms::Button^ btnBet;
	private: System::Windows::Forms::Label^ label3;
	private: System::Windows::Forms::Label^ label4;
	private: System::Windows::Forms::Button^ btnPlayAgain;

	private: System::Windows::Forms::Label^ label5;
	private: System::Windows::Forms::TextBox^ txtPlayerHand;
	private: System::Windows::Forms::TextBox^ txtDealerHand;
	private: System::Windows::Forms::TextBox^ txtStatus;



	private: System::Windows::Forms::MenuStrip^ menuStrip1;
	private: System::Windows::Forms::ToolStripMenuItem^ RULESOFTHEGAMEToolStripMenuItem;


	private: System::Windows::Forms::Button^ btnQuit;
	private: System::Windows::Forms::Button^ btnHit;
	private: System::Windows::Forms::Button^ btnStay;
	private: System::Windows::Forms::GroupBox^ gbAction;

	private: System::Windows::Forms::GroupBox^ gbBet;

	protected:

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container^ components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->lblBet = (gcnew System::Windows::Forms::Label());
			this->txtBet = (gcnew System::Windows::Forms::TextBox());
			this->btnBet = (gcnew System::Windows::Forms::Button());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->label4 = (gcnew System::Windows::Forms::Label());
			this->btnPlayAgain = (gcnew System::Windows::Forms::Button());
			this->label5 = (gcnew System::Windows::Forms::Label());
			this->txtPlayerHand = (gcnew System::Windows::Forms::TextBox());
			this->txtDealerHand = (gcnew System::Windows::Forms::TextBox());
			this->txtStatus = (gcnew System::Windows::Forms::TextBox());
			this->menuStrip1 = (gcnew System::Windows::Forms::MenuStrip());
			this->RULESOFTHEGAMEToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->btnQuit = (gcnew System::Windows::Forms::Button());
			this->btnHit = (gcnew System::Windows::Forms::Button());
			this->btnStay = (gcnew System::Windows::Forms::Button());
			this->gbAction = (gcnew System::Windows::Forms::GroupBox());
			this->gbBet = (gcnew System::Windows::Forms::GroupBox());
			this->menuStrip1->SuspendLayout();
			this->gbAction->SuspendLayout();
			this->gbBet->SuspendLayout();
			this->SuspendLayout();
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label1->Location = System::Drawing::Point(81, 43);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(598, 72);
			this->label1->TabIndex = 0;
			this->label1->Text = L"                    Welcome to the C++ BlackJack Table!\r\n      You will begin wit"
				L"h $1,000 for your gambling pleasure.\r\nYou may view the rules by clicking on \"RUL"
				L"ES OF THE GAME\"";
			// 
			// lblBet
			// 
			this->lblBet->AutoSize = true;
			this->lblBet->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->lblBet->Location = System::Drawing::Point(9, 23);
			this->lblBet->Name = L"lblBet";
			this->lblBet->Size = System::Drawing::Size(329, 20);
			this->lblBet->TabIndex = 1;
			this->lblBet->Text = L"Enter your bet in the box and press BET";
			// 
			// txtBet
			// 
			this->txtBet->Location = System::Drawing::Point(376, 23);
			this->txtBet->Name = L"txtBet";
			this->txtBet->Size = System::Drawing::Size(100, 20);
			this->txtBet->TabIndex = 2;
			// 
			// btnBet
			// 
			this->btnBet->BackColor = System::Drawing::Color::PaleTurquoise;
			this->btnBet->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnBet->Location = System::Drawing::Point(542, 9);
			this->btnBet->Name = L"btnBet";
			this->btnBet->Size = System::Drawing::Size(104, 45);
			this->btnBet->TabIndex = 3;
			this->btnBet->Text = L"BET";
			this->btnBet->UseVisualStyleBackColor = false;
			this->btnBet->Click += gcnew System::EventHandler(this, &MyForm::btnBet_Click);
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label3->Location = System::Drawing::Point(12, 178);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(119, 20);
			this->label3->TabIndex = 4;
			this->label3->Text = L"Player\'s Hand";
			// 
			// label4
			// 
			this->label4->AutoSize = true;
			this->label4->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label4->Location = System::Drawing::Point(217, 178);
			this->label4->Name = L"label4";
			this->label4->Size = System::Drawing::Size(123, 20);
			this->label4->TabIndex = 5;
			this->label4->Text = L"Dealer\'s Hand";
			// 
			// btnPlayAgain
			// 
			this->btnPlayAgain->BackColor = System::Drawing::Color::PaleTurquoise;
			this->btnPlayAgain->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnPlayAgain->Location = System::Drawing::Point(435, 352);
			this->btnPlayAgain->Name = L"btnPlayAgain";
			this->btnPlayAgain->Size = System::Drawing::Size(137, 73);
			this->btnPlayAgain->TabIndex = 6;
			this->btnPlayAgain->Text = L"PLAY AGAIN!";
			this->btnPlayAgain->UseVisualStyleBackColor = false;
			this->btnPlayAgain->Click += gcnew System::EventHandler(this, &MyForm::btnPlayAgain_Click);
			// 
			// label5
			// 
			this->label5->AutoSize = true;
			this->label5->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label5->Location = System::Drawing::Point(433, 178);
			this->label5->Name = L"label5";
			this->label5->Size = System::Drawing::Size(115, 20);
			this->label5->TabIndex = 7;
			this->label5->Text = L"Game Status";
			// 
			// txtPlayerHand
			// 
			this->txtPlayerHand->Location = System::Drawing::Point(12, 201);
			this->txtPlayerHand->Multiline = true;
			this->txtPlayerHand->Name = L"txtPlayerHand";
			this->txtPlayerHand->ScrollBars = System::Windows::Forms::ScrollBars::Vertical;
			this->txtPlayerHand->Size = System::Drawing::Size(189, 138);
			this->txtPlayerHand->TabIndex = 8;
			// 
			// txtDealerHand
			// 
			this->txtDealerHand->Location = System::Drawing::Point(221, 201);
			this->txtDealerHand->Multiline = true;
			this->txtDealerHand->Name = L"txtDealerHand";
			this->txtDealerHand->ScrollBars = System::Windows::Forms::ScrollBars::Vertical;
			this->txtDealerHand->Size = System::Drawing::Size(189, 138);
			this->txtDealerHand->TabIndex = 9;
			// 
			// txtStatus
			// 
			this->txtStatus->Location = System::Drawing::Point(435, 201);
			this->txtStatus->Multiline = true;
			this->txtStatus->Name = L"txtStatus";
			this->txtStatus->ScrollBars = System::Windows::Forms::ScrollBars::Vertical;
			this->txtStatus->Size = System::Drawing::Size(326, 138);
			this->txtStatus->TabIndex = 10;
			// 
			// menuStrip1
			// 
			this->menuStrip1->Items->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(1) { this->RULESOFTHEGAMEToolStripMenuItem });
			this->menuStrip1->Location = System::Drawing::Point(0, 0);
			this->menuStrip1->Name = L"menuStrip1";
			this->menuStrip1->Size = System::Drawing::Size(773, 24);
			this->menuStrip1->TabIndex = 11;
			this->menuStrip1->Text = L"menuStrip1";
			// 
			// RULESOFTHEGAMEToolStripMenuItem
			// 
			this->RULESOFTHEGAMEToolStripMenuItem->Name = L"RULESOFTHEGAMEToolStripMenuItem";
			this->RULESOFTHEGAMEToolStripMenuItem->Size = System::Drawing::Size(130, 20);
			this->RULESOFTHEGAMEToolStripMenuItem->Text = L"RULES OF THE GAME";
			this->RULESOFTHEGAMEToolStripMenuItem->Click += gcnew System::EventHandler(this, &MyForm::RULESOFTHEGAMEToolStripMenuItem_Click);
			// 
			// btnQuit
			// 
			this->btnQuit->BackColor = System::Drawing::Color::PaleTurquoise;
			this->btnQuit->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 14.25F, System::Drawing::FontStyle::Bold));
			this->btnQuit->Location = System::Drawing::Point(624, 353);
			this->btnQuit->Name = L"btnQuit";
			this->btnQuit->Size = System::Drawing::Size(137, 73);
			this->btnQuit->TabIndex = 14;
			this->btnQuit->Text = L"QUIT";
			this->btnQuit->UseVisualStyleBackColor = false;
			this->btnQuit->Click += gcnew System::EventHandler(this, &MyForm::btnQuit_Click);
			// 
			// btnHit
			// 
			this->btnHit->BackColor = System::Drawing::Color::PaleTurquoise;
			this->btnHit->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->btnHit->Location = System::Drawing::Point(6, 12);
			this->btnHit->Name = L"btnHit";
			this->btnHit->Size = System::Drawing::Size(104, 45);
			this->btnHit->TabIndex = 12;
			this->btnHit->Text = L"HIT";
			this->btnHit->UseVisualStyleBackColor = false;
			this->btnHit->Click += gcnew System::EventHandler(this, &MyForm::btnHit_Click);
			// 
			// btnStay
			// 
			this->btnStay->BackColor = System::Drawing::Color::PaleTurquoise;
			this->btnStay->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold));
			this->btnStay->Location = System::Drawing::Point(246, 12);
			this->btnStay->Name = L"btnStay";
			this->btnStay->Size = System::Drawing::Size(104, 45);
			this->btnStay->TabIndex = 13;
			this->btnStay->Text = L"STAY";
			this->btnStay->UseVisualStyleBackColor = false;
			this->btnStay->Click += gcnew System::EventHandler(this, &MyForm::btnStay_Click);
			// 
			// gbAction
			// 
			this->gbAction->Controls->Add(this->btnStay);
			this->gbAction->Controls->Add(this->btnHit);
			this->gbAction->Location = System::Drawing::Point(31, 345);
			this->gbAction->Name = L"gbAction";
			this->gbAction->Size = System::Drawing::Size(356, 69);
			this->gbAction->TabIndex = 15;
			this->gbAction->TabStop = false;
			this->gbAction->Visible = false;
			// 
			// gbBet
			// 
			this->gbBet->Controls->Add(this->btnBet);
			this->gbBet->Controls->Add(this->txtBet);
			this->gbBet->Controls->Add(this->lblBet);
			this->gbBet->Location = System::Drawing::Point(71, 115);
			this->gbBet->Name = L"gbBet";
			this->gbBet->Size = System::Drawing::Size(662, 60);
			this->gbBet->TabIndex = 16;
			this->gbBet->TabStop = false;
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(7, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->BackColor = System::Drawing::Color::IndianRed;
			this->ClientSize = System::Drawing::Size(773, 437);
			this->Controls->Add(this->gbBet);
			this->Controls->Add(this->gbAction);
			this->Controls->Add(this->btnQuit);
			this->Controls->Add(this->txtStatus);
			this->Controls->Add(this->txtDealerHand);
			this->Controls->Add(this->txtPlayerHand);
			this->Controls->Add(this->label5);
			this->Controls->Add(this->btnPlayAgain);
			this->Controls->Add(this->label4);
			this->Controls->Add(this->label3);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->menuStrip1);
			this->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 8.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->MainMenuStrip = this->menuStrip1;
			this->Name = L"MyForm";
			this->Text = L"Group2 BlackJack";
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			this->menuStrip1->ResumeLayout(false);
			this->menuStrip1->PerformLayout();
			this->gbAction->ResumeLayout(false);
			this->gbBet->ResumeLayout(false);
			this->gbBet->PerformLayout();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion

		//RulesOfTheGameMenuItem event
	private: System::Void RULESOFTHEGAMEToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e) {
		String^ message = "Welcome to BlackJack! The goal of the game is to get all of your cards to add up as close as possible to 21. \r\nFirst, make your bet. Click the HIT button to add another card to your hand. Click STAY to keep your hand. \r\nIf your cards go over 21, you bust and lose the game. If you choose to stay, the dealer will continue to draw until they choose to stop. \r\nWhoever is closer to 21 wins, if there is a tie, you win. Press PLAY AGAIN to play again, and QUIT to quit. Good luck!";
		MessageBox::Show(message);

	}

		   ////Bet event
	private: System::Void btnBet_Click(System::Object^ sender, System::EventArgs^ e) {
		int bet{ 0 };
		Boolean isValidBet = false;

		bet = Convert::ToInt32(txtBet->Text);
		isValidBet = myGame.SetBet(bet);
		if (isValidBet)
		{

			myGame.InitialDeal();
			txtPlayerHand->Text = gcnew String(myGame.ShowPlayerHand().c_str());
			txtDealerHand->Text = gcnew String(myGame.ShowDealersHand(true).c_str());

			if (myGame.IsBlackJack()) {
				stringstream ss;
				ss << "BlackJack\r\n" << myGame.PlayerWins();
				txtStatus->Text = gcnew String(ss.str().c_str());
			}
			else {
				gbBet->Visible = false;
				gbAction->Visible = true; 
			}
		}
		else {
			txtStatus->Text = gcnew String("Invalid Bet, try again.");
		}
	}

		   //Button hit event
	private: System::Void btnHit_Click(System::Object^ sender, System::EventArgs^ e) {
		if (myGame.PlayerContinues()) {
			myGame.PlayerHits();
			txtPlayerHand->Text = gcnew String(myGame.ShowPlayerHand().c_str());
			if (myGame.PlayerBusted()) {
				txtDealerHand->Text = gcnew String(myGame.ShowDealersHand(false).c_str());
				txtStatus->Text = gcnew String(myGame.ShowResults().c_str());
				gbAction->Visible = false;
			}
		}
	}

		   //Button stay event
	private: System::Void btnStay_Click(System::Object^ sender, System::EventArgs^ e) {
		gbAction->Visible = false;
		while (myGame.DealerContinues()) {
			txtDealerHand->Text = gcnew String(myGame.ShowDealersHand(false).c_str());
		}
		txtDealerHand->Text = gcnew String(myGame.ShowDealersHand(false).c_str());
		txtStatus->Text = gcnew String(myGame.ShowResults().c_str());
	}

		   //Play Again button event
	private: System::Void btnPlayAgain_Click(System::Object^ sender, System::EventArgs^ e) {
		myGame.ClearHands();
		txtPlayerHand->Text = gcnew String("");
		txtDealerHand->Text = gcnew String("");
		txtStatus->Text = gcnew String("");
		txtBet->Text = gcnew String("");
		myGame.ClearHands();
		gbBet->Visible = true;
		gbAction->Visible = false;
	}

		   //Button quit event
	private: System::Void btnQuit_Click(System::Object^ sender, System::EventArgs^ e) {
		myGame.EndGame();
		Application::Exit();
	}

		   //Form Load Event
	private: System::Void MyForm_Load(System::Object^ sender, System::EventArgs^ e) {
		if (!myGame.IsLogOpened()) {
			txtStatus->Text = gcnew String("Unable to open the log. You can still play without the log.");
		}
	}

	};
}