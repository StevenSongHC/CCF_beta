package com.ccf.service.impl;

import com.ccf.bean.User;
import com.ccf.dao.StevDAO;
import com.ccf.service.StevService;

public class StevServiceImpl implements StevService {
	private StevDAO stevDao;
	
	public StevDAO getStevDao() {
		return stevDao;
	}

	public void setStevDao(StevDAO stevDao) {
		this.stevDao = stevDao;
	}

	public boolean checkIsStev(User user) {
		return this.stevDao.isStev(user);
	}
	
	public void addTestUser(User user) {
		this.stevDao.addUser(user);
	}
	
	public void changeLeader(int cid, String newLeaderCode) {
		this.stevDao.updateLeaderCode(cid, newLeaderCode);
	}

	public void memberModified(int cid, String c_members,
			String c_code_edit_authority_members, String c_code_current_leader) {
		this.stevDao.memberModified(cid, c_members, c_code_edit_authority_members, c_code_current_leader);
		
	}
	
}
