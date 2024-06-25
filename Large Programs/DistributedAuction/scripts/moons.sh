#!/bin/bash

houses=1
agents=1
moons=("chawla" "phoebe" "sycorax" "brown" "hemera" "deimos" "ramon" "prospero" "phobos" "callisto" "leda" "titan" "europa") #"ganymede"
auctionHouses=("Acme" "GarrysStore" "NASASurplus" "Nat20Emporium" "TrekyShop" "WeasleysWiz")

while getopts "h:a:" option; do
    case "${option}" in
        h) houses=${OPTARG};;
        a) agents=${OPTARG};;
        *) echo "Invalid option. Use -h for number of houses and -a for number of agents. Use --help for usage."; exit 1;;
    esac
done

if [[ $houses -lt 1 || $agents -lt 1 ]]; then
    echo "Invalid argument value. Values must be greater than or equal to 1."
    exit 1
fi

if [[ "${1:-}" == "--help" ]]; then
    echo "Usage: ./script.sh [-h NUM] [-a NUM]"
    echo "  -h NUM      Number of houses to echo. Default is 1 if not specified."
    echo "  -a NUM      Number of agents to echo. Default is 1 if not specified."
    exit 0
fi

gnome-terminal --tab --title="Bank@ganymede" -- sh -c "ssh -o StrictHostKeyChecking=no ganymede.cs.unm.edu ' ~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ./jars/Bank.jar 9090' ;  bash" 

# Delay for the bank to startup
sleep 3

for ((i=0; i<$houses; i++)); do
    available_names=( "${names[@]}" )
    for ((j=0; j<$i; j++)); do
        used_name="${used_names[$j]}"
        available_names=( "${available_names[@]/$used_name}" )
    done
    export moon=${moons[$i]}
    export ah_name=${auctionHouses[$i]}
    export i
    echo "$moon house" 909$i ${auctionHouses[$i]}
    gnome-terminal --tab --title="AuctionHouse@$moon" -- sh -c "ssh -o StrictHostKeyChecking=no $moon.cs.unm.edu ' ~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ./jars/AuctionHouse.jar ganymede.local 9090 $ah_name 909$i <./AuctionHouse/$ah_name' ;  bash"
    
done
for ((j=0; j<$agents; j++)); do
    export moon=${moons[$((i+j))]}
    echo "$moon agent"
    gnome-terminal --tab --title="AgentGUI@$moon" -- sh -c "ssh -o StrictHostKeyChecking=no $moon.cs.unm.edu -X '~/bin/jdk-17.0.7/bin/java  --module-path ~/bin/javafx-sdk-20/lib/ --add-modules javafx.controls,javafx.fxml -jar ./jars/AgentGUI.jar ganymede.local 9090'  ;  bash"
done

