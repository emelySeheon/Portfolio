#!/bin/bash

## Clean up just in case something is running
killall ~/bin/jdk-17.0.7/bin/java

gnome-terminal --tab --title="Bank" -- sh -c "~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/Bank.jar 9090;  bash" 

# Delay for the bank to startup
sleep 1

gnome-terminal --tab --title="AcmeAuctionHouse" -- sh -c " ~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/AuctionHouse.jar localhost 9090 Acme 9091 <../res/AuctionHouse/Acme;  bash"

gnome-terminal --tab --title="GarrysStoreAuctionHouse" -- sh -c " ~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/AuctionHouse.jar localhost 9090 GarrysStore 9092 <../res/AuctionHouse/GarrysStore;  bash"

gnome-terminal --tab --title="NASASurplusAuctionHouse" -- sh -c " ~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/AuctionHouse.jar localhost 9090 NASASurplus 9093 <../res/AuctionHouse/NASASurplus;  bash"

gnome-terminal --tab --title="Nat20EmporiumAuctionHouse" -- sh -c " ~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/AuctionHouse.jar localhost 9090 Nat20Emporium 9094 <../res/AuctionHouse/Nat20Emporium;  bash"

gnome-terminal --tab --title="TrekyShopAuctionHouse" -- sh -c " ~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/AuctionHouse.jar localhost 9090 TrekyShop 9095 <../res/AuctionHouse/TrekyShop;  bash"

gnome-terminal --tab --title="WeasleysWizAuctionHouse" -- sh -c " ~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/AuctionHouse.jar localhost 9090 WeasleysWiz 9096 <../res/AuctionHouse/WeasleysWiz;  bash"

gnome-terminal --tab --title="AgentGUI " -- sh -c "~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/AgentGUI.jar localhost 9090 ;  bash"

gnome-terminal --tab --title="AgentGUI2 " -- sh -c "~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ../jars/AgentGUI.jar localhost 9090 ;  bash"
