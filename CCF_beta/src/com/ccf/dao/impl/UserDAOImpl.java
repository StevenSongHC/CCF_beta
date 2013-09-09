package com.ccf.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ccf.bean.User;
import com.ccf.dao.UserDAO;
import com.ccf.util.FormatTransformer;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
	
	private FormatTransformer format = new FormatTransformer();
	
	@SuppressWarnings("unchecked")
	public List<Object[]> querySql(String sql) {
		System.out.println("sql check:" + sql);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		//.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP), then use map.get(String) to get the values
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		/*SQLQuery query = session.createSQLQuery(sql);*/
		session.getTransaction().commit();
		return (List<Object[]>) query.list();
	}
	
	public Object executeSql(String sql) {
		System.out.println("sql check:" + sql);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		Query query = session.createSQLQuery(sql);  
		session.getTransaction().commit();
		return (Object) query.uniqueResult();
	}
	
	public void runSql(String sql) {
		System.out.println("sql check:" + sql);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> executeHql(String hql) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		Query query = session.createQuery(hql);  
		session.getTransaction().commit();
		return (List<Object[]>) query.list();
	}
	
	public Object fetchHqlUniqueResult(String hql) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		Query query = session.createQuery(hql);  
		session.getTransaction().commit();
		return query.uniqueResult();
	}
	
	public void registerUser(User user) {
		this.getHibernateTemplate().save(user);
		int uid = Integer.parseInt(executeSql("select count(*) from user").toString());
		//鍒涘缓涓�釜鏂扮敤鎴锋椂鍚屾椂鏂板缓notification 琛ㄤ竴涓柊璁板綍
		String sql = "insert into notification(uid,notice,invitation,invitation_reply,join_club_apply," +
				"join_club_apply_reply,newbie,message,new_subscription_amount,subscription,comment,file) values (" + 
				uid + ",0,0,0,0,0,0,0,0,'',0,0)";
		runSql(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> fetchAllUsersInfo() {
		String hql = "from User user order by user.uid desc";
		return (List<User>)this.getHibernateTemplate().find(hql);
	}
	
	public User findUserByUid(int uid) {
		System.out.println(" find uid:" + uid);
		return (User)this.getHibernateTemplate().get(User.class, uid);
	}
	
	//For entering logged user homepage
	public User findUserByAccount(String account) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		String hql = "From User user where user.u_account='" + account + "'";
		System.out.println("hql check:" + hql);	//For debug using
		Query query = session.createQuery(hql);  
		session.getTransaction().commit();
		return (User) query.uniqueResult();
	}
	
	//Basic User Component,just for leader searching temporarily
	public User findUserByCode(String code) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		String hql = "From User user where user.u_code='" + code + "'";
		System.out.println("hql check:" + hql);	//For debug using
		Query query = session.createQuery(hql);  
		session.getTransaction().commit();
		User user = (User) query.uniqueResult();
		System.out.println("Got one by the code:" + code + "->" + user.getU_name()); 
		/*Criteria Solution
		 Criteria criteria = session.createCriteria(User.class):
		 Criterion criterionCode = Expression.eq("u_code", code);
		 criteria.add(criterionCode);
		 user = (User) criteria.uniqueResult();
		 */
		return user;
	}
	
	public int findUidByAccount(String account) {
		return Integer.parseInt(executeSql("select uid from user where u_account='" + account + "'").toString());
	}
	
	public List<User> findUserSetByUids(String uids) {
		List<User> users = new ArrayList<User>();
		for (int uid : format.transformString2IntegerArray(uids, ",")) {
			users.add(findUserByUid(uid));
		}
		return users;
	}

	public int[] getUserClubArray(int uid) {
		return format.transformString2IntegerArray(executeSql("SELECT u_clubs FROM user WHERE uid=" + uid).toString(), ",");
	}
	
	public int[] getUserClubJobArray(int uid) {
		return format.transformString2IntegerArray(executeSql("SELECT u_clubs_level FROM user WHERE uid=" + uid).toString(), ",");
	}
	
	public int getJobCode(String joName) {
		return Integer.parseInt(executeSql("SELECT joid FROM job WHERE jo_name='" + joName + "'").toString());
	}
	
	public boolean updateUserClubLevel(int uid, int [] newJobArray) {
		String sql = null;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			sql = "UPDATE user SET u_clubs_level='" + 
					format.transformIntegerArray2String(newJobArray, ",") + "' WHERE uid=" + uid;
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
		} catch(RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}
		finally {
			session.close();
		}
		return true;
	}
	
	@SuppressWarnings("null")
	public List<User> findUserSetByCodes(String[] codes) {
		List<User> users = null;
		for(int i=0; i<codes.length; i++) {
			User user = findUserByCode(codes[i]);
			users.add(user);
		}	//It might returns null
		return users;
	}
	
	public void  updateUserBasicProfile(User user) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();  
		session.beginTransaction();  
		String hql = "update User user set user.u_name='" + user.getU_name() 
				+ "',user.u_college='" + user.getU_college() + "',user.u_pic='" 
				+ user.getU_pic() + "',user.u_summary='" + user.getU_summary() 
				+ "',user.u_clubs='" + user.getU_clubs() + "',user.u_clubs_level='" 
				+ user.getU_clubs_level() + "' where uid=" + user.getUid();
				
		System.out.println("hql check:" + hql);	//For debug using
		Query query = session.createQuery(hql);  
		query.executeUpdate();  
		session.getTransaction().commit();
		
		/*this.getHibernateTemplate().update(user);*/	//鏇存柊鏁版嵁搴撴墍鏈夋暟鎹椂鎵嶇敤Only for data resetting using
	}
	
	//Validate by checking account and password first,then return user's data
	public User login(String account, String password) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		/*session.clear();*/
		String hql = "From User user where user.u_account='" + account + "'";
		System.out.println("hql check:" + hql);	//For debug using
		Query query = session.createQuery(hql);  
		session.getTransaction().commit();
		return (User) query.uniqueResult();
	}
	
	// return user's job list in mapping format
	public Map<String, String> userJobList(int uid) {
		Map<String, String> jobList = new HashMap<String, String>();
		// fetch related data from database
		String sql = "select u_clubs,u_clubs_level from user where uid=" + uid;
		// parse the data
		Map<?, ?> map = (Map<?, ?>) (Object) querySql(sql).get(0);
		String clubData = map.get("u_clubs").toString();
		String jobData = map.get("u_clubs_level").toString();
		
		if (clubData.equals("") || jobData.equals(""))		// well, in case...
		{	System.out.println("NULL");return null;}
		System.out.println("clubData:" + clubData);
		int[] clubs = format.transformString2IntegerArray(clubData, ",");
		int[] jobs = format.transformString2IntegerArray(jobData, ",");		// ATTENTION: mutilated job is not ready
		for (int i=0; i<clubs.length; i++) {
			// fetch different clubs' title
			sql = "select club.cid,job.jo_name" +
					" from club inner join job on job.joid=" + jobs[i] + " where club.cid=" + clubs[i];
			map = (Map<?, ?>) (Object) querySql(sql).get(0);
			// push in
			jobList.put(map.get("cid").toString(), map.get("jo_name").toString());
		}
		return jobList;
	}
	
	// just returns club's cid & c_name plus user's job
	public List<Map<String, Object>> userClubList(int uid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, String> jobMap = userJobList(uid);
		String sql = "SELECT u_clubs FROM user WHERE uid=" + uid;
		/*Map<?, ?> map = (Map<?, ?>) (Object) querySql(sql).get(0);*/
		String clubs = executeSql(sql).toString();
		//  no club yet
		if (clubs.equals(""))
			return null;
		System.out.println("clubsNAME" + clubs);
		int[] clubIds = format.transformString2IntegerArray(clubs, ",");
		for (int cid : clubIds) {
			sql = "SELECT c_name from club where cid=" + cid;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cid", cid);
			map.put("clubname", executeSql(sql).toString());
			map.put("jobname", jobMap.get(String.valueOf(cid)));
			list.add(map);
		}
		return list;
	}
	
	public boolean verifyLeader(int cid, int uid) {		//鍙娇鐢ㄨ繖涓や釜鍒ゆ柇骞朵笉瀹夊叏锛屼箣鍚庤浣跨敤User瀵硅薄鍒ゆ柇 use User class to verify
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		String hql = "select club.c_code_current_leader from Club club where club.cid=" + cid;	//change leader to int
		System.out.println("hql check:" + hql);
		Query query = session.createQuery(hql);  
		session.getTransaction().commit();
		
		System.out.println("the leader of club " + cid + " is " + (String) query.uniqueResult());
		
		int leader = Integer.parseInt((String) query.uniqueResult());
		if (uid != leader) //妫�祴鍒版潈闄愪笉鏄痩eader verify failed
			return false;
		System.out.println(uid + " is leader of " + cid);
		return true;
	}
	
	
	public int[] memberUidList(int cid) {
		String sql = "select c_members from club where cid=" + cid;
		return format.transformString2IntegerArray(executeSql(sql).toString(), ",");
	}
	
	public int[] clubMembersUid(int cid) {
		return format.transformString2IntegerArray(executeSql(
				"select c_members from club where cid=" + cid).toString(), ",");
	}
	
	public List<Object[]> clubInvitingList(int cid) {
		return querySql("select * from invitation where status=0 and cid=" + cid);
	}
	
	public List<Object[]>clubJoinApplyList(int cid) {
		return querySql("SELECT * FROM join_club_apply WHERE status=0 and cid=" + cid);
	}
	
	//a way to become a member of a club is to be invited by the leader of that club
	public void invite(int cid, int hostUid, int guestUid) {
		//get the instance date
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(now);
		System.out.println("Date:" + date);
		
		//insert new data into table1->invitation
		String sql = "insert into invitation(cid,host_uid,guest_uid,date,status) values (" + cid + "," + 
						hostUid + "," + guestUid + ",'" + date + "',0)";
		runSql(sql);	//execute sql to storage the invitation data
		
		//update table2->guest's notification.invitation
		sql = "update notification set invitation=invitation+1 where uid=" + guestUid;
		runSql(sql);
	}
	
	public void acceptInvition(int hostUid, int guestUid, int cid) {
		// add new member in club table
		// status=1 means accept, but host don't know yet, & update the date
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(now);
		String sql = "update invitation set status=1,date='" + date + "'" + 
						" where host_uid=" + hostUid + " and guest_uid=" + guestUid + " and cid=" + cid;
		// update invitation's status=1, which means invitation accepted
		runSql(sql);	
		
		// update guest(newbie a.k.a. receiver)'s invitation Amount -= 1
		sql = "update notification set invitation=invitation-1 where uid=" + guestUid;
		runSql(sql);
		
		// update host(leader a.k.a. sender)'s invitation_reply Amount += 1
		sql = "update notification set invitation_reply=invitation_reply+1 where uid=" + hostUid;
		runSql(sql);
		
		// add user to club's members
		sql = "update club set c_members=concat(c_members,'" + String.valueOf(guestUid) + ",') where cid=" + cid;
		runSql(sql);
		
		// update user's clubs
		sql = "update user set u_clubs=concat(u_clubs,'" + String.valueOf(cid) + ",') where uid=" + guestUid;
		runSql(sql);
		// and user's clubs_level
		sql = "update user set u_clubs_level=concat(u_clubs_level,'5,') where uid=" + guestUid;	// default level is 5
		runSql(sql);
		
		// let every member in the club but leader and newbie knows there's a newbie
		// step 1: insert a new data of newbie into 'newbie' table, means you are a member of that club now
		sql = "insert into newbie(uid,cid,newbie_uid,date) values (" + guestUid + "," + cid + ",'','')";
		runSql(sql);
		// step 2: push a newbie(guestUid in this context) notification to every old members in that club but leader
		sql = "update newbie set newbie_uid=concat(newbie_uid,'" + guestUid + ",'),date=concat(date,'" + date + ",') where cid=" + cid + 
				" and uid<>(select c_code_current_leader from club where cid=" + cid + ") and uid<>" + guestUid;
		runSql(sql);
		// step 3: add notification's newbie amount data
		sql = "select c_members from club where cid=" + cid;
		int[] pals = format.transformString2IntegerArray(executeSql(sql).toString(), ",");
		for (int pal : pals) {
			if (pal != guestUid && pal != hostUid) {	// if this pal is not leader or newbie
				sql = "update notification set newbie=newbie+1 where uid=" + pal;
				runSql(sql);
			}
		}
	}
	
	public void refuseInvition(int hostUid, int guestUid, int cid) {
		// update invitation's status=2 (means refuse, host don't know yet) where houst_uid=hostUId and guest_uid=guestUid
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(now);
		String sql = "update invitation set status=" + 2 + ",date='" + date + "'" + 
						" where host_uid=" + hostUid + " and guest_uid=" + guestUid + " and cid=" + cid;
		runSql(sql);
		
		// update guest's invitation invitation Amount -= 1
		sql = "update notification set invitation=invitation-1 where uid=" + guestUid;
		runSql(sql);
		
		// update host's notification invitation_reply Amount += 1
		sql = "update notification set invitation_reply=invitation_reply+1 where uid=" + hostUid;
		runSql(sql);
	}
	
	// this is just a join request sent by a user, needs leader's permission
	public void joinClub(int uid, int cid) {
		//鐢宠鐨勬椂闂�date of apply
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(now);
		System.out.println("Date:" + date);
		
		//鍚ц鏉¤褰曚繚瀛樺叆join_club_apply table涓紝璇存槑鏈変汉鎯冲姞鍏lub, status=0 means leader not read
		String sql = "INSERT INTO join_club_apply(cid,leader_uid,applicant_uid,date,status) VALUES (" + 
						cid + ",(SELECT c_code_current_leader FROM club WHERE cid=" + cid + ")," + uid + 
						",'" + date + "',0)";
		runSql(sql);
		// 鍦ㄨclub's leader鐨刵otification 琛ㄤ负join_club_apply璁板綍plus one
		sql = "update notification set join_club_apply=join_club_apply+1 where uid=" +
				"(select c_code_current_leader from club where cid=" + cid + ")";
		runSql(sql);
	}
	
	// only for leader
	public void approveJoinClubApply(int cid, int applicantUid) {
		// update apply's status=1, means leader has read and accept it, & uppdate the date
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(now);
		String sql = "UPDATE join_club_apply SET status=1,date='" + date + 
						"' WHERE cid=" + cid + " AND applicant_uid=" + applicantUid;
		runSql(sql);
		
		// update leader notification.join_club_apply=join_club_apply-1
		sql = "update notification set join_club_apply=join_club_apply-1 where uid=(SELECT c_code_current_leader" +
				" FROM club WHERE cid=" + cid + ")";
		runSql(sql);
		
		// update applicant's join_club_apply_reply=join_club_apply_reply+1, let them know their apply has been responded
		sql = "update notification set join_club_apply_reply=join_club_apply_reply+1 where uid=" + applicantUid;
		runSql(sql);
	}
	
	public void rejectJoinClubApply(int cid, int applicantUid) {
		// fuck him, update leader and applicant's notification's join_club_apply and join_club_apply_reply
		String sql = "update notification set join_club_apply=join_club_apply-1 where uid=" +
				"(select c_code_current_leader from club where cid=" + cid + ")";
		runSql(sql);
		
		sql = "update notification set join_club_apply_reply=join_club_apply_reply+1 where uid=" + applicantUid;
		runSql(sql);
		
		// update join_club_apply.status=2 which means this apply has been rejected, & update the date
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(now);
		sql = "update join_club_apply set status=2,date='" + date + 
				"' where cid=" + cid + " and applicant_uid=" + applicantUid;
		runSql(sql);
	}
	
	// add new member function, NOT including newbie notification function
	public void addANewMember(int cid, int newbieUid) {
		// add applicant into club's members
		// step1 : update c_members data
		String sql = "UPDATE club SET c_members=concat(c_members,'" + newbieUid + ",') where cid=" + cid;
		runSql(sql);
		// step2 : update newbie's user.clubs & user.clubs_levels(default-5)
		sql = "UPDATE user SET u_clubs=concat(u_clubs,'" + cid + ",'),u_clubs_level=concat(u_clubs_level,'5,')" +
				" where uid=" + newbieUid;
		runSql(sql);
		
		// let every old member in the club knows newbie's existence 
		//step 1 : insert a newbie's new data in newbie table
		sql = "INSERT INTO newbie(uid,cid,newbie_uid,date) VALUES (" + newbieUid + "," + cid + ",'','')";
		runSql(sql);
		// step 2 : update old members' newbie data, except newbie and leader's
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(now);
		sql = "UPDATE newbie SET newbie_uid=concat(newbie_uid,'" + newbieUid + ",'),date=concat(date,'" + date + 
				",') WHERE cid=" + cid + " AND uid<>(SELECT c_code_current_leader FROM club WHERE cid=" + cid + 
				") AND uid<>" + newbieUid;
		runSql(sql);
		// step 3 : update old members' notification.newbie=newbie+1
		sql = "select c_members from club where cid=" + cid;
		int[] members = format.transformString2IntegerArray(executeSql(sql).toString(), ",");
		int leaderUid = Integer.parseInt(executeSql("select c_code_current_leader from club where cid=" + cid).toString());
		for (int member : members) {
			if (member != newbieUid && member != leaderUid) {
				sql = "update notification set newbie=newbie+1 where uid=" + member;
				runSql(sql);
			}
		}
		
	}
	
	public boolean isNotificationEmpty(int uid) {
		String sql = "SELECT notice+invitation+invitation_reply+join_club_apply+join_club_apply_reply" +
						"+newbie+message+new_subscription_amount+comment+file FROM notification WHERE uid=" + uid;
		Object result = executeSql(sql);
		// cuz someone's notification did not existed
		if (result == null || Integer.parseInt(result.toString()) == 0)	
				return true;
		return false;
	}
	
	//check user's notification from datatable->notification
	public List<Object[]> checkNotification(int uid) {
		
		if (isNotificationEmpty(uid))
			return null;
		
		List<Object[]> notifications = new ArrayList<Object[]>();
		String sql = "select * from notification where uid=" + uid;
		List<Object[]> notificationAmount = querySql(sql);
		for (Object obj : notificationAmount) {
			Map<?, ?> amount = (Map<?, ?>) obj;
			int invitationAmount = Integer.parseInt(amount.get("invitation").toString());
			int invitationReplyAmount = Integer.parseInt(amount.get("invitation_reply").toString());
			int newbieAmount = Integer.parseInt(amount.get("newbie").toString());
			int joinApplyReplyAmount = Integer.parseInt(amount.get("join_club_apply_reply").toString());
			
			//only for leader
			int joinApplyAmount = Integer.parseInt(amount.get("join_club_apply").toString());
			
			System.out.println("Your have " + invitationAmount + " invitation(s) " + 
					invitationReplyAmount + " response(s)," + newbieAmount + " newbies, total " + 
					(invitationAmount+invitationReplyAmount+newbieAmount) + " notification(s)");
			
			// notification.invitation not empty
			if (invitationAmount != 0) {	//if new invitation existed (invitation>0)
				// query invitation table by guest_uid
				sql = "select user.u_name as sender,club.c_name as joinClub,invitation.date as invitationSentDate," + 
						"invitation.host_uid as sender_uid,invitation.guest_uid as receiver_uid," +
						"invitation.cid as club_cid " + 
						"from invitation inner join user on user.uid=invitation.host_uid " + 
						"inner join club on club.cid=invitation.cid " +
						"where invitation.guest_uid=" + uid + " and invitation.status=0";
				notifications.addAll(querySql(sql));
			}
			
			// notification.invitation_reply not empty
			if (invitationReplyAmount != 0)	{	//if new invitation reply existed (invitation_reply>0)
				// query invitation_reply table by host_uid, status<3(0,1,2) means invitation's status unread
				sql = "select user.u_name as newbie,club.c_name as myClub," + 
						"invitation.status as status,invitation.date as invitationReplyDate," +
						"invitation.guest_uid as sender,invitation.host_uid as receiver,invitation.cid as cid " +
						"from invitation inner join user on user.uid=invitation.guest_uid " + 
						"inner join club on club.cid=invitation.cid " +
						"where invitation.host_uid=" + uid + " and invitation.status<3";
				notifications.addAll(querySql(sql));
			}
			
			// notification.newbie not empty
			if (newbieAmount != 0) {
				sql = "select cid,newbie_uid,date from newbie where uid=" + uid;
				for (Object o : querySql(sql)) {
					Map<?, ?> map = (Map<?, ?>) o;
					System.out.println("o" + o.toString());
					int cid = Integer.parseInt(map.get("cid").toString());
					int[] newbies = format.transformString2IntegerArray(map.get("newbie_uid").toString(), ",");
					String[] date = map.get("date").toString().split(",");
					for (int i=0; i<newbies.length; i++) {
						// 璇峰嬁鏈涙枃鐢熶箟,鑰屼笖鍒贡鏀硅繖浜涜緝闀跨殑鐨剆ql
						sql = "select user.u_name as newbie,club.c_name as club,'" + date[i] + "' as newbieJoinDate," + 
						cid + " as cid,newbie.uid as receiver,user.uid as sender from newbie" + 
						" inner join user on user.uid=" + newbies[i] + 
						" inner join club on club.cid=" + cid + " where newbie.uid=" + uid + " and newbie.cid=" + cid;
						
						notifications.addAll(querySql(sql));
					}
				}
			}
			
			
			// if current user is leader, those can do some meaningful job
			if (joinApplyAmount != 0) {
				sql = "select user.u_name as applicant,club.c_name as club,join_club_apply.date as joinClubApplyDate," +  
						"join_club_apply.cid as cid,join_club_apply.applicant_uid as sender," +
						"join_club_apply.leader_uid as receiver from join_club_apply" +
						" inner join user on user.uid=join_club_apply.applicant_uid" +
						" inner join club on club.cid=join_club_apply.cid where leader_uid=" + uid + 
						" and status=0";

				notifications.addAll(querySql(sql));
			}
			
			
			
			// notification.join_club_apply_reply is not empty, and this is all about the applicant, so he is the receiver
			if (joinApplyReplyAmount != 0) {
				sql = "select user.u_name as leader,club.c_name as club,join_club_apply.date as joinClubApplyReplyDate," + 
						"join_club_apply.cid as cid,join_club_apply.leader_uid as sender," +
						"join_club_apply.applicant_uid as receiver,join_club_apply.status as status" +
						" from join_club_apply inner join user on user.uid=join_club_apply.leader_uid" +
						" inner join club on club.cid=join_club_apply.cid where applicant_uid=" + uid + 
						" and status>0";		// means been replied but don't know yet
							
				notifications.addAll(querySql(sql));
			}
			
			
		}
		return notifications;
	}
	
	// mute invitation data in notification list
	public void muteInvitationReplyInNotification(int senderUid, int receiverUid, int cid, String date) {
		// two table effected, invitation.status & notification.invitation_reply
		
		// first step: update invitation's status+=2
		String sql = "update invitation set status=status+2 where cid=" + cid + 
				" and host_uid=" + receiverUid + " and guest_uid=" + senderUid + 
				" and date='" + date + "'";	// status+2 means read(a.k.a. R)
		runSql(sql);
		
		// the second one: update notification's invitation -=1
		sql = "update notification set invitation_reply=invitation_reply-1" + " where uid=" + receiverUid;
		runSql(sql);
	}
	
	public void muteJoinClubApplyNoticeInNotification(int senderUid, int receiverUid, int cid) {
		// two tables effected, join_club_apply.status & notification.join_club_apply_reply
		
		// step 1 : delete join_club_apply data
		String sql = "DELETE FROM join_club_apply WHERE leader_uid=" + senderUid + 
						" AND applicant_uid=" + receiverUid + " AND cid=" + cid;
		runSql(sql);
		// step 2 : update applicant's notification.join_club_apply_reply-=1
		sql = "UPDATE notification SET join_club_apply_reply=join_club_apply_reply-1 where uid=" + receiverUid;
		runSql(sql);
	}
	
	public void muteNewbieNoticeInNotification(int senderUid, int receiverUid, int cid) {
		// two tables effected, newbie.newbie_uid & notification.newbie
		
		// delete sender(newbie)'s newbie_uid data in newbie
		String sql = "update newbie set newbie_uid=replace(newbie_uid,'" + senderUid + ",','') " +
						"where uid=" + receiverUid + " and cid=" + cid;
		runSql(sql);
		
		// subtract 1 ,receiver(user self)'s newbie amount
		sql = "update notification set newbie=newbie-1 where uid=" + receiverUid;
		runSql(sql);
	}
	
}
