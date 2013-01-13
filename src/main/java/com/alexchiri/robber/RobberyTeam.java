package com.alexchiri.robber;

import com.alexchiri.bank.Bank;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Alexandru Chiritescu
 * 13-1-13
 */
public class RobberyTeam {
    private final static int ROBBER_CAPACITY = 2;
    private int size;
    private String name;

    public RobberyTeam(String name, int size) {
        System.out.println("Team " + name + " is ready to go!");
        this.size = size;
        this.name = name;
    }

    public void robb(Bank bank) {
        boolean robberyComplete = false;
        while(!robberyComplete) {
            String[] moneyToRobb = bank.openSafeUnderGunpoint();
            if(moneyToRobb != null && moneyToRobb.length > 0) {
                int randomStealingCapacity = new Random().nextInt(moneyToRobb.length);
                System.out.println("Team " + name + " can only steal " + randomStealingCapacity + " money bags.");
                moneyToRobb = Arrays.copyOfRange(moneyToRobb, 0, randomStealingCapacity);

                Logistics logistics = new Logistics(moneyToRobb, bank);
                ForkJoinPool forkJoinPool = new ForkJoinPool(size);
                forkJoinPool.invoke(logistics);

                bank.wrapUp();
                robberyComplete = true;
            } else {
                System.out.println("No more money to steal! Waiting for bank to get more money!");
            }
        }


    }


    class Logistics extends RecursiveAction {
        private String[] moneyBagsToRobb;
        private Bank bank;

        Logistics(String[] moneyBagsToRobb, Bank bank) {
            this.moneyBagsToRobb = moneyBagsToRobb;
            this.bank = bank;
        }

        @Override
        protected void compute() {
            if(moneyBagsToRobb.length <= ROBBER_CAPACITY) {
                bank.removeMoneyFromSafe(moneyBagsToRobb);
            } else {
                int half = moneyBagsToRobb.length / 2;
                Logistics firstHalf = new Logistics(Arrays.copyOfRange(moneyBagsToRobb, 0, half), bank);
                Logistics secondHalf = new Logistics(Arrays.copyOfRange(moneyBagsToRobb, half, moneyBagsToRobb.length), bank);
                invokeAll(firstHalf, secondHalf);
            }
        }
    }
}
