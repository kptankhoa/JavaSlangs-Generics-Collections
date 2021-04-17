package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class io {
    public static void main(String[] args) {
//        Random rgen = new Random();  // Random number generator
//        int[] cards = new int[4];
//
////--- Initialize the array to the ints 0-51
//        for (int i=0; i<cards.length; i++) {
//            cards[i] = i;
//        }
//
////--- Shuffle by exchanging each element randomly
//        for (int i=0; i<cards.length; i++) {
//            int randomPosition = rgen.nextInt(cards.length);
//            int temp = cards[i];
//            cards[i] = cards[randomPosition];
//            cards[randomPosition] = temp;
//        }
//        for (int i = 0; i < cards.length; i++) {
//            System.out.println(cards[i]);
//        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {

            System.out.println("nhap:");
            String str = br.readLine();
            while(!checkAnsInput(str)){
                System.out.print("Nhập không hợp lệ! Vui lòng nhập lại (A/B/C/D): ");
                str = br.readLine();
            }
            System.out.println(str.charAt(0));

            System.out.println("xong");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private static boolean checkAnsInput(String str) {
        if(str.length()>1)
            return false;
        int c = str.charAt(0);
        if (c<65||c>68)
            return false;
        return true;
    }
}