/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.lady;

/**
 *
 * @author Merioksa
 */
public class Finances {
    private float currentMoney;
    private float totalMoneyEarned;
    private float totalMoneySpent;
    
    public Finances() {
        currentMoney = 0f;
        totalMoneyEarned = 0f;
        totalMoneySpent = 0f;
    }
    
    public float getCurrentMoney() {
        return currentMoney;
    }
    
    public float getTotalMoneyEarned() {
        return totalMoneyEarned;
    }
    
    public float getTotalMoneySpent() {
        return totalMoneySpent;
    }
    
    public void earnMoney(float amount) {
        currentMoney += amount;
        totalMoneyEarned += amount;
    }
    
    /**
     * @param amount amount of money to remove from the account
     * @return true if there was enough money, false otherwise. NOTE: if there's not enough money on the account, nothing is withdrawn
     */
    public boolean spendMoney(float amount) {
        if(currentMoney >= amount) {
            currentMoney -= amount;
            totalMoneySpent += amount;
        }
        
        return false;
    }
}
