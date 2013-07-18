package com.form.evn.evnErrorBuffer;

import com.form.FSeed;

public class FEvnErrorBuffer extends FSeed {
	private int error_Id;
	private String error_Description;
	private String error_Code;
	private int module_Id;
	private String nameTable;
	private int type = 0;

	public void reset() {
		this.error_Id = 0;
		this.error_Description = "";
		this.error_Code = "";
		this.module_Id = 0;

	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}

	public String getError_Description() {
		return error_Description;
	}

	public void setError_Description(String error_Description) {
		this.error_Description = error_Description;
	}

	public String getError_Code() {
		return error_Code;
	}

	public void setError_Code(String error_Code) {
		this.error_Code = error_Code;
	}

	public int getError_Id() {
		return error_Id;
	}

	public void setError_Id(int error_Id) {
		this.error_Id = error_Id;
	}

	public int getModule_Id() {
		return module_Id;
	}

	public void setModule_Id(int module_Id) {
		this.module_Id = module_Id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
