package com.taco.api.economy;

import java.util.UUID;

public interface Bank {
    double getBalance();

    void setBalance(double balance);

    UUID getUniqueId();

    String getName();

    void setName(String name);
}