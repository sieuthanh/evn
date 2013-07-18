package com.lib;

import java.io.UnsupportedEncodingException;

import java.math.BigInteger;

public class encode {
  public static String crypt(String key, String email){
      String result = "";
      int j = 0;
      for(int i=0;i<key.length();i++){
          result += (char)((int)email.charAt(j)%256 ^ (int)key.charAt(i)%256);
          j++; if(j>=email.length()) j=0;
      }
      return new String(result);
  }
 }
