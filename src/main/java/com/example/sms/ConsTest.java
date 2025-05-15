package com.example.sms;

/**
 * @author Santosh Kumar
 * @Created 16-05-2024
 */
public class ConsTest {

    public static void main(String[] args){

        C c = new D("Hello");

    }







}

class C {

    public C(){
        System.out.println("-------------Default Cons---------------------");
    }

    public  C(String s){

        System.out.println("-------------C---------------------");

    }


}


class D extends C{

    public  D(String s){
        super();
        //super("j");

        System.out.println("-------------D---------------------");

    }


}
