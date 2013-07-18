package com.action.evn;

import com.bo.evn.ticket.BTicket;
import com.exp.EException;
import com.form.FBeans;
import com.form.evn.ticket.FTicket;
import com.inf.IKey;
import com.inf.Queue;
import java.sql.SQLException;


public class AThread2AddQueue extends  Thread {
    private BTicket bo =new BTicket();
    private FTicket bean =new FTicket();
    private static Queue dataQueue=null;

    public AThread2AddQueue(){
        dataQueue=IKey.getDataQueue();
    }

    public void run() 
    {
  
        while(true) 
        {
                System.out.println("---------------runing AThread2AddQueue-----------------");
              FBeans  beans=new FBeans();
    
                try {
                    beans = bo.getAllRecordActiveO(0);
                    if (beans.size()>0){
                        for (int i=0 ;i<beans.size() ;i++ ){
                            bean =new FTicket();
                            bean=(FTicket)beans.get(i);
                            dataQueue.enqueue(bean);
                        }
                    }
                } catch (SQLException e) {
                    // TODO
                } catch (EException e) {
                    // TODO
                }
                try {
                        sleep(IKey.getDelay()*60*1000);
                } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }
       
    }

}
