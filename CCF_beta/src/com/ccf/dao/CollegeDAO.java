package com.ccf.dao;

import java.util.List;

import com.ccf.bean.College;

public interface CollegeDAO {

	public List<College> list();
	
	public void add(College college);
	
	public College fetchCollege(String index);		// index = first || last, only accept string index
	
	public College getCollegeByCoid(int coid);
	
	public void update(College college);
	
	public void plusClubAmount(int coid, int amount);
	
}
