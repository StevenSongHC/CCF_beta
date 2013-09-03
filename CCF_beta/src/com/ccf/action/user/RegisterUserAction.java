package com.ccf.action.user;

import com.ccf.bean.User;
import com.ccf.service.UserService;
import com.ccf.util.UserCodeUtil;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterUserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DEFAULT_COLLEGE = "unknown";
	public static final String DEFAULT_PIC = "images/author_default.png";
	public static final String DEFAULT_HOMEPAGE = "index.jsp";
	public static final String DEFAULT_CLUBS = "";
	public static final String DEFAULT_CLUBS_LEVEL = "";
	public static final int DEFAULT_XP = 0;

	private String college,username,account,password,password_cfm,summary;
	private User user = new User();
	private UserService service;
	private UserCodeUtil codeUtil = new UserCodeUtil();

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_cfm() {
		return password_cfm;
	}

	public void setPassword_cfm(String password_cfm) {
		this.password_cfm = password_cfm;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public UserCodeUtil getCodeUtil() {
		return codeUtil;
	}

	public void setCodeUtil(UserCodeUtil codeUtil) {
		this.codeUtil = codeUtil;
	}

	@Override
	public void validate() {
		if("".equals(username))	//Empty username
			this.addFieldError("username", "Username required");
		else {	//Username is good
			if("".equals(account))	//Empty account
				this.addFieldError("account", "You Must Input A Account");
			else{	//Account is not empty
				if(service.findUserByAccount(account) != null)	//Already Existed Account
					this.addFieldError("account", "This Account Has Been Used,Please Change Another One");
				else {	//Account is good
					if("".equals(password)) 	//Empty password
						this.addFieldError("password", "Please Input Password!");
					else {	//Password is good
						if(password.length() < 6 || password.length() > 20)	//Bad length
							this.addFieldError("password", "The Length Of Password Is Not Allowed!");
						else {	//Length is good
							if("".equals(password_cfm))	//No confirm password
								this.addFieldError("password_cfm", "Please Confirm Your Password");
							else if(!password_cfm.equals(password))	//Password Confirm Failed
								this.addFieldError("password_cfm", "Reconfirm Your Password");
						}
					}
				}
			}
		}
		
	}

	@Override
	public String execute() throws Exception {
		//调用service方法 complete action from front page
		System.out.println("Comman User Register:" + college + username + account + password);
		if(college.equals("") || college == null)
			this.user.setU_college(DEFAULT_COLLEGE);
		this.user.setU_code(codeUtil.generateUserCode());
		this.user.setU_name(this.username);
		this.user.setU_account(account);
		this.user.setU_password(password);
		this.user.setU_summary(summary);
		this.user.setU_pic(DEFAULT_PIC);
		this.user.setU_homepage(DEFAULT_HOMEPAGE);
		this.user.setU_clubs(DEFAULT_CLUBS);
		this.user.setU_clubs_level(DEFAULT_CLUBS_LEVEL);
		this.user.setU_xp(DEFAULT_XP);
		this.service.register(this.user);
		return SUCCESS;
	}
	
}
