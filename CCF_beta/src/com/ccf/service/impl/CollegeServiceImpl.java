package com.ccf.service.impl;

import java.util.List;

import com.ccf.bean.College;
import com.ccf.dao.CollegeDAO;
import com.ccf.service.CollegeService;

public class CollegeServiceImpl implements CollegeService {
	private CollegeDAO collegeDao;

	public CollegeDAO getCollegeDao() {
		return collegeDao;
	}

	public void setCollegeDao(CollegeDAO collegeDao) {
		this.collegeDao = collegeDao;
	}

	
	public List<College> list() {
		return collegeDao.list();
	}

	@Override
	public void add(College college) {
		collegeDao.add(college);
	}

	@Override
	public College getLastCollege() {
		return collegeDao.fetchCollege("last");
	}

	@Override
	public College getCollege(int coid) {
		return collegeDao.getCollegeByCoid(coid);
	}

	@Override
	public void update(College college) {
		collegeDao.update(college);
	}
}
