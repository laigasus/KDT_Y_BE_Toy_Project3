package com.example.kdt_y_be_toy_project2.global.config;

import com.example.kdt_y_be_toy_project2.global.exception.InvalidPrincipalDetailsException;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;

public class CheckUserLogined {
    public static void checkUserLogined(PrincipalDetails principalDetails) {
        if(principalDetails ==null){
            throw new InvalidPrincipalDetailsException();
        }
    }
}
