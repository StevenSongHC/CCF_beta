package com.ccf.dao;

import java.util.List;
import java.util.Map;

import com.ccf.bean.Club;

public interface ClubDAO {

	public List<Object[]> querySql(String sql);
	
	public Object executeSql(String sql);
	
	public void runSql(String sql);
	
	public void addClub(Club club);
	
	public List<Club> showAllClubs();
	
	public Club findClubById(int cid);
	
	public void updateClubBasicInfo(Club club);
	
	public Map<String, Object> clubDetails(int cid);
	
	public int[] clubMemberUidArray(int cid);
	
	public int[] clubPublisherUidArray(int cid);
	
	public String clubMemberUidString(int cid);
	
	public String clubPublisherUidString(int cid);
	
	// note: update - user.u_clubs no modify required
	//		 delete - have to modify those data
	public boolean updateCMembers(int cid, String newCMembers);
	
	public boolean updateCCodeEditAuthorityMembers(int cid, String CCodeEditAuthorityMembers);
	
	public boolean deleteMember(int cid, int uid);
	
	public boolean deletePublisher(int cid, int uid);
	
}
