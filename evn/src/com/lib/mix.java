package com.lib;
import com.exp.EException;
import com.inf.ISysMsg;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class mix 
{
    private static Log log = LogFactory.getLog(mix.class);
    public mix()
    {
    }
    public static Boolean StringToBoolean(String value)
    {
      return new Boolean(value.equalsIgnoreCase("TRUE"));
    }
    public static Integer StringToInteger(String value)
    {
      return StringToInteger(value,10);
    }
    public static Integer StringToInteger(String value,Integer valueDefault)
    {
      Integer result = StringToInteger(value);
      if(result==null) result = valueDefault;
      return result;
    }
    public static Double StringToDouble(String value,Double valueDefault)
    {
      Double result = StringToDouble(value);
      if(result==null) result = valueDefault;
      return result;
    }
    public static Long StringToLong(String value,Long valueDefault)
    {
      Long result = StringToLong(value);
      if(result==null) result = valueDefault;
      return result;
    }
    public static Float StringToFloat(String value,Float valueDefault)
    {
      Float result = StringToFloat(value);
      if(result==null) result = valueDefault;
      return result;
    }
    public static Integer StringToInteger(String value,int radix)
    {
      final String LOCATION = "->StringToInteger(" + value + ")";
      Integer result=null;
        try {
            result = Integer.valueOf(value,radix);
        } catch (NumberFormatException ex){
            if(AppConfigs.APP_SAVE_LOG) log.error(LOCATION + ISysMsg.GET_INTEGER_ERROR);
            if(AppConfigs.APP_DEBUG) throw new NumberFormatException(LOCATION);
        }
      return result;
    }
  public static  Double StringToDouble(String value)
    {
      final String LOCATION = "->StringToDouble(" + value + ")";
      Double result=null;
        try {
            result = Double.valueOf(value);
        } catch (NumberFormatException ex){
            if(AppConfigs.APP_SAVE_LOG) log.error(LOCATION + ISysMsg.GET_DOUBLE_ERROR);
            if(AppConfigs.APP_DEBUG) throw new NumberFormatException(LOCATION);
        }
      return result;
    }
    public static  Long StringToLong(String value)
    {
      final String LOCATION = "->StringToLong(" + value + ")";
      Long result=null;
        try {
            result = Long.valueOf(value);
        } catch (NumberFormatException ex){
            if(AppConfigs.APP_SAVE_LOG) log.error(LOCATION + ISysMsg.GET_LONG_ERROR);
            if(AppConfigs.APP_DEBUG) throw new NumberFormatException(LOCATION);
        }
      return result;
    }
    public static  Float StringToFloat(String value)
    {
      final String LOCATION = "->StringToFloat(" + value + ")";
      Float result=null;
        try {
            result = Float.valueOf(value);
        } catch (NumberFormatException ex){
            if(AppConfigs.APP_SAVE_LOG) log.error(LOCATION + ISysMsg.GET_FLOAT_ERROR);
            if(AppConfigs.APP_DEBUG) throw new NumberFormatException(LOCATION);
        }
      return result;
    }
    public static java.sql.Date StringToSqlDate(String value, String pattern) throws EException {
            java.sql.Date result = null;
            final String LOCATION = "->StringToDate(" + value + "," + pattern + ")";
            try {
                SimpleDateFormat fm = new SimpleDateFormat(pattern);
                fm.setLenient(false);
                result = new java.sql.Date(fm.parse(value).getTime());
            }
            catch (ParseException ex) {
                if(AppConfigs.APP_SAVE_LOG) log.error(LOCATION + ISysMsg.GET_DATE_ERROR);
                if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
            }
            return result;
    }

    public static java.util.Date StringToUtilDate(String value, String pattern) throws EException  {
        java.util.Date result = null;
        final String LOCATION = "->StringToDate(" + value + "," + pattern + ")";
        try {
            SimpleDateFormat fm = new SimpleDateFormat(pattern);
            fm.setLenient(false);
            result = new java.sql.Date(fm.parse(value).getTime());
        }
        catch (ParseException ex) {
            if(AppConfigs.APP_SAVE_LOG) log.error(LOCATION + ISysMsg.GET_DATE_ERROR);
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        }
        return result;
    }

    public static Timestamp StringToTimestamp(String value, String pattern) throws  EException {
        Timestamp result = null;
        final String LOCATION = "->StringToTimestamp(" + value + "," + pattern + ")";
        try {
            DateFormat fmt = new SimpleDateFormat(pattern);
            result = new Timestamp(fmt.parse(value).getTime());
        }
        catch (ParseException ex) {
            if(AppConfigs.APP_SAVE_LOG) log.error(LOCATION + ISysMsg.GET_DATE_ERROR);
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        }
        return result;
    }
    public static boolean isInteger(String value){
      final String num = "-0123456789";
      for (int i = 0; i< value.length(); i++){          
          if (num.indexOf(value.charAt(i))<0){
              return false;
          }
      }
      return (value.lastIndexOf("-")<1);
    }
    public static boolean isPositive(String value){      
      return isFloat(value) && (value.indexOf("-")<0);
    }
    public static boolean isNegative(String value){      
      return isFloat(value) && (value.indexOf("-")==0);
    }
    public static boolean isFloat(String value){
      final String num = "-.0123456789";
      for (int i = 0; i< value.length(); i++){          
          if (value.indexOf(value.charAt(i))<0 ){
              return false;
          }
      }
      return (value.lastIndexOf("-")<1) && (value.indexOf(".")==value.lastIndexOf("."));
    }
 
}