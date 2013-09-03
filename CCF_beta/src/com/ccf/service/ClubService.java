package com.ccf.service;

import java.util.List;
import java.util.Map;

import com.ccf.bean.Club;

public interface ClubService {

	public void join(Club club);	
	
	public List<Club> list();
	
	public Club findClub(int cid);
	
	public void updateBasicInfo(Club club);
	
	public Map<String, Object> clubLeaderDetails(int cid);
	
}