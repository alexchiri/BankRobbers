package com.alexchiri;

import com.alexchiri.bank.Bank;
import com.alexchiri.bank.MoneyBag;
import com.alexchiri.robber.RobberyTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Alexandru Chiritescu
 * 13-1-13
 */
public class CrimeSetup {
    public static void main(String[] args) {
        List<MoneyBag> moneyBags = new ArrayList<MoneyBag>();
        int randomNumber = Math.abs(new Random().nextInt(100));
        System.out.println("Random number of money bags to load the safe with: " + randomNumber);

        for(int i = 0; i< randomNumber; i++) {
            moneyBags.add(new MoneyBag());
        }

        Bank bank = new Bank(moneyBags, "Cool bank");

        RobberyTeam team = new RobberyTeam("Skeets", 5);
        team.robb(bank);
    }
}
