package com.ccf.bean;

import java.io.Serializable;

public class Club implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4845277849456209321L;
	
	private int cid;
	private String c_name;
	private String c_pic;
	private String c_homepage;
	private String c_members;
	private String c_code_current_leader;
	private String c_code_edit_authority_members;
	private String c_intro;
	private String c_activities;
	private String c_shared_files;
	private int c_xp;
	private int c_province;
	private int c_city;
	private String c_college;
	private String c_field;

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_pic() {
		return c_pic;
	}
	public void setC_pic(String c_pic) {
		this.c_pic = c_pic;
	}
	public String getC_homepage() {
		return c_homepage;
	}
	public void setC_homepage(String c_homepage) {
		this.c_homepage = c_homepage;
	}
	public String getC_members() {
		return c_members;
	}
	public void setC_members(String c_members) {
		this.c_members = c_members;
	}
	public String getC_code_current_leader() {
		return c_code_current_leader;
	}
	public void setC_code_current_leader(String c_code_current_leader) {
		this.c_code_current_leader = c_code_current_leader;
	}
	public String getC_code_edit_authority_members() {
		return c_code_edit_authority_members;
	}
	public void setC_code_edit_authority_members(
			String c_code_edit_authority_members) {
		this.c_code_edit_authority_members = c_code_edit_authority_members;
	}
	public String getC_intro() {
		return c_intro;
	}
	public void setC_intro(String c_intro) {
		this.c_intro = c_intro;
	}
	public String getC_activities() {
		return c_activities;
	}
	public void setC_activities(String c_activities) {
		this.c_activities = c_activities;
	}
	public String getC_shared_files() {
		return c_shared_files;
	}
	public void setC_shared_files(String c_shared_files) {
		this.c_shared_files = c_shared_files;
	}
	public int getC_xp() {
		return c_xp;
	}
	public void setC_xp(int c_xp) {
		this.c_xp = c_xp;
	}
	public int getC_province() {
		return c_province;
	}
	public void setC_province(int c_province) {
		this.c_province = c_province;
	}
	public int getC_city() {
		return c_city;
	}
	public void setC_city(int c_city) {
		this.c_city = c_city;
	}	
	public String getC_college() {
		return c_college;
	}
	public void setC_college(String c_college) {
		this.c_college = c_college;
	}
	public String getC_field() {
		return c_field;
	}
	public void setC_field(String c_field) {
		this.c_field = c_field;
	}
	
}
