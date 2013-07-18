package com.dao;





public class DSql extends DSqlAdmin{
    public final String SQL_SEARCH_ROWS_HEAD=SELECT + STAR + FROM + OPEN + SELECT + "P."+ STAR + COMMA + "rownum rnum" + FROM + OPEN;
    public final String SQL_SEARCH_ROWS_TAIL=CLOSE + " P" + WHERE + "rownum <=" + QUESTION + CLOSE + WHERE + "rnum >=" + QUESTION; 

     
  
}
