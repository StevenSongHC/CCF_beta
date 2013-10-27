package com.ccf.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;

import com.ccf.bean.User;
import com.ccf.service.ClubService;
import com.ccf.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class SearchSuggestionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8623064017785344837L;
	
	private UserService userService;
	private ClubService clubService;
	private String sort;
	private String keyword;
	private String result;

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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String execute() throws Exception {
		System.out.println("............sort:" + sort + " keyword:" + keyword);
		List<Map<String, Object>> suggestions = new ArrayList<Map<String, Object>>();
		
		System.out.println("啥");
		
		// if you wanna search a man
		if (sort.equals("man")) {
			List<User> users = userService.getUserListByNameLike(keyword, 5);		// only return 5 results
			if (users.size() == 0)
				System.out.println("suggestion: Nothing found");
			else {
				for (User user : users) {
					System.out.println("suggestion:" + user.getU_name());
					Map<String, Object> suggestion = new HashMap<String, Object>();
					suggestion.put("name", user.getU_name());
					suggestion.put("account", user.getU_account());
					suggestions.add(suggestion);
				}
			}
		}
		
		result = JSONArray.fromObject(suggestions).toString();
		System.out.println(result);
		
		return SUCCESS;
	}

}
