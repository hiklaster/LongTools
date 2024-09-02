package com.hikst;

import com.hikst.string.StringUtils;

import java.util.Objects;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        String s = "哈哈我正邦！yes..";
//        System.out.println(StringUtils.getLength(s));
//        System.out.println(s.length());
//        System.out.println(StringUtils.getLength(new StringBuffer('哈')));
        char c = '哈';
        System.out.println(StringUtils.getIndex(s, c));
        System.out.println(s.indexOf(c));
        String s1 = Objects.requireNonNull(s);
    }
}
