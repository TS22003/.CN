//ASCII
import java.util.Scanner;
public class Ascii {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a Character : ");
char ch = sc.next().charAt(0);
int ascii = (int) ch;
System.out.println("The ASCII Value of " + ch + " is " + ascii);
}
}
//Fibonacci
import java.util.Scanner;
public class Fibb {
public static void main(String args[]) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a Number : ");
int n = sc.nextInt();
int a = 0, b = 1, c;
if (n <= 0) {
System.out.println("Invalid Range");
System.exit(0);
}
if (n == 1)
System.out.println(a);
else if (n == 2)
System.out.println(a + " " + b + " ");
else {
System.out.print(a + " " + b + " ");
for (int i = 2; i < n; i++) {
c = a + b;
System.out.print(c + " ");
a = b;
b = c;
}
System.out.println();
}
}
}
//ODD EVEN
import java.util.Scanner;
public class OddEven {
public static void main(String args[]) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a Number : ");
int num = sc.nextInt();
if (num % 2 == 0) {
System.out.println(num + " is an Even Number");
} else {
System.out.println(num + " is an Odd Number");
}
}
}
//Prime
import java.util.Scanner;
public class Prime {
public static void main(String args[]) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a Number : ");
int num = sc.nextInt();
int flag = 1;
if (num == 0 || num == 1) {
System.out.println(num + " is not a Prime");
System.exit(0);
}
for (int i = 2; i <= num / 2; i++) {
if (num % i == 0) {
flag = 0;
break;
}
}
if (flag == 1) {
System.out.println(num + " is a Prime");
} else {
System.out.println(num + " is not a Prime");
}
}
}
//Reverse Number
import java.util.Scanner;
public class ReverseNum {
public static void main(String args[]) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a Number : ");
int num = sc.nextInt();
int rnum = 0, r;
while (num > 0) {
r = num % 10;
rnum = rnum * 10 + r;
num /= 10;
}
System.out.println("Reversed Number : " + rnum);
}
}
