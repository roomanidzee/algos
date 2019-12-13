package com.romanidze.algos.nineth;

import java.math.BigInteger;
import java.util.List;

/**
 * 13.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class MillerRabbin {

    private int[] getTestPrimes(BigInteger num){
        if (num.compareTo(new BigInteger("3474749660383")) >= 0) {
            return new int[]{2, 3, 5, 7, 11, 13, 17};
        }
        if (num.compareTo(new BigInteger("2152302898747")) >= 0) {
            return new int[]{2, 3, 5, 7, 11, 13};
        }
        if (num.compareTo(new BigInteger("118670087467")) >= 0) {
            return new int[]{2, 3, 5, 7, 11};
        }
        if (num.compareTo(new BigInteger("25326001")) >= 0) {
            return new int[]{2, 3, 5, 7};
        }
        if (num.compareTo(new BigInteger("1373653")) >= 0) {
            return new int[]{2, 3, 5};
        }
        return new int[]{2, 3};
    }

    private boolean compositeCheck(int a, BigInteger d, BigInteger n, BigInteger s){

        BigInteger aValue = BigInteger.valueOf(a);

        if(aValue.modPow(d, n).equals(BigInteger.ONE)){
            return false;
        }

        int i = 0;
        while (BigInteger.valueOf(i).compareTo(s) < 0) {

            BigInteger first = aValue.modPow(BigInteger.valueOf(2).pow(i).multiply(d), n);
            BigInteger second = n.subtract(BigInteger.ONE);

            if (first.equals(second)) {
                return false;
            }

            i++;
        }

        return true;

    }

    public boolean isPrime(BigInteger num, int precision){

        if (num.compareTo(new BigInteger("341550071728321")) >= 0) {
            return num.isProbablePrime(precision);
        }

        int intValue = num.intValue();

        if(List.of(1, 4, 6, 8).contains(intValue)){
            return false;
        }

        if(List.of(2, 3, 5, 6).contains(intValue)){
            return true;
        }

        int[] testPrimes = getTestPrimes(num);

        BigInteger d = num.subtract(BigInteger.ONE);
        BigInteger s = BigInteger.ZERO;

        while(d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)){
            d = d.shiftRight(1);
            s = s.add(BigInteger.ONE);
        }

        for(int a: testPrimes){
            if(compositeCheck(a, d, num, s)){
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {

        String num = "170141183460469231731687303715884105727";
        BigInteger bigNum = new BigInteger(num);

        System.out.println("Число: " + bigNum.toString());
        MillerRabbin millerRabbinTest = new MillerRabbin();

        boolean result = millerRabbinTest.isPrime(bigNum, 100);

        if(result){
            System.out.println("Простое");
        }else{
            System.out.println("Не является простым");
        }


    }

}
