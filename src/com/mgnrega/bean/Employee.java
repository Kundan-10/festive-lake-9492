package com.mgnrega.bean;

public class Employee {

	private int eid;
	private String ename;
	private String emobile;
	private String eaddress;
	private int dayworked;
	private int ewages;

	public Employee() {
		
	}

	public Employee(int eid, String ename, String emobile, String eaddress, int dayworked, int ewages) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.emobile = emobile;
		this.eaddress = eaddress;
		this.dayworked = dayworked;
		this.ewages = ewages;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmobile() {
		return emobile;
	}

	public void setEmobile(String emobile) {
		this.emobile = emobile;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}

	public int getDayworked() {
		return dayworked;
	}

	public void setDayworked(int dayworked) {
		this.dayworked = dayworked;
	}

	public int getEwages() {
		return ewages;
	}

	public void setEwages(int ewages) {
		this.ewages = ewages;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", emobile=" + emobile + ", eaddress=" + eaddress
				+ ", dayworked=" + dayworked + ", ewages=" + ewages + "]";
	}
}
