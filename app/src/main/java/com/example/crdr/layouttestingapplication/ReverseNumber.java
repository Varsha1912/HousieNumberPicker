package com.example.crdr.layouttestingapplication;

import java.util.HashMap;
import java.util.Scanner;

public class ReverseNumber {

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter your number:");
        int num=sc.nextInt();
        int rem=0;
        int newnum=0;
        boolean flag=false;
        int firstDigit=0;

        if(num<=10000)
        {

            while (num!=0)
            {
                rem=num%10;
                newnum=newnum*10+rem;
                num=num/10;
               /* if(num==0)
                {
                    firstDigit=0;
                }
                else
                {
                    firstDigit=1;
                }*/

            }

      /*      if(firstDigit==0)
            {
                System.out.println("in if:");
                String n=newnum+"0";
                System.out.println("reverse:"+n);
            }
            else
            {
                System.out.println("in else:");
                System.out.println("reverse:"+newnum);
            }
        */
            System.out.println("reverse:"+newnum);


        }
        else {
            System.out.print("number is greater than 10000");
        }


    }
}
