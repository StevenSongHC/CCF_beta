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
	
	public Map<String, Object> clubCommanDetails(int cid) {
		return this.clubDao.clubDetails(cid);
	}
	
	public void addMember(int cid, int uid) {
		System.out.println("addMember ====" + cid + ", " + uid);
		clubDao.updateCMembers(cid, clubDao.clubMemberUidString(cid) + uid + ",");
	}
	
	public void removeMember(int cid, int uid) {
		StringBuilder builder = new StringBuilder();
		for (int locatedUid : clubDao.clubMemberUidArray(cid)) {
			if (uid != locatedUid)
				builder.append(locatedUid + ",");
		}
		clubDao.updateCMembers(cid, builder.toString());
	}


	@Override
	public void addPublisher(int cid, int uid) {
		clubDao.updateCCodeEditAuthorityMembers(cid, clubDao.clubPublisherUidString(cid) + uid + ",");
	}

	@Override
	public void removePublisher(int cid, int uid) {
		StringBuilder builder = new StringBuilder();
		for (int locatedUid : clubDao.clubPublisherUidArray(cid)) {
			if (uid != locatedUid)
				builder.append(locatedUid + ",");
		}
		System.out.println("new publisher array---" + builder.toString());
		clubDao.updateCCodeEditAuthorityMembers(cid, builder.toString());
	}

	@Override
	public boolean updateLeader(int cid, int oldLeaderUid, int newLeaderUid) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
