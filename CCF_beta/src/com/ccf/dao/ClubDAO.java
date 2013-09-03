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
	
}
