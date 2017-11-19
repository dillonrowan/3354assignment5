package shippingstore;

import java.io.*;
import java.util.*;

public class Validate {

  public Validate() {
  }

  public static boolean isPosInt(String s){
        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))){
                 return false;
            }
        }
        return true;
  }

  public static boolean isPositive(String s) {
    Double number = Double.parseDouble(s);
    if(number < 0)
      return false;

    return true;
  }
}
