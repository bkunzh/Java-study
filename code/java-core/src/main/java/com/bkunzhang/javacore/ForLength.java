package com.bkunzhang.javacore;

import java.util.ArrayList;
import java.util.List;

/**
 * 4-no.length()这种放for循环中判断循环次数，如果在循环中no.length()变化会导致错误
 * @author bingkun_zhang
 * @date 2020/5/26
 */
public class ForLength {
    public static void main(String[] args) {
        String no = "1";
        for (int i=0; i<4-no.length(); ++i) {  // 4-no.length()  xx  .length()在变化，4-no.length在变小，导致循环次数变小
            no = "0" + no;
        }
        System.out.println(no); // 001

        no = "1";
        int n = 4-no.length();
        for (int i=0; i<n; ++i) {
            no = "0" + no;
        }
        System.out.println(no); // 0001

        no = "1";
        while (no.length() < 4) {
            no = "0" + no;
        }
        System.out.println(no);

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        for (int i=0; i<4-list.size(); ++i) { // 4-list.size()  xx
//            list.add(i);
//        }
//        System.out.println(list); // [1, 0, 1]
//
//        list = new ArrayList<>();
//        list.add(1);
//        n = 4-list.size();
//        for (int i=0; i<n; ++i) { // 4-list.size()  xx
//            list.add(i);
//        }
//        System.out.println(list); // [1, 0, 1]
    }
}
