package com.ccf.action.stev;

import java.util.ArrayList;
import java.util.List;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.ccf.util.TestFreemarkerUtil;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.SimpleHash;

public class FreemarkerUserList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4188064772873209493L;
	private UserService service;
	private String htmlPage;
	
	public UserService getService() {
		return service;
	}
	
	public void setService(UserService service) {
		this.service = service;
	}

	public String getHtmlPage() {
		return htmlPage;
	}

	public void setHtmlPage(String htmlPage) {
		this.htmlPage = htmlPage;
	}

	@Override
	public String execute() throws Exception {
		TestFreemarkerUtil fm = new TestFreemarkerUtil(); 
		List<User> users = new ArrayList<User>();
		users = this.service.fetchUsersList();	//get user list
		String[] clubs;
		List<String> club_name = new ArrayList<String>();
		List<String> club_url = new ArrayList<String>();
		List<String> club_job = new ArrayList<String>();
		
		SimpleHash root = new SimpleHash();
		for(User user : users) {
			clubs = user.getU_clubs().split(",");
			if (clubs.length == 1 && clubs[0].equals("null")) {	//user没有club，即null
				club_name.add("");
				club_url.add("");
				club_job.add("");
			}
			else {												//当user拥有不止一个club时
				for(int i=0; i<clubs.length; i++) {
					String sql = "select club.c_name,club.c_homepage,job.jo_name"
							+ " from user inner join club on user.u_clubs=club.cid" 
							+ " inner join job on user.u_clubs_level=job.joid" 
							+ " where user.uid=" + user.getUid();
					System.out.println("hi,sql:" + sql);
					List<Object[]> results = this.service.querySql(sql);
					for(Object[] obj : results) {
						/*root.put("shit", (String) obj[2]);*/
						club_name.add((String) obj[2]);
						club_url.add((String) obj[3]);
						club_job.add((String) obj[4]);
						System.out.println(user.getUid() + ":" + "name:(" + user.getU_name() + "_club_name_list) " + (String) obj[2]);
						System.out.println(user.getUid() + ":" + "name:(" + user.getU_name() + "_club_url_list) " + (String) obj[3]);
						System.out.println(user.getUid() + ":" + "name:(" + user.getU_name() + "_club_job_list) " + (String) obj[4]);
					}
				}
				root.put(user.getU_name()+"_club_name_list", club_name);
				root.put(user.getU_name()+"_club_url_list", club_url);
				root.put(user.getU_name()+"_club_job_list", club_job);
			}
		}
		/*Map<String, List<User>> map = new HashMap<String, List<User>>();
		map.put("userlist", users);*/
		root.put("userlist", users);
		
		String htmlFileName = "userlist.html";
		
		fm.initUserList("users.ftl", htmlFileName, root, "templates");
		this.htmlPage = htmlFileName;
		return SUCCESS;
	}
	
}
