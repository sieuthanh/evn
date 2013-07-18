package com.form.evn.evnModuleBuffer;

import com.form.FSeed;

public class FEvnModuleBuffer extends FSeed {
	private int moduleId;
	public String moduleCode;
	public String moduleDescription;
	public String moduleAtribute1;
	public String moduleAtribute2;
	public String moduleAtribute3;
	public String nameTable;
	public int type = 0;

	public FEvnModuleBuffer() {
		moduleAtribute1="";
		moduleAtribute2="";
		moduleAtribute3="";
	}

	public void reset() {
		moduleId = 0;
		moduleCode = "";
		moduleDescription = "";
		moduleAtribute1 = "";
		moduleAtribute2 = "";
		moduleAtribute3 = "";
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public String getModuleAtribute1() {
		return moduleAtribute1;
	}

	public void setModuleAtribute1(String moduleAtribute1) {
		this.moduleAtribute1 = moduleAtribute1;
	}

	public String getModuleAtribute2() {
		return moduleAtribute2;
	}

	public void setModuleAtribute2(String moduleAtribute2) {
		this.moduleAtribute2 = moduleAtribute2;
	}

	public String getModuleAtribute3() {
		return moduleAtribute3;
	}

	public void setModuleAtribute3(String moduleAtribute3) {
		this.moduleAtribute3 = moduleAtribute3;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
