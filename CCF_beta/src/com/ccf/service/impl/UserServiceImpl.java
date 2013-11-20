package com.ccf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ccf.bean.User;
import com.ccf.dao.UserDAO;
import com.ccf.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO userDao; 
	
	public UserDAO getUserDao() {
		return userDao;
	}
	
	public Object executeSql(String sql) {
		return this.userDao.executeSql(sql);
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	//methods
	public List<Object[]> querySql(String sql) {
		return this.userDao.querySql(sql);
	}
	
	public List<Object[]> executeHql(String hql) {
		return this.userDao.executeHql(hql);
	}
	
	public void register(User user) {
		this.userDao.registerUser(user);	//è¿™é‡Œæ‰�çœŸæ­£æ³¨å†Œuser
	}
	
	public List<User> fetchUsersList() {
		return this.userDao.fetchAllUsersInfo();
	}
	
	public User findUser(int uid) {
		return this.userDao.findUserByUid(uid);
	}
	
	public User findUserByAccount(String account) {
		System.out.println("ServiceImpl account:" + account);
		return this.userDao.findUserByAccount(account);
	}
	
	public User findUserByCode(String code) {
		return this.userDao.findUserByCode(code);
	}
	
	public List<User> getUserListByNameLike(String username, int limit) {
		return this.userDao.getUserListByNameLike(username, limit);
	}
	
	public List<Map<String, Object>> findUsersInfoByUids(String uids) {
		List<Map<String, Object>> usersInfo = new ArrayList<Map<String, Object>>();		// contains u_name & uid
		List<User> users = this.userDao.findUserSetByUids(uids);
		for (User user : users) {
			Map<String, Object> info = new HashMap<String, Object>();
			info.put("username", user.getU_name());
			info.put("useraccount", user.getU_account());
			info.put("userpic", user.getU_pic());
			info.put("uid", user.getUid());
			usersInfo.add(info);
		}
		return usersInfo;
	}
	
	public User getCurrentUser() {
		return (User) ServletActionContext.getRequest().getSession()
				.getAttribute(com.ccf.action.user.UserLoginAction.USER_SESSION);
	}
	
	/*public List<User> findUserSetByCodes(String[] codes) {
		return this.userDao.findUserSetByCodes(codes);
	}*/
	
	public void updateBasicProfile(User user) {
		this.userDao.updateUserBasicProfile(user);
	}
	
	public User login(String account, String password) {
		return this.userDao.login(account, password);
	}
	
	public String userJob(int uid, int cid) {
		// this.userDao.userJobList(uid).toString()-->map
		// this.userDao.userJobList(uid)-->user's full club-job mapping list
		// this.userDao.userJobList(uid).get(String.valueOf(cid))-->the job, a.k.a destination
		if (this.userDao.userJobList(uid) == null)
			return "free";
		return this.userDao.userJobList(uid).get(String.valueOf(cid));
	}
	
	public List<Map<String, Object>> getUserClubList(int uid) {
		return this.userDao.userClubList(uid);
	}
	
	public boolean verifyLeader(int cid, int uid) {
		return this.userDao.verifyLeader(cid, uid);
	}
	
	public String tryInviteNewMember(String account, int cid) {	// needs dao's isMember & isInviting
		int user = this.userDao.findUidByAccount(account);
		// if that guy already existed in the club's member list
		for (int existedMember : this.userDao.clubMembersUid(cid)) {
			if (user == existedMember)
				return "member";
		}
		// if that guy already existed in the club's inviting list
		for (Object obj : this.userDao.clubInvitingList(cid)) {
			Map<?, ?> map = (Map<?, ?>) obj;
			if (user == Integer.parseInt(map.get("guest_uid").toString()))
				return "inviting";
		}
		
		return "OK";
	}
	
	public void invite(int cid, int hostUid, int guestUid) {
		this.userDao.invite(cid, hostUid, guestUid);
	}
	
	public void acceptInvition(int hostUid, int guestUid, int cid) {
		this.userDao.acceptInvition(hostUid, guestUid, cid);
	}
	
	public void refuseInvition(int hostUid, int guestUid, int cid) {
		this.userDao.refuseInvition(hostUid, guestUid, cid);
	}
	
	public String tryJoinClub(int cid, int uid) {
		// if that guy already existed in the club's member list
		for (int existedMember : this.userDao.clubMembersUid(cid)) {
			if (uid == existedMember)
				return "member";
		}
		// if that guy already sent out an join_club_apply
		for (Object obj : this.userDao.clubJoinApplyList(cid)) {
			Map<?, ?> map = (Map<?, ?>) obj;
			if (uid == Integer.parseInt(map.get("applicant_uid").toString()))
				return "applying";
		}
		
		return "Ñ…Ð°Ñ€Ð°ÑˆÐ¾";	// good to go
	}
	
	public void joinClub(int uid, int cid) {
		this.userDao.joinClub(uid, cid);
	}
	
	public void approveJoinClubApply(int cid, int applicantUid) {
		this.userDao.approveJoinClubApply(cid, applicantUid);
		
	}
	
	public void rejectJoinClubApply(int cid, int applicantUid) {
		this.userDao.rejectJoinClubApply(cid, applicantUid);
	}
	
	public void addANewMember(int cid, int newbieUid) {
		this.userDao.addANewMember(cid, newbieUid);
	}
	
	// Delete it!!!
	public void checkNotification(int uid, Map<String, Object> request) {
		//check notification
		List<Object[]> notifications = this.userDao.checkNotification(uid);
		
		List<Map<String, String>> invitations = new ArrayList<Map<String, String>>();
		List<Map<String, String>> invitationReplys = new ArrayList<Map<String, String>>();
		List<Map<String, String>> newbieNotices = new ArrayList<Map<String, String>>();
		List<Map<String, String>> joinClubApplyReplies = new ArrayList<Map<String, String>>();
		
		// only for leader
		List<Map<String, String>> joinClubApplies = new ArrayList<Map<String, String>>();
		
		Map<String, List<Map<String, String>>> allNotifications = new HashMap<String, List<Map<String, String>>>();
		
		for (Object notification : notifications) {
			Map<?, ?> map = (Map<?, ?>) notification;
			// distinguish data type by check the type of data
			if (map.get("invitationSentDate") != null) {	// current data is invitation
				Map<String, String> item = new HashMap<String, String>();
				String sender = map.get("sender").toString();
				String club = map.get("joinClub").toString();
				String date = map.get("invitationSentDate").toString();
				String from = map.get("sender_uid").toString();		// marker for sender's uid
				String to = map.get("receiver_uid").toString();			// marker for receiver's uid
				String where = map.get("club_cid").toString();		// marker for club's cid
				item.put("sender", sender);
				item.put("club", club);
				item.put("date", date);
				item.put("from", from);
				item.put("to", to);
				item.put("where", where);
				
				System.out.println(sender + "invites u to join " + club + "  " + date);
				
				invitations.add(item);
			}
			
			if(map.get("invitationReplyDate") != null) {	// current data is invitation_reply
				Map<String, String> item = new HashMap<String, String>();
				String newbie = map.get("newbie").toString();
				String club = map.get("myClub").toString();
				String status = map.get("status").toString();
				if (status.equals("1"))		//switch only be supported under the jre 1.7
					status = "accepted";		// 1=host unread, guest accepted
				else if (status.equals("2"))	// 2=host unread, guest refused
					status = "refused";
				String date = map.get("invitationReplyDate").toString();
				String sender = map.get("sender").toString();
				String receiver = map.get("receiver").toString();
				String cid = map.get("cid").toString();
				item.put("newbie", newbie);
				item.put("club", club);
				item.put("status", status);
				item.put("date", date);
				item.put("sender", sender);
				item.put("receiver", receiver);
				item.put("cid", cid);
				System.out.println(newbie + " has " + status + " your invotation to join " + club + "  " + date);
				
				invitationReplys.add(item);
			}
			
			if (map.get("newbieJoinDate") != null) {
				Map<String, String> item = new HashMap<String, String>();
				String newbie = map.get("newbie").toString();
				String club = map.get("club").toString();
				String date = map.get("newbieJoinDate").toString();
				String cid = map.get("cid").toString();
				String sender = map.get("sender").toString();
				String receiver = map.get("receiver").toString();
				System.out.println("newbie:" + newbie + " club:" + club + " date:" + date);
				item.put("newbie", newbie);
				item.put("club", club);
				item.put("date", date);
				item.put("cid", cid);
				item.put("sender", sender);
				item.put("receiver", receiver);
				System.out.println(newbie + " is a part of your club(" + club + ") now");
				
				newbieNotices.add(item);
			}
			
			if (map.get("joinClubApplyReplyDate") != null) {
				Map<String, String> item = new HashMap<String, String>();
				String leader = map.get("leader").toString();
				String club = map.get("club").toString();
				String date = map.get("joinClubApplyReplyDate").toString();
				String status = map.get("status").toString();
				if (status.equals("1"))
					status = "approved";
				if (status.equals("2"))
					status = "rejected";
				String cid = map.get("cid").toString();
				String sender = map.get("sender").toString();
				String receiver = map.get("receiver").toString();
				item.put("leader", leader);
				item.put("club", club);
				item.put("date", date);
				item.put("status", status);
				item.put("cid", cid);
				// sender=leaderUid, receiver=applicantrUid
				item.put("sender", sender);
				item.put("receiver", receiver);
				
				joinClubApplyReplies.add(item);
				
			}
			
			
			// only for leader
			if (map.get("joinClubApplyDate") != null) {
				Map<String, String> item = new HashMap<String, String>();
				String applicant = map.get("applicant").toString();
				String club = map.get("club").toString();
				String date = map.get("joinClubApplyDate").toString();
				String cid = map.get("cid").toString();
				String sender = map.get("sender").toString();
				String receiver = map.get("receiver").toString();
				item.put("applicant", applicant);
				item.put("club", club);
				item.put("date", date);
				item.put("cid", cid);
				// sender=applicantUid, receiver=leaderUid
				item.put("sender", sender);
				item.put("receiver", receiver);
				
				joinClubApplies.add(item);
			}
			
		}
		// how it works
		//(Object)sql->string->(Map) item->(List) notificationItem (i.e.invitations)->(Map) allNotifications
		
		allNotifications.put("invitation", invitations);
		allNotifications.put("invitation-reply", invitationReplys);
		allNotifications.put("newbie-notice", newbieNotices);
		allNotifications.put("join-club-apply-reply", joinClubApplyReplies);
		
		allNotifications.put("join-club-apply", joinClubApplies);
		
		// push notification to request page
		request.put("notifications", allNotifications);
	}
	
	
	
	
	
	
	
	public List<Map<String, Object>> checkNotification(int uid) {
		List<Object[]> list = this.userDao.checkNotification(uid);
		if (list == null)
			return null;
		List<Map<String, Object>> notifications = new ArrayList<Map<String, Object>>();
		for (Object notification : list) {
			Map<?, ?> map = (Map<?, ?>) notification;
			if (map.get("invitationSentDate") != null) {	// current data is invitation
				Map<String, Object> item = new HashMap<String, Object>();
				String sender = map.get("sender").toString();		// u_name
				String club = map.get("joinClub").toString();		// c_name
				String date = map.get("invitationSentDate").toString();
				int from = Integer.parseInt(map.get("sender_uid").toString());		// marker for sender's uid
				int to = Integer.parseInt(map.get("receiver_uid").toString());			// marker for receiver's uid
				int where = Integer.parseInt(map.get("club_cid").toString());		// marker for club's cid
				item.put("sender", sender);
				item.put("club", club);
				item.put("date", date);
				item.put("from", from);
				item.put("to", to);
				item.put("where", where);
				item.put("type", 1);		// invitation
				
				System.out.println(sender + "invites u to join " + club + "  " + date);
				
				notifications.add(item);
			}
			
			if(map.get("invitationReplyDate") != null) {	// current data is invitation_reply
				Map<String, Object> item = new HashMap<String, Object>();
				String newbie = map.get("newbie").toString();		// u_name
				String club = map.get("myClub").toString();			// c_name
				String status = map.get("status").toString();		
				if (status.equals("1"))		//switch only be supported under the jre 1.7
					status = "accepted";		// 1=host unread, guest accepted
				else if (status.equals("2"))	// 2=host unread, guest refused
					status = "refused";
				String date = map.get("invitationReplyDate").toString();
				int sender = Integer.parseInt(map.get("sender").toString());		// uid
				int receiver = Integer.parseInt(map.get("receiver").toString());	// uid
				int cid = Integer.parseInt(map.get("cid").toString());				// cid
				item.put("newbie", newbie);
				item.put("club", club);
				item.put("status", status);
				item.put("date", date);
				item.put("sender", sender);
				item.put("receiver", receiver);
				item.put("cid", cid);
				item.put("type", 2);		// invitation reply
				System.out.println(newbie + " has " + status + " your invotation to join " + club + "  " + date);
				
				notifications.add(item);
			}
			
			if (map.get("newbieJoinDate") != null) {
				Map<String, Object> item = new HashMap<String, Object>();
				String newbie = map.get("newbie").toString();						// u_name
				String club = map.get("club").toString();							// c_name
				String date = map.get("newbieJoinDate").toString();
				int cid = Integer.parseInt(map.get("cid").toString());				// cid
				int sender = Integer.parseInt(map.get("sender").toString());		// uid
				int receiver = Integer.parseInt(map.get("receiver").toString());	// uid
				System.out.println("newbie:" + newbie + " club:" + club + " date:" + date);
				item.put("newbie", newbie);
				item.put("club", club);
				item.put("date", date);
				item.put("cid", cid);
				item.put("sender", sender);
				item.put("receiver", receiver);
				item.put("type", 3);		// newbie
				System.out.println(newbie + " is a part of your club(" + club + ") now");
				
				notifications.add(item);
			}
			
			if (map.get("joinClubApplyReplyDate") != null) {
				Map<String, Object> item = new HashMap<String, Object>();
				String leader = map.get("leader").toString();						// u_name
				String club = map.get("club").toString();							// c_name
				String date = map.get("joinClubApplyReplyDate").toString();
				String status = map.get("status").toString();
				if (status.equals("1"))
					status = "approved";
				if (status.equals("2"))
					status = "rejected";	
				int cid = Integer.parseInt(map.get("cid").toString());				// cid
				int sender = Integer.parseInt(map.get("sender").toString());		// uid
				int receiver = Integer.parseInt(map.get("receiver").toString());	// uid
				item.put("leader", leader);
				item.put("club", club);
				item.put("date", date);
				item.put("status", status);
				item.put("cid", cid);
				// sender=leaderUid, receiver=applicantrUid
				item.put("sender", sender);
				item.put("receiver", receiver);
				item.put("type", 4);		// join club apply reply
				
				notifications.add(item);
				
			}
			
			
			// only for leader
			if (map.get("joinClubApplyDate") != null) {
				Map<String, Object> item = new HashMap<String, Object>();
				String applicant = map.get("applicant").toString();					// u_name
				String club = map.get("club").toString();							// c_name
				String date = map.get("joinClubApplyDate").toString();		
				int cid = Integer.parseInt(map.get("cid").toString());				// cid		
				int sender = Integer.parseInt(map.get("sender").toString());		// uid
				int receiver = Integer.parseInt(map.get("receiver").toString());	// uid
				item.put("applicant", applicant);
				item.put("club", club);
				item.put("date", date);
				item.put("cid", cid);
				// sender=applicantUid, receiver=leaderUid
				item.put("sender", sender);
				item.put("receiver", receiver);
				item.put("type", 5);		// join club apply
				
				notifications.add(item);
			}
		}
		return notifications;
	}
	
	
	
	
	
	
	
	
	
	
	
	public void muteInvitationReplyInNotification (int senderUid, int receiverUid, int cid, String date) {
		this.userDao.muteInvitationReplyInNotification(senderUid, receiverUid, cid, date);
	}
	
	public void muteNewbieNoticeInNotification(int senderUid, int receiverUid, int cid) {
		this.userDao.muteNewbieNoticeInNotification(senderUid, receiverUid, cid);
	}
	
	public void muteJoinClubApplyNoticeInNotification(int senderUid, int receiverUid, int cid) {
		this.userDao.muteJoinClubApplyNoticeInNotification(senderUid, receiverUid, cid);
	}
	
	public void updateUserClubJob(int cid, int uid, String newJob) {
		int index = 0;
		int[] jobArray = userDao.getUserClubJobArray(uid);
		for (int currentCid : userDao.getUserClubArray(uid)) {
			if (cid == currentCid)
				jobArray[index] = userDao.getJobCode(newJob);
			else
				index++;
		}
		userDao.updateUserClubLevel(uid, jobArray);
	}
	
}
