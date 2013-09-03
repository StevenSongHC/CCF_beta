package com.ccf.service;

import java.util.List;
import java.util.Map;

import com.ccf.bean.User;

public interface UserService {
	
	public List<Object[]> querySql(String sql);
	
	public Object executeSql(String sql);
	
	public List<Object[]> executeHql(String hql);
	
	public void register(User user);
	
	public List<User> fetchUsersList();
	
	public User findUser(int uid);	//用来通过uid快速找到user用
	
	public User findUserByAccount(String account);
	
	public User findUserByCode(String code);	//trace user by code
	
	public List<Map<String, Object>> findUsersInfoByUids(String uids); 
	
	public User getCurrentUser();
	
	/*public List<User> findUserSetByCodes(String[] codes);*/
	
	public void updateBasicProfile(User user);
	
	public User login(String account, String password);
	
	public String userJob(int uid, int cid);
	
	public List<Map<String, Object>> getUserClubList(int uid);	// returns cid & c_name plus user's jobname
	
	public boolean verifyLeader(int cid, int uid);	//find the club by cid, then check leader authority by user id
	
	public String tryInviteNewMember(String account, int cid);
	
	public void invite(int cid, int hostUid, int guestUid);	//only leader can use it
	
	public void acceptInvition(int hostUid, int guestUid, int cid);
	
	public void refuseInvition(int hostUid, int guestUid, int cid);
	
	public String tryJoinClub(int cid, int uid);
	
	public void joinClub(int uid, int cid);
	
	public void approveJoinClubApply(int cid, int applicantUid);
	
	public void rejectJoinClubApply(int cid, int applicantUid);
	
	public void addANewMember(int cid, int newbieUid);
	
	public void checkNotification(int uid, Map<String, Object> request);	//check user's notification
	
	public List<Map<String, Object>> checkNotification(int uid);						// check user's notification, for ajax using
	
	// mute invitation data in notification list
	public void muteInvitationReplyInNotification(int senderUid, int receiverUid, int cid, String date);
	
	public void muteNewbieNoticeInNotification(int senderUid, int receiverUid, int cid);
	
	public void muteJoinClubApplyNoticeInNotification(int senderUid, int receiverUid, int cid);
}
