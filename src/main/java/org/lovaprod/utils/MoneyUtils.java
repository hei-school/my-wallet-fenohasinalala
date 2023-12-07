package org.lovaprod.utils;

public class MoneyUtils {
    public static boolean isAmountValid(double amount){
        if (amount<0){
            System.out.println("Amount must be > 0"+MONEY_UNIT);
            return false;
        }
        return true;
    }

    public static boolean isAmountValid(double amount, double balance){
        if (!isAmountValid(amount)){
            return false;
        }
        if (amount > balance){
            System.out.println("amount must be < Balance: "+balance+MONEY_UNIT);
            return false;
        }
        return true;
    }

    public static boolean isAmountValid(double amount, double balance, double maxTransaction){
        if (!isAmountValid(amount,balance)){
            return false;
        }
        if (amount > maxTransaction){
            System.out.println("amount must be < maxTransaction: "+maxTransaction+MONEY_UNIT);
            return false;
        }
        return true;
    }

    public static final String MONEY_UNIT = " MGA";
}
