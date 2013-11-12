package com.ccf.action.college;

import net.sf.json.JSONObject;

import com.ccf.bean.City;
import com.ccf.bean.College;
import com.ccf.bean.Province;
import com.ccf.service.CityService;
import com.ccf.service.CollegeService;
import com.ccf.service.ProvinceService;
import com.opensymphony.xwork2.ActionSupport;

public class AddAndFetchLastDataAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8861737538898924191L;
	private ProvinceService prService;
	private CityService ctService;
	private CollegeService coService;
	private String name;
	private String cnName;
	private String fullName;
	private String summary;
	private int clubAmount;
	private int prid;
	private int ctid;
	private String data;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getClubAmount() {
		return clubAmount;
	}
	public void setClubAmount(int clubAmount) {
		this.clubAmount = clubAmount;
	}
	public int getPrid() {
		return prid;
	}
	public void setPrid(int prid) {
		this.prid = prid;
	}
	public int getCtid() {
		return ctid;
	}
	public void setCtid(int ctid) {
		this.ctid = ctid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String execute() throws Exception {
		System.out.println(name+cnName+fullName+summary+clubAmount+prid+ctid);
		College college = new College();
		college.setName(name);
		college.setCnName(cnName);
		college.setFullName(fullName);
		college.setSummary(summary);
		college.setClubAmount(clubAmount);
		college.setPrid(prid);
		college.setCtid(ctid);
		coService.add(college);
		
		// create an archive folder
		Province province = prService.getProvince(prid);
		City city = ctService.getCity(ctid);
		if (province != null && city != null) {
			new com.ccf.util.FileUtil().createCollegeFolder(province.getName(), city.getName(), name);
		}
		else
			System.out.println("prid or ctid is invalid!");
		
		// city.collegeAmount +1
		ctService.plusCollegeAmount(ctid, 1);
		
		// return data
		data = JSONObject.fromObject(coService.getLastCollege()).toString();
		
		return SUCCESS;
	}

}
