package com.ccf.service.impl;

import java.util.List;
import java.util.Map;

import com.ccf.bean.Club;
import com.ccf.dao.ClubDAO;
import com.ccf.service.ClubService;

public class ClubServiceImpl implements ClubService {
	private ClubDAO clubDao;
	
	public ClubDAO getClubDao() {
		return clubDao;
	}

	public void setClubDao(ClubDAO clubDao) {
		this.clubDao = clubDao;
	}
	
	public void join(Club club) {
		this.clubDao.addClub(club);
	}

	public List<Club> list() {
		return this.clubDao.showAllClubs();
	}
	
	public Club findClub(int cid) {
		return this.clubDao.findClubById(cid);
	}
	
	public void updateBasicInfo(Club club) {
		this.clubDao.updateClubBasicInfo(club);
	}
	
	public Map<String, Object> clubLeaderDetails(int cid) {
		return this.clubDao.clubDetails(cid);
	}

}
