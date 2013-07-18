package com.form.evn.ticket;

import com.form.FSeed;

/**
 *
 * @author hungnn
 */
public class FTicket extends FSeed{

	private long ticket_id; 
	private long ticket_id_err; 
	private int src_connect_id; 
	private String status;
	private int active;
	private int evn_id; 
	private String evn_time; 
	private int buffer_id; 
	private String buffer_time;
	private int ebs_id; 
	private String ebs_time; 
	private String start_time; 
	private String end_time; 
	private int total_records;
	private String code; 
	private String table_name; 
	private int wheres; 
	private String description;
    private String dataFile;
    private String error_code;
    private String priority_time;
	
    private String nameTable;
    private String columnName;
    private String sqlWhere;
    private int thread1;
    private int thread2;
    private int delay;
    private int amountT;
    
    private String search_time; 
    private String search_type; 
    private int search_type_time; 
    
    public void reset(){
        this.ticket_id=0;
        this.ticket_id_err=0;
        this.src_connect_id=0;
        this.status="";
        this.active=0;
        this.description="";
        this.evn_id=0;
        this.evn_time="";
        this.code="";
        this.table_name="";
        this.start_time="";
        this.end_time="";
        this.total_records=0;
    }
    
	public long getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}
	public long getTicket_id_err() {
		return ticket_id_err;
	}
	public void setTicket_id_err(long ticket_id_err) {
		this.ticket_id_err = ticket_id_err;
	}
	public int getSrc_connect_id() {
		return src_connect_id;
	}
	public void setSrc_connect_id(int src_connect_id) {
		this.src_connect_id = src_connect_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getEvn_id() {
		return evn_id;
	}
	public void setEvn_id(int evn_id) {
		this.evn_id = evn_id;
	}
	public String getEvn_time() {
		return evn_time;
	}
	public void setEvn_time(String evn_time) {
		this.evn_time = evn_time;
	}
	public int getBuffer_id() {
		return buffer_id;
	}
	public void setBuffer_id(int buffer_id) {
		this.buffer_id = buffer_id;
	}
	public String getBuffer_time() {
		return buffer_time;
	}
	public void setBuffer_time(String buffer_time) {
		this.buffer_time = buffer_time;
	}
	public int getEbs_id() {
		return ebs_id;
	}
	public void setEbs_id(int ebs_id) {
		this.ebs_id = ebs_id;
	}
	public String getEbs_time() {
		return ebs_time;
	}
	public void setEbs_time(String ebs_time) {
		this.ebs_time = ebs_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getTotal_records() {
		return total_records;
	}
	public void setTotal_records(int total_records) {
		this.total_records = total_records;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public int getWheres() {
		return wheres;
	}
	public void setWheres(int wheres) {
		this.wheres = wheres;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSqlWhere() {
        return sqlWhere;
    }

    public void setSqlWhere(String sqlWhere) {
        this.sqlWhere = sqlWhere;
    }

    public int getThread1() {
        return thread1;
    }

    public void setThread1(int thread1) {
        this.thread1 = thread1;
    }

    public int getThread2() {
        return thread2;
    }

    public void setThread2(int thread2) {
        this.thread2 = thread2;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getAmountT() {
        return amountT;
    }

    public void setAmount(int amountT) {
        this.amountT = amountT;
    }

    public String getSearch_time() {
        return search_time;
    }

    public void setSearch_time(String search_time) {
        this.search_time = search_time;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public int getSearch_type_time() {
        return search_type_time;
    }

    public void setSearch_type_time(int search_type_time) {
        this.search_type_time = search_type_time;
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getPriority_time() {
        return priority_time;
    }

    public void setPriority_time(String priority_time) {
        this.priority_time = priority_time;
    }
}
