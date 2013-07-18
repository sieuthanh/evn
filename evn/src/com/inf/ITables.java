package com.inf;

import com.inf.admin.ITablesAdmin;


public interface ITables extends ITablesAdmin{   
    public final String TABLE_INV_INT_BUFFER= "EVN_INV_INT_BUFFER";
    
    public final String TABLE_TAX_INT_BUFFER= "EVN_TAX_INT_BUFFER";
    public final String TABLE_GL_INT_BALANCES_BUFFER= "EVN_GL_INT_BALANCES_BUFFER";
    public final String TABLE_AR_INT_CUSTOMERS_BUFFER= "EVN_AR_INT_CUSTOMERS_BUFFER";
    public final String TABLE_AR_INT_TRANSACTIONS_BUFFER= "EVN_AR_INT_TRANSACTIONS_BUFFER";
    public final String TABLE_AR_INT_TRANS_DETAIL_BUFFER= "EVN_AR_INT_TRANS_DETAIL_BUFFER";
    
    public final String TABLE_TICKET_BUFFER= "EVN_TICKET_BUFFER";
    public final String TABLE_ALERT_BUFFER= "EVN_ALERT_BUFFER";
    public final String TABLE_REPORTS_BUFFER= "EVN_REPORTS_BUFFER";
    public final String TABLE_REPORTS_NORM_BUFFER= "EVN_REPORTS_NORM_BUFFER";
    
    public final String TABLE_REPORTS_DATA_BUFFER= "EVN_REPORTS_DATA_BUFFER";
    public final String TABLE_SOURCE_CONNECT_TO_BUFFER= "EVN_SOURCE_CONNECT_TO_BUFFER";    
    public final String TABLE_SOURCE_ACCOUNT_BUFFER= "EVN_SOURCE_ACCOUNT_BUFFER";
    public final String TABLE_TABLES= "EVN_TABLES";
    //THANHNN
    public final String TABLE_EVN_ERROR_BUFFER="EVN_ERROR_BUFFER";
    public final String TABLE_EVN_MODULE_BUFFER="EVN_MODULE_BUFFER";
 

    
    
}
