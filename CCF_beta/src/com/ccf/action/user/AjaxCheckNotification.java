package com.ccf.action.user;

import java.util.List;
import java.util.Map;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class AjaxCheckNotification extends ActionSupport {

	/**@author stev
	 * @description fetch user's notification in Ajax way
	 * @date 07/14/2013
	 */
	private static final long serialVersionUID = -1130734034149186654L;
	
	private UserService service;
	private String notification;

	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotifications(String notification) {
		this.notification = notification;
	}
	@Override
	public String execute() throws Exception {
		User user = this.service.getCurrentUser();
		
		if (user == null)
			return SUCCESS;
		
		List<Map<String, Object>> notifications = this.service.checkNotification(user.getUid());
		if (notifications == null)
			return SUCCESS;
		StringBuilder sb = new StringBuilder();
		for (Map<String, Object> map : notifications) {
			switch (Integer.parseInt(map.get("type").toString())) {
			// type: 1=invitation, 2=invitation_reply, 3=newbie, 4=join_club_apply_reply, 5=join_club_apply
				case 1 :
					// like
					/*<div class="invitation-item">
						$1 wants to join $2 - $3 <a href="acceptInvitation?from=$4&to=$5&where=$6">accept</a>
						<a href="refuseInvitation?from=$4&to=$5&where=$6">refuse</a>
					</div>*/
					sb.append("<div class=\"invitation-item\">");
					sb.append(map.get("sender").toString());
					sb.append(" wants to join ");sb.append(map.get("club").toString());
					sb.append(" - ");sb.append(map.get("date").toString());
					sb.append("<a href=\"acceptInvitation?from=");sb.append(map.get("from").toString());
					sb.append("&to=");sb.append(map.get("to").toString());
					sb.append("&where=");sb.append(map.get("where").toString());
					sb.append("\">accept</a>");
					sb.append("<a href=\"refuseInvitation?from=");sb.append(map.get("from").toString());
					sb.append("&to=");sb.append(map.get("to").toString());
					sb.append("&where=");sb.append(map.get("where").toString());
					sb.append("\">refuse</a>");
					break;
				case 2 :
					/*<div class="invitation-reply-item">
						$1 has $2 your invitation to join $3 - $4
						 <a href="muteNotification?type=invitation-reply&sender=$5&receiver=$6&cid=$7&date=$4">
						got it</a>
					</div>*/
					sb.append("<div class=\"invitation-reply-item\">");
					sb.append(map.get("newbie").toString());
					sb.append(" has ");
					sb.append(map.get("status").toString());
					sb.append(" your invitation to join ");sb.append(map.get("club").toString());
					sb.append(" - ");sb.append(map.get("date").toString());
					sb.append("<a href=\"muteNotification?type=invitation-reply");
					sb.append("&sender=");sb.append(map.get("sender").toString());
					sb.append("&receiver=");sb.append(map.get("receiver").toString());
					sb.append("&cid=");sb.append(map.get("cid").toString());
					sb.append("&date=");sb.append(map.get("date").toString());
					sb.append("\">gotcha</a>");
					break;
				case 3 :
					/*<div class="newbie-notice-item">
						a new member $1 has just joined your club $2 - $3
						<a href="muteNotification?type=newbie-notice&sender=$4&receiver=$5&cid=$6">got it</a>
					</div>*/
					sb.append("<div class=\"newbie-notice-item\">");
					sb.append("a new member ");sb.append(map.get("newbie").toString());
					sb.append(" has just joined your club ");sb.append(map.get("club").toString());
					sb.append(" - ");sb.append(map.get("date").toString());
					sb.append("<a href=\"muteNotification?type=newbie-notice&sender=");
					sb.append(map.get("sender").toString());
					sb.append("&receiver=");sb.append(map.get("receiver").toString());
					sb.append("&cid=");sb.append(map.get("cid").toString());
					sb.append("\">got it</a>");
					break;
				case 4 :
					/*<div class="join-club-apply-reply-item">
						$leader has $status your apply to join $club - $date
						<a href="muteNotification?type=join-club-reply&sender=$sender&receiver=$receiver&cid=$cid">
						I see</a>
					</div>*/
					sb.append("<div class=\"join-club-apply-reply-item\">");
					sb.append(map.get("leader").toString());
					sb.append(" has ");sb.append(map.get("status").toString());
					sb.append(" your apply to join ");sb.append(map.get("club").toString());
					sb.append(" - ");sb.append(map.get("date").toString());
					sb.append("<a href=\"muteNotification?type=join-club-reply");
					sb.append("&sender=");sb.append(map.get("sender").toString());
					sb.append("&receiver=");sb.append(map.get("receiver").toString());
					sb.append("&cid=");sb.append(map.get("cid").toString());
					sb.append("\">I see</a></div>");
					break;
				case 5 :
					/*<div class="join-club-apply-item">
					$applicant wants to join your club $club - $date
					 <a href="responseJoinClubApply?action=approve&sender=$sender&receiver=$receiver&cid=$cid">
					<span class="approve">approve</span></a>
						<a href="responseJoinClubApply?action=reject&sender=$sender&receiver=$receiver&cid=$cid">
					<span class="reject">negative</span></a>
					</div>*/
					sb.append("<div class=\"join-club-apply-item\">");
					sb.append(map.get("applicant").toString());
					sb.append(" wants to join your club ");sb.append(map.get("club").toString());
					sb.append(" - ");sb.append(map.get("date").toString());
					sb.append(" <a href=\"responseJoinClubApply?action=approve");
					sb.append("&sender=");sb.append(map.get("sender").toString());
					sb.append("&receiver=");sb.append(map.get("receiver").toString());
					sb.append("&cid=");sb.append(map.get("cid").toString());
					sb.append("\"><span class=\"reject\">negative</span></a></div>");
					break;
			}
		}
		notification = sb.toString();
		System.out.println(notification);
		
		return SUCCESS;
		
		// parse to JSON
		/*if (notifications == null)
			System.out.println("Notifications null");
		else {
			JSONArray jsonArr = new JSONArray();
			for (Map<String, Object> map : notifications) {
				JSONObject jsonObj = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					jsonObj.put(entry.getKey(), entry.getValue());
				}
				jsonArr.add(jsonObj);
			}
			System.out.print("it's jsonArr:" + jsonArr.toString());
			notification = jsonArr.toString();
		}*/
	}
	
}
