package com.example.smartfactory.network.VO;

public class LoginVO {
       public String ID;
       public String PW;
       public int env;

    public LoginVO(String ID, String PW, int env) {
        this.ID = ID;
        this.PW = PW;
        this.env = env;
    }
}