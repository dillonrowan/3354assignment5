package shippingstore;

import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    return s.matches("\\d+(\\.\\d+)?");
  }

  public static Date parseDate(String maybeDate) {
    Date date = null;
    String format = "MM/dd/yyyy";
    String reFormat = Pattern.compile("d+|M+").matcher(Matcher.quoteReplacement(format)).replaceAll("\\\\d{1,2}");
    reFormat = Pattern.compile("y+").matcher(reFormat).replaceAll("\\\\d{4}");
    if ( Pattern.compile(reFormat).matcher(maybeDate).matches() ) {
      SimpleDateFormat sdf = (SimpleDateFormat)DateFormat.getDateInstance();
      sdf.applyPattern(format);
      sdf.setLenient(false);
      try { date = sdf.parse(maybeDate); } catch (ParseException e) { }
    }
    return date;
  }
}
