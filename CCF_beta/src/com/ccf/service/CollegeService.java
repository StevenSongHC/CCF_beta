package com.ccf.service;

import java.util.List;

import com.ccf.bean.College;

public interface CollegeService {
	
	public List<College> list();

	public void add(College college);
	
	public College getLastCollege();
	
	public College getCollege(int coid);
	
	public void update(College college);
	
	public void plusClubAmount(int coid, int amount);
}
