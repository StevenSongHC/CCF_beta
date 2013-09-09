package com.ccf.action.user;

import com.ccf.service.ClubService;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class PersonelChangeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6795726093724829019L;
	
	private int cid;
	private String account;
	private String oldJob;
	private String newJob;
	private UserService userService;
	private ClubService clubService;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOldJob() {
		return oldJob;
	}
	public void setOldJob(String oldJob) {
		this.oldJob = oldJob;
	}
	public String getNewJob() {
		return newJob;
	}
	public void setNewJob(String newJob) {
		this.newJob = newJob;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public ClubService getClubService() {
		return clubService;
	}
	public void setClubService(ClubService clubService) {
		this.clubService = clubService;
	}
	@Override
	public String execute() throws Exception {
		if (!this.userService.verifyLeader(cid, this.userService.getCurrentUser().getUid()))
			return LOGIN;
		
		System.out.println("-------------------- cid:" + cid + " account:" + account + " oldJob:" + oldJob + " newJob:" + newJob + " -------------------- ");
		
		if (oldJob == null || newJob == null) {
			System.out.println("emptyShit");
			return "emptyShit";
		}
		
		int staffUid = userService.findUserByAccount(account).getUid();		// make it simple when refactor, like, "getUidByAccount()"
		// member -> publisher
		if (oldJob.equals("member") && newJob.equals("publisher")) {
			System.out.println("=====  member -> publisher  =====");
			// removed from member data first, then added to publisher data
			clubService.removeMember(cid, staffUid);		
			clubService.addPublisher(cid, staffUid);
			userService.updateUserClubJob(cid, staffUid, "publisher");
		}
		
		// publisher -> member
		if (oldJob.equals("publisher") && newJob.equals("member")) {
			System.out.println("=====  publisher -> member  =====");
			// removed from publisher data first, then added to member data
			clubService.removePublisher(cid, staffUid);
			clubService.addMember(cid, staffUid);
		}
		
		return SUCCESS;
	}

}
