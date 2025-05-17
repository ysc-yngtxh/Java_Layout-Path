package com.example.controller;

public class Student {

	private Integer sid;
	private String sname;

	public Student() {}

	public Student(Integer sid, String sname) {
		this.sid = sid;
		this.sname = sname;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

}
