package com.alexchiri.bank;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Alexandru Chiritescu
 * 13-1-13
 */
public class Safe extends ConcurrentHashMap<String, MoneyBag> {
    public Safe(List<MoneyBag> moneyBags) {
        if(moneyBags != null && moneyBags.size() > 0) {
            System.out.println("Loading safe with " + moneyBags.size() + " money bags!");
            for(MoneyBag money:moneyBags) {
                put(UUID.randomUUID().toString(), money);
            }
        }
    }
}
