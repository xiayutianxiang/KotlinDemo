package com.example.test;

import android.util.Log;

public class Calcute {

    private int mInt;

    public int add(){
        System.out.println("calcute add");
        return ++mInt;
    }

    public int reduce(){
        System.out.println("calcute reduce");
        return --mInt;
    }
}
