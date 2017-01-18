package com.wy;

public class Sort {
    private static char[] NUM = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};// 所有的字母数

    public char[] randNum(int num) {// 随机选取num个数
        char[] s = new char[num];// 定就num个长度的字符数组
        int i = 0;// 计算循环数
        int n = 0;// 控制循环
        while (i < num) {
            char c = NUM[(int) (Math.random() * NUM.length)];// 随机出一个字符,将其赋给c
            for (int j = 0; j < s.length; j++) {// 这个循环是除去重的字符
                if (s[j] == c) {// 如果有重复的则终止循环
                    n = 1;// 将n=1
                    break;// 终止for循环
                }
            }
            if (n == 1) { // 如果n==1,则进入下一个while循环
                n = 0; // 将n还原 n=0;
                continue; // 进入下一循环
            } else { // 如果n不等于1,刚将字符c赋给s[i]
                n = 0; // 将n还原 n=0;
                s[i] = c; // 将字符c赋给s[i]
                i++;
            }
        }
        return s;// 返回含有num个不重复的字符数组
    }

    public void sortNum(int num) {//排列出所有的可能
        char[] s = randNum(num).clone();//clone一个randNum(num);
        System.out.println("***" + s.length);//打印s的长度
        for (int i = 0; i < s.length; i++) {//排列循环
            for (int j = 0; j < s.length - 1; j++) {
                char t;
                t = s[j];
                s[j] = s[j + 1];
                s[j + 1] = t;
                for (int m = 0; m < s.length; m++) {//打印排列
                    System.out.print(s[m]);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] a) {
        Sort s = new Sort();
        s.sortNum(5);
    }
}
