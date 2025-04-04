package com.revature.stay.models;

public enum RoomStatus {
    VACANT,
    RESERVED,
    OCCUPIED;

    public static boolean isContained(String value){
        boolean result = false;
        for(RoomStatus status : values()){
            if(status.name().equals(value)){
                result = true;
                break;
            }
        }
        return result;
    }
}