package com.ccf.dao;

import java.util.List;
import java.util.Map;

import com.ccf.bean.User;

public interface UserDAO {
	
	public List<Object[]> querySql(String sql);
	
	public Object executeSql(String sql);
	
	public void runSql(String sql);
	
	public List<Object[]> executeHql(String hql);
	
	public void registerUser(User user);
	
	public List<User> fetchAllUsersInfo();
	
	public User findUserByUid(int uid);
	
	public User findUserByAccount(String account);
	
	public User findUserByCode(String code);
	
	public List<User> getUserListByNameLike(String username, int limit);
	
	public int findUidByAccount(String account);
	
	public List<User> findUserSetByUids(String uids);
	
	public List<User> findUserSetByCodes(String[] codes);
	
	public int[] getUserClubArray(int uid);
	
	public int[] getUserClubJobArray(int uid);
	
	public int getJobCode(String newJob);
	
	public boolean updateUserClubLevel(int uid, int[] newJobArray);
	
	public void updateUserBasicProfile(User user);
	
	public User login(String account, String password);
	
	public Map<String, String> userJobList(int uid);
	
	public List<Map<String, Object>> userClubList(int uid);
	
	public boolean verifyLeader(int cid, int uid);
	
	public int[] clubMembersUid(int cid);
	
	
	public List<Object[]>clubInvitingList(int cid);
	
	public List<Object[]>clubJoinApplyList(int cid);
	
	// the following methods are all about the way to increase a new member
	public void invite(int cid, int hostUid, int guestUid);
	
	public void acceptInvition(int hostUid, int guestUid, int cid);
	
	public void refuseInvition(int hostUid, int guestUid, int cid);
	
	public void joinClub(int uid, int cid);
	
	public void approveJoinClubApply(int cid, int applicantUid);
	
	public void rejectJoinClubApply(int cid, int applicantUid);
	
	public void addANewMember(int cid, int newbieUid);
	// done here
	
	public boolean isNotificationEmpty(int uid);
	
	public List<Object[]> checkNotification(int uid);
	
	public void muteInvitationReplyInNotification(int senderUid, int receiverUid, int cid, String date);
	
	public void muteNewbieNoticeInNotification(int senderUid, int receiverUid, int cid);
	
	public void muteJoinClubApplyNoticeInNotification(int senderUid, int receiverUid, int cid);
	
}
