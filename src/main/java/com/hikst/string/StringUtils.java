package com.hikst.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 *用于处理字符串内容
 * @author john.xie
 * @date 2024-09-01 20:39
 */
public class StringUtils {
    /**
     * 获取字符串的长度
     * @param ch
     * @return 字符串的长度，如果不是String类型，返回-1
     */
    public static int getLength(CharSequence ch){
        if(ch instanceof String){
            return ch.length();
        }
        return -1;
    }

    /**
     * 获取字符串中一个字符的角标位
     * @param str
     * @param ch
     * @return 字符角标位，如不存在或str为null或字符串长度为0时或字符为null时返回-1
     */
    public static int getIndex(String str,Character ch){
        if(str == null || str.length() == 0 || ch == null) return -1;
        //把字符串转换为字符数组
        char[] chars = str.toCharArray();
        int index = -1;
        for (int i = 0; i < chars.length; i++) {
            if(ch.equals(chars[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 在字符串中查找能匹配上的字符串开始角标
     * @param str
     * @param ch
     * @return
     */
    public static int getIndex(String str,String ch){
        if(str == null || str.length() == 0 || ch == null || ch.length() == 0) return -1;

        //不使用正则匹配，如何实现？
        char[] chs1 = str.toCharArray();
        char[] chs2 = ch.toCharArray();
        boolean flag = false;
        for (int i = 0; i < chs1.length; i++) {

            if(chs1[i] == chs2[0]){
                int count = 0;
                for (int j = 0,k = i; j < chs2.length; j++,k++) {
                    if(chs2[j] != chs1[k]) break;
                    count++;
                }
                if(count == chs2.length) return i;
            }
        }
        return -1;
    }

    /**
     * 在字符串中查找能匹配上的字符串开始角标，使用正则实现
     * @param str
     * @param ch
     * @return
     */
    public static int getIndexOfPattern(String str,String ch){
        if(str == null || str.length() == 0 || ch == null || ch.length() == 0) return -1;
        //简单的方法，用正则去匹配
//        boolean flag = Pattern.matches(ch, str);
        Pattern p = Pattern.compile(ch);
        Matcher m = p.matcher(str);
        boolean flag = m.matches();
        return flag?m.start():-1;
    }

    /**
     * 获取字符串中某一角标位的字符
     * @param str
     * @param index
     * @return 角标位的字符
     */
    public static String getStringOfIndex(String str,int index){
        if(index < 0 || str == null || str.length() == 0) throw new IllegalArgumentException("传入的参数异常");//要么return null或者空字符
        char[] chs = str.toCharArray();
        if(index > str.length() -1) return "";

        return String.valueOf(chs[index]);
    }

    /**
     * 获取字符串中从start至end角标位的字符串
     * @param str
     * @param start
     * @param end
     * @return 对应角标位的字符串
     */
    public static String getStringOfIndex(String str,int start,int end){
        if(str == null || str.length() == 0 || start < 0 || start > end || end > str.length()-1) throw new IllegalArgumentException("传入的参数异常");
        //方式一
        char[] chs = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            if(i >= start && i <= end){
                sb.append(chs[i]);
            }
        }
        return sb.toString();
        /*
        方式二： return str.subString(start,end);
         */
    }

    /**
     * 判断字符串中是否包含对应的子串
     * @param str 字符串
     * @param subStr 子串
     * @return 包含结果
     */
    public static boolean contains(String str,String subStr){
        if(str == null || str.length() == 0 || subStr == null || subStr.length() == 0) return false;
        return Pattern.matches(subStr,str);
    }
}
