package com.alexchiri;

import com.alexchiri.bank.Bank;
import com.alexchiri.bank.MoneyBag;
import com.alexchiri.robber.Heist;

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
        int randomNumber = Math.abs(new Random().nextInt(10));
        System.out.println("Random number of money bags to load the safe with: " + randomNumber);

        for(int i = 0; i< randomNumber; i++) {
            moneyBags.add(new MoneyBag());
        }

        Bank bank = new Bank(moneyBags, "Cool bank");

        Heist heist = new Heist("Kansas", 2, bank);
        new Thread(heist).start();

        Heist heist2 = new Heist("Charlie's Angels", 3, bank);
        new Thread(heist2).start();

        Heist heist3 = new Heist("Lottery ticket", 3, bank);
        new Thread(heist3).start();

        Heist heist4 = new Heist("Who wants to be a millionaire?", 4, bank);
        new Thread(heist4).start();
    }
}
