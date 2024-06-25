/*
Stores an auction house's info
 */

package DistributedAuction;

import java.io.Serializable;
import java.util.List;

public record AuctionHouseInfo(String ip, int port, int houseID, String name) implements Serializable {
    /**
     * Finds the index of an auction house
     * @param houseInfos list of AuctionHouseInfo objects
     * @param houseID is of the auction house
     * @return index of the auction house
     */
    public static int indexOf(List<AuctionHouseInfo> houseInfos, int houseID) {
        int size = houseInfos.size();

        for (int i = 0; i < size; i++) {
            AuctionHouseInfo houseInfo = houseInfos.get(i);
            if (houseInfo.houseID() == houseID) return i;
        }

        return -1;
    }


    /**
     * Determines if this house is the same as param house
     * @param houseID is the house being compared
     * @return is the result of the comparison
     */
    public boolean matches(int houseID) {
        return this.houseID == houseID;
    }

    /**
     * Determines if this house is the same as param house
     * @param houseInfo is the house being compared
     * @return is the result of the comparison
     */
    public boolean matches(AuctionHouseInfo houseInfo) {
        return matches(houseInfo.houseID());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof AuctionHouseInfo houseInfo)) return false;

        return matches(houseInfo);
    }
}
