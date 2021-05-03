package org.gptccherthala.virtualqueue.user;

public class UserDatabase {
    public String name;
    public long phoneNumber;
    public int pinCode;

    public UserDatabase() {
    }

    public UserDatabase(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public UserDatabase(String name, long phoneNumber, int pinCode) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
    }
}
