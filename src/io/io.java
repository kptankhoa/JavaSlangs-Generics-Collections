package io;

import java.util.Collection;
import java.util.HashMap;

public class io{
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        map.put(1, "a");
        map.put(2, "a a");
        map.put(3, "fucku");
        map.put(4, "fuckkkkk");
        map.put(5, "aaaaaa");
        map.put(8, "baaass");
        map.put(10, "yo yoy o");
        map.put(19, "im kp man");
        map.put(13, "shitttt");
        map.put(12, "ayooooooo");

        System.out.printf(map.toString());
        System.out.println(map.values());
    }
}