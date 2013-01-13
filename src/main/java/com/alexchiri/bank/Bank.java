package com.alexchiri.bank;

import java.util.List;

/**
 * Alexandru Chiritescu
 * 13-1-13
 */
public class Bank {
    private Safe safe;
    private String name;

    public Bank(List<MoneyBag> moneyBags, String name) {
        System.out.println("Loading bank '" + name + "' with " + moneyBags.size() + " money bags!");
        this.safe = new Safe(moneyBags);
        this.name = name;
    }

    public String[] openSafeUnderGunpoint() {
        System.out.println("(Bank:" + name + ") Opening safe under gunpoint for " + Thread.currentThread().getName());
        return safe.keySet().toArray(new String[0]);
    }

    public void removeMoneyFromSafe(String[] bagTags) {
        if(bagTags != null && bagTags.length > 0) {
            System.out.println("(Bank:" + name + ") Removing " + bagTags.length + " money bags from the safe for " + Thread.currentThread().getName());
            for(String bagTag: bagTags) {
                safe.remove(bagTag);
            }
        }
    }

    public void wrapUp() {
        System.out.println("(Bank:" + name + ") Robbery ended and in the Safe we have " + safe.size() + " money bags left!");
    }
}
