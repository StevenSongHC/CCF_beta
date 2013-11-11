package com.ccf.action.college;

import net.sf.json.JSONObject;

import com.ccf.bean.City;
import com.ccf.bean.College;
import com.ccf.bean.Province;
import com.ccf.service.CityService;
import com.ccf.service.CollegeService;
import com.ccf.service.ProvinceService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCollegeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7619549372208625950L;
	private ProvinceService prService;
	private CityService ctService;
	private CollegeService coService;
	private String result;
	private int coid;
	private String newName;
	private String newCnName;
	private String newFullName;
	private String newSummary;
	private int newClubAmount;
	private int newPrid;
	private int newCtid;
	private int oldPrid;
	private int oldCtid;
	public ProvinceService getPrService() {
		return prService;
	}
	public void setPrService(ProvinceService prService) {
		this.prService = prService;
	}
	public CityService getCtService() {
		return ctService;
	}
	public void setCtService(CityService ctService) {
		this.ctService = ctService;
	}
	public CollegeService getCoService() {
		return coService;
	}
	public void setCoService(CollegeService coService) {
		this.coService = coService;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCoid() {
		return coid;
	}
	public void setCoid(int coid) {
		this.coid = coid;
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
	public String getNewFullName() {
		return newFullName;
	}
	public void setNewFullName(String newFullName) {
		this.newFullName = newFullName;
	}
	public String getNewSummary() {
		return newSummary;
	}
	public void setNewSummary(String newSummary) {
		this.newSummary = newSummary;
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
	public int getNewCtid() {
		return newCtid;
	}
	public void setNewCtid(int newCtid) {
		this.newCtid = newCtid;
	}
	public int getOldPrid() {
		return oldPrid;
	}
	public void setOldPrid(int oldPrid) {
		this.oldPrid = oldPrid;
	}
	public int getOldCtid() {
		return oldCtid;
	}
	public void setOldCtid(int oldCtid) {
		this.oldCtid = oldCtid;
	}
	
	public String execute() throws Exception {
		College newCollege = new College();
		newCollege.setId(coid);
		newCollege.setName(newName);
		newCollege.setCnName(newCnName);
		newCollege.setFullName(newFullName);
		newCollege.setSummary(newSummary);
		newCollege.setClubAmount(newClubAmount);
		newCollege.setPrid(newPrid);
		newCollege.setCtid(newCtid);
		
		Province newProvince = prService.getProvince(newPrid);
		City newCity = ctService.getCity(newCtid);
		Province oldProvince = prService.getProvince(oldPrid);
		City oldCity = ctService.getCity(oldCtid);
		// if the new property is valid, then update folder's name
		if (newProvince != null && newCity != null) {
			// previous folder did not existed, good, just create a new one
			if (oldProvince == null || oldCity == null) {
				new com.ccf.util.FileUtil().createCollegeFolder(newProvince.getName(), newCity.getName(), newName);
			}
			// rename
			else {
				new com.ccf.util.FileUtil().renameCollegeFolder(oldProvince.getName(), oldCity.getName(), coService.getCollege(coid).getName(), 
															newProvince.getName(), newCity.getName(), newName);
			}
			// update database
			coService.update(newCollege);
		}
		// or just do nothing
		result = JSONObject.fromObject(coService.getCollege(coid)).toString();
		
		return SUCCESS;
	}

}
