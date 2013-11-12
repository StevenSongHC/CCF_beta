package com.ccf.action.city;

import net.sf.json.JSONObject;

import com.ccf.bean.City;
import com.ccf.service.CityService;
import com.ccf.service.ProvinceService;
import com.opensymphony.xwork2.ActionSupport;

public class AddAndFetchLastDataAction extends ActionSupport {

	/**
	 * add a city, then return its data in Ajax way
	 */
	private static final long serialVersionUID = 809718818679676622L;
	private CityService ctService;
	private ProvinceService prService;
	private String name;
	private String cnName;
	private int collegeAmount;
	private int clubAmount;
	private int prid;
	private String data;
	public CityService getCtService() {
		return ctService;
	}
	public void setCtService(CityService ctService) {
		this.ctService = ctService;
	}
	public ProvinceService getPrService() {
		return prService;
	}
	public void setPrService(ProvinceService prService) {
		this.prService = prService;
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
	public int getCollegeAmount() {
		return collegeAmount;
	}
	public void setCollegeAmount(int collegeAmount) {
		this.collegeAmount = collegeAmount;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String execute() throws Exception {
		System.out.println(name+cnName+collegeAmount+clubAmount+prid);
		// save the city
		City city = new City();
		city.setName(name);
		city.setCnName(cnName);
		city.setCollegeAmount(collegeAmount);
		city.setClubAmount(clubAmount);
		city.setPrid(prid);
		ctService.add(city);
		// province.cityAmount +1
		prService.plusCityAmount(prid, 1);
		
		// then return this city data
		data = JSONObject.fromObject(ctService.getLastCity()).toString();
		return SUCCESS;
	}
	

}
