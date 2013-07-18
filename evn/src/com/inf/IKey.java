
/*
 * IKey.java
 *
  */
package com.inf;

import com.inf.admin.IKeyAdmin;
import com.lib.AppConfigs;
import java.util.Iterator;
import java.util.Vector;

/**
 * IKey
 */
public class IKey extends  IKeyAdmin
{
    public static final int FETCH_SIZE =Integer.parseInt(getValue("FETCH.SIZE"));
    public static final int RECORDS_COMMIT_TOTAL =Integer.parseInt(getValue("RECORDS.COMMIT.TOTAL"));
    public static final String WS_URL = getValue("WS.URL");
    public static final String WS_FUNCTION_GETDATA = getValue("WS.FUNCTION.GETDATA");
    public static final String WS_FUNCTION_PUSHDATA = getValue("WS.FUNCTION.PUSHDATA");
    public static final String APP_DATE_VIETNAMESE   = "APP.DATE.VIETNAMESE";      
    public static final int COUNT_CHECK_DATE = 3;
    public static final String[] DATE_TYPES= {APP_DATE_YEAR,APP_DATE_MONTH, AppConfigs.APP_DATE_DEFAULT};
    
    
    public static final String SYSTEM_FILE_XML = getValue("SYSTEM.FILE.XML") ;
    public static final String REPORT_FILE_PATH = getValue("REPORT.FILE.PATH") ;
    public static final String REPORT_FILE_NAME = getValue("REPORT.FILE.NAME") ;
    public static final int REPORT_SHEETLIST_X = Integer.parseInt(getValue("REPORT.SHEETLIST.X")) ;
    public static final int REPORT_SHEETLIST_Y = Integer.parseInt(getValue("REPORT.SHEETLIST.Y")) ;
    public static final String SYSTEM_FILE_SCHIP = getValue("SYSTEM.FILE.SCHIP") ;
    public static final int EVN_TICKET_DELAY = Integer.parseInt(getValue("EVN.DELAY")) ;
    private static int thread1 = 0;
    private static int thread2 = 0;
    private static int thread3 = 0;
    private static int delay = 5;
    private static int delay3 = 5;
    private static int amountT = 1;
    private static boolean thread1Runing = false;
    private static boolean thread2Runing = false;
    private static boolean thread3Runing = false;
    private static Vector liveThreads = new Vector();
    private static Queue dataQueue=new Queue("dataQueue");
    public static void addLiveThread(Thread thread)
    {
            synchronized(liveThreads)
            {
                    liveThreads.add(thread);
            }
    }
    public static boolean removeThread(Thread diedThread)   {
            synchronized (liveThreads) {
                    Iterator it = liveThreads.iterator();
                    while (it.hasNext()) {
                            Thread currThread = (Thread) it.next();
                            if (currThread.equals(diedThread)) {
                                    liveThreads.remove(diedThread);
                                    return true;
                            }
                    }
                    return false;
            }
    }
    public static int getThread1() {
        return thread1;
    }

    public static void setThread1(int thread1) {
        IKey.thread1 = thread1;
    }

    public static int getThread2() {
        return thread2;
    }

    public static void setThread2(int thread2) {
        IKey.thread2 = thread2;
    }

    public static boolean isThread1Runing() {
        return thread1Runing;
    }

    public static void setThread1Runing(boolean thread1Runing) {
        IKey.thread1Runing = thread1Runing;
    }

    public static boolean isThread2Runing() {
        return thread2Runing;
    }

    public static void setThread2Runing(boolean thread2Runing) {
        IKey.thread2Runing = thread2Runing;
    }

    public static int getDelay() {
        return delay;
    }

    public static void setDelay(int delay) {
        IKey.delay = delay;
    }

    public static int getAmountT() {
        return amountT;
    }

    public static void setAmountT(int amountT) {
        IKey.amountT = amountT;
    }

    public static int getThread3() {
        return thread3;
    }

    public static void setThread3(int thread3) {
        IKey.thread3 = thread3;
    }

    public static int getDelay3() {
        return delay3;
    }

    public static void setDelay3(int delay3) {
        IKey.delay3 = delay3;
    }

    public static boolean isThread3Runing() {
        return thread3Runing;
    }

    public static void setThread3Runing(boolean thread3Runing) {
        IKey.thread3Runing = thread3Runing;
    }

    public static Queue getDataQueue() {
        return dataQueue;
    }

}
