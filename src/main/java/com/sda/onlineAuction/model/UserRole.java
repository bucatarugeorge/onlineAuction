package com.sda.onlineAuction.model;

public enum UserRole {
    ROLE_BIDDER,
    ROLE_SELLER;

    public String getFriendlyName() {
        switch (this) {
            case ROLE_BIDDER: {
                return "Bidder";
            }
            case ROLE_SELLER: {
                return "Seller";
            }
            default: {
                return "Unknown";
            }

        }
    }
}
