
/*
 * IRoles.java
 *
 */
package com.inf;

/**
 * IRoles
 */
public interface IRoles extends ICoreRoles
{
      
        /*All Privileges*/
        
        public static final int pSELECT      = 1;
        public static final int pUPDATE      = 2;
        public static final int pINSERT      = 4;
        public static final int pDELETE      = 8;
        public static final int pDOWNLOAD    = 16;
        public static final int pUPLOAD      = 32;
        
        /*All Role*/
        public static final int rOFFICER     = 1;
        public static final int rLEADER      = 2;
        public static final int rMANAGER     = 4;
        public static final int rGUEST       = 8;
        public static final int rBROADCAST   = 16;
        
        public static final int rCALENDARDEP = 32;
        public static final int rOBAGENDA = 64;
    
        
    /*All cabin*/
    public static final int rCABIN_ONE   =128;
    public static final int rCABIN_MUTI   =256;
    public static final int rCABIN_PUBLIC   =512;
//      
    public static final int pTIMEKEEPER           = 128;//CHAM CONG
    public static final int pEXAMINE              = 256;//DUYET CONG
    public static final int pEXAMINESUM              = 512;//TONG DUYET CONG
    public static final int ROFFICER     = rOFFICER;
    public static final int RLEADER      = rLEADER;
    public static final int RMANAGER     = rMANAGER;

}