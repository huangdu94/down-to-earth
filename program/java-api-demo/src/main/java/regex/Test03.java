package regex;

import java.util.Scanner;

public class Test03 {
    public static void main(String[] args) {
        /*
         * 程序启动后，要求用户输入一个计算表达式，
         * 经过程序运算输出结果：
         * 例如：
         * 用户输入：1+2
         * 输出：3
         *
         * 用户输入2*3
         * 输出：6
         *
         */
        Action(scanner());
    }

    public static String scanner() {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个计算表达式：");
        String line = scan.nextLine();
        scan.close();
        return line;
    }

    public static void Action(String line) {
        char[] opts = {'+', '-', '*', '/'};
        for (char opt : opts) {
            if (line.indexOf(opt) != -1) {
                String[] str = line.split("\\" + opt);
                int a = Integer.parseInt(str[0]);
                int b = Integer.parseInt(str[1]);
                System.out.println(line + "=" + computer(a, b, opt));
                return;
            }
        }
    }

    public static int computer(int a, int b, char opt) {
        switch (opt) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                throw new RuntimeException();
        }
    }
}
