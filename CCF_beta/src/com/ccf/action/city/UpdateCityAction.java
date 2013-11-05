package com.ccf.action.city;

import net.sf.json.JSONObject;

import com.ccf.bean.City;
import com.ccf.service.CityService;
import com.ccf.util.FileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCityAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7393866467490859554L;
	private CityService ctService;
	private String result;
	private int ctid;
	private String newName;
	private String newCnName;
	private int newCollegeAmount;
	private int newClubAmount;
	private int newPrid;
	public CityService getCtService() {
		return ctService;
	}
	public void setCtService(CityService ctService) {
		this.ctService = ctService;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCtid() {
		return ctid;
	}
	public void setCtid(int ctid) {
		this.ctid = ctid;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getNewCnName() {
		return newCnName;
	}
	public void setNewCnName(String newCnName) {
		this.newCnName = newCnName;
	}
	public int getNewCollegeAmount() {
		return newCollegeAmount;
	}
	public void setNewCollegeAmount(int newCollegeAmount) {
		this.newCollegeAmount = newCollegeAmount;
	}
	public int getNewClubAmount() {
		return newClubAmount;
	}
	public void setNewClubAmount(int newClubAmount) {
		this.newClubAmount = newClubAmount;
	}
	public int getNewPrid() {
		return newPrid;
	}
	public void setNewPrid(int newPrid) {
		this.newPrid = newPrid;
	}
	
	public String execute() throws Exception {
		City city = new City();
		city.setId(ctid);
		city.setName(newName);
		city.setCnName(newCnName);
		city.setCollegeAmount(newCollegeAmount);
		city.setClubAmount(newClubAmount);
		city.setPrid(newPrid);
		// get the old name of this city
		/*String oldName = ctService.getCity(ctid).getName();*/
		// update city folder name
		/*new FileUtil().renameFolder("archive-activities", oldName, newName);*/
		// then update database
		ctService.update(city);
		
		result = JSONObject.fromObject(ctService.getCity(city.getId())).toString();
		return SUCCESS;
	}

}
