package com.hikst.string;

import java.lang.reflect.Field;
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

    /**
     * 判断字符串内容是否为空
     * @param str 传入的字符串，不能为null
     * @return 判断结果
     */
    public static boolean isEmpty(String str){
        if(str == null) throw new IllegalArgumentException("String参数不能为null");
        return str.length() == 0;
    }

    /**
     * 判断字符串是否以某一字符串开头
     * @param str 字符串内容
     * @param prefix 开头的字符串
     * @return 是否以某一字符串开头的判断结果
     */
    public static boolean startsWith(String str,String prefix){
        char[] chs1 = str.toCharArray();
        char[] chs2 = prefix.toCharArray();
        boolean result = true;
        for (int i = 0; i < chs2.length; i++) {
            if(chs1[i] != chs2[i]){
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 判断字符串是否以某段内容结尾
     * @param str 需判断字符串
     * @param suffix 结尾字符串
     * @return 判断结果
     */
    public static boolean endsWith(String str,String suffix){
        char[] chs1 = str.toCharArray();
        char[] chs2 = suffix.toCharArray();
        boolean result = true;
        for (int i = chs2.length - 1,j = chs1.length - 1; i >= 0; i--,j--) {
            if(chs1[j] != chs2[i]){
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 判断两个字符串内容是否相等
     * @param first 第一个字符串
     * @param second 第二个字符串
     * @return 是否相等的比较结果
     */
    public static boolean equals(String first,String second){
        if(first.length() != second.length()) return false;
        char[] chs1 = first.toCharArray();
        char[] chs2 = second.toCharArray();
        boolean result = true;
        for (int i = 0; i < chs1.length; i++) {
            if(chs1[i] != chs2[i]){
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 忽略大小写判断两个字符串内容是否相等(只判断26个英文字母)
     * @param first 第一个字符串
     * @param second 第二个字符串
     * @return 是否相等的判断结果
     */
    public static boolean equalsIgnore(String first,String second){
        if(first.length() != second.length()) return false;
        char[] chs1 = first.toCharArray();
        char[] chs2 = second.toCharArray();
        boolean result = true;
        for (int i = 0; i < chs1.length; i++) {
            if(chs1[i] != chs2[i]){
                if(!equalsIgnoreChar(chs1[i],chs2[i])){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 判断字符是否属于26个大小写英文字母
     * @param ch 需判断的字符
     * @return 判断结果
     */
    private static boolean isLetter(char ch){
        //a-z 65-90 A-Z 97-122 97-65=32
        return (ch >= 'a' && ch <= 'z') || (ch >='A' && ch <= 'Z');
    }

    /**
     * 判断两个字符是否是相应的大小写对应字母
     * @param ch1 第一个字符
     * @param ch2 第二个字符
     * @return 是/否对应的大小写字母
     */
    private static boolean equalsIgnoreChar(char ch1,char ch2){
        if(!isLetter(ch1) || !isLetter(ch2)) return false;
        return ch1 - ch2 == 32;
    }

    /**
     * 把字符数组转换为字符串
     * @param chs 字符数组
     * @return 转换后的字符串
     */
    public static String valueOf(char[] chs){
//        return new String(chs);
        return String.valueOf(chs);
    }

    /**
     * 把int数字转为字符串
     * @param i int型数字
     * @return 转换后的字符串
     */
    public static String valueOf(int i){
        return Integer.toString(i);
    }

    /**
     * 把浮点型float数字转为字符串
     * @param f 单精度浮点数
     * @return 转换后的字符串
     */
    public static String valueOf(float f){
        return Float.toString(f);
    }

    /**
     * 把双精度浮点数转为字符串
     * @param d 双精度浮点数
     * @return 转换后的字符串
     */
    public static String valueOf(double d){
        return Double.toString(d);
    }

    /**
     * 单个字符转为字符串
     * @param c 单个字符
     * @return 转换后的字符串
     */
    public static String valueOf(char c){
        return Character.toString(c);
    }

    /**
     * 长整型转为字符串
     * @param l 长整型数字
     * @return 转换后的字符串
     */
    public static String valueOf(long l){
        return Long.toString(l);
    }

    /**
     * 短整型数字转为字符串
     * @param s 短整型数字
     * @return 转换后的字符串
     */
    public static String valueOf(short s){
        return Short.toString(s);
    }

    /**
     * 布尔类型转为字符串
     * @param b 布尔值
     * @return 转换后的字符串
     */
    public static String valueOf(boolean b){
        return Boolean.toString(b);
    }

    /**
     * 对象转为字符串
     * @param o 需转换的对象
     * @return null或调用传入对象的toString方法
     */
    public static String valueOf(Object o){
        return o == null ? "null" : o.toString();
    }

    /**
     * 将字符串转为字符数组
     * @param str 需转换的字符串
     * @return 被转换成功的字符数组
     * @throws NoSuchFieldException String类中没有对应字段名
     * @throws IllegalAccessException 字段的类对象获取对象不正确，不是要求的String类型
     */
    public static char[] toCharArray(String str) throws NoSuchFieldException, IllegalAccessException {
        //通过反射获取String内部保存的数据结构中的数据value
        Class<? extends String> clazz = str.getClass();
        Field v = clazz.getDeclaredField("value");
        v.setAccessible(true);
        char[] result = (char[])v.get(str);
        return result;
        // 最简单方式 return str.toCharArray()
    }
}
