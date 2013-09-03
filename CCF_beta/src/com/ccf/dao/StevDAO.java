package com.ccf.dao;

import com.ccf.bean.User;

public interface StevDAO {
	
	public boolean isStev(User user);
	
	public void addUser(User user);
	
	public void updateLeaderCode(int cid, String newLeaderCode);
	
	public void memberModified(int cid, String c_members,
			String c_code_edit_authority_members, String c_code_current_leader);
	
}
