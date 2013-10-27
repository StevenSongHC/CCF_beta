package com.ccf.action.province;

import net.sf.json.JSONObject;

import com.ccf.bean.Province;
import com.ccf.service.ProvinceService;
import com.opensymphony.xwork2.ActionSupport;

public class AddAndFetchLastDataAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8864479556122202521L;
	private ProvinceService prService;
	private int id;
	private String name;
	private String cnName;
	private String cnShortName;
	private String capital;
	private int cityAmount;
	private int brightness;
	private String data;
	public ProvinceService getPrService() {
		return prService;
	}
	public void setPrService(ProvinceService prService) {
		this.prService = prService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCnShortName() {
		return cnShortName;
	}
	public void setCnShortName(String cnShortName) {
		this.cnShortName = cnShortName;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public int getCityAmount() {
		return cityAmount;
	}
	public void setCityAmount(int cityAmount) {
		this.cityAmount = cityAmount;
	}
	public int getBrightness() {
		return brightness;
	}
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String execute() throws Exception {
		System.out.println(name+cnName+cnShortName+capital+cityAmount+brightness);
		// save the province
		Province province = new Province();
		province.setName(name);
		province.setCnName(cnName);
		province.setCnShortName(cnShortName);
		province.setCapital(capital);
		province.setCityAmount(cityAmount);
		province.setBrightness(brightness);
		prService.add(province);
		// then return this province data
		data = JSONObject.fromObject(prService.getLastProvince()).toString();
		return SUCCESS;
	}

}
