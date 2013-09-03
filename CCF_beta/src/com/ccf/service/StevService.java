package com.ccf.service;

import com.ccf.bean.User;

public interface StevService {
	
	public boolean checkIsStev(User user);
	
	public void addTestUser(User user);
	
	public void changeLeader(int cid, String newLeaderCode);
	
	public void memberModified(int cid, String c_members,
			String c_code_edit_authority_members, String c_code_current_leader);
	
}
