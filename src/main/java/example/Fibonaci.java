package example;

import java.util.Scanner;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/9 - 10:43
 **/

//斐波那契数列
public class Fibonaci {
    public static int fibonaci(int n){
        if (n == 1){
            return 0;
        }
        if (n == 2){
            return 1;
        }
        return fibonaci(n-1) + fibonaci(n-2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int fib = fibonaci(n);
        System.out.println(fib);
    }
}
