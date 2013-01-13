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
public class Heist{
    private final static int ROBBER_CAPACITY = 2;
    private int teamSize;
    private String name;
    private Bank bank;

    public Heist(String name, int size, Bank bank) {
        System.out.println("Team " + name + " is ready to go!");
        this.teamSize = size;
        this.name = name;
        this.bank = bank;
    }

    public void robbBank() {
        boolean robberyComplete = false;
        while(!robberyComplete) {
            String[] moneyToRobb = bank.openSafeUnderGunpoint();
            if(moneyToRobb != null && moneyToRobb.length > 0) {
                int randomStealingCapacity = new Random().nextInt(moneyToRobb.length);
                System.out.println("Team " + name + " can only steal " + randomStealingCapacity + " money bags.");
                moneyToRobb = Arrays.copyOfRange(moneyToRobb, 0, randomStealingCapacity);

                Logistics logistics = new Logistics(moneyToRobb, bank);
                ForkJoinPool forkJoinPool = new ForkJoinPool(teamSize);
                forkJoinPool.invoke(logistics);

                bank.wrapUp();
                robberyComplete = true;
            } else {
                System.out.println("No more money to steal! Waiting for the bank to get more money!");
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
