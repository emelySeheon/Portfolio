/*
Contains possible bit statuses
 */

package DistributedAuction;

import java.io.Serializable;

public enum BidStatus implements Serializable {
    ACCEPTED, REJECTED, OUTBID, WINNER, LOSER
}
