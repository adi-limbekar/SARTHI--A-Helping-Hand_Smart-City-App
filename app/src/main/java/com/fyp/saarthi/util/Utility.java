package com.fyp.saarthi.util;

public class Utility {

    public static int checkPhoneNumber(String PhoneNumber){
      int flag=0;
      String PhonePattern = "[6-9]{1}[0-9]{9}";
      if(PhoneNumber.matches(PhonePattern)){
          flag=1;
      }
      else{
          flag=0;
      }
      return flag;
    }

    public static int checkEmail(String Email){
        int flag=0;
        String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.{1}+[a-z]+";
        if (Email.matches(EmailPattern)) {
           flag=1;
        }
        else {
            flag=0;
        }
       return flag;
    }

    public static int checkPassword(String Password){
        int flag=0;
        String PassPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        if(Password.matches(PassPattern)){
            flag=1;
        }
        else{
            flag=0;
        }
        return flag;
    }
}


