package com.ccf.action.province;

import net.sf.json.JSONObject;

import com.ccf.bean.Province;
import com.ccf.service.ProvinceService;
import com.ccf.util.FileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateProvinceAction extends ActionSupport {
	/*
	 * update province data, then return it's json data 
	 */
	private static final long serialVersionUID = 4625666498834260618L;
	
	private ProvinceService prService;
	private String result;
	private int prid;
	private String newName;
	private String newCnName;
	private String newCnShortName;
	private String newCapital;
	private int newCityAmount;
	private int newBrightness;
	
	public ProvinceService getPrService() {
		return prService;
	}
	public void setPrService(ProvinceService prService) {
		this.prService = prService;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getPrid() {
		return prid;
	}
	public void setPrid(int prid) {
		this.prid = prid;
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
	public String getNewCnShortName() {
		return newCnShortName;
	}
	public void setNewCnShortName(String newCnShortName) {
		this.newCnShortName = newCnShortName;
	}
	public String getNewCapital() {
		return newCapital;
	}
	public void setNewCapital(String newCapital) {
		this.newCapital = newCapital;
	}
	public int getNewCityAmount() {
		return newCityAmount;
	}
	public void setNewCityAmount(int newCityAmount) {
		this.newCityAmount = newCityAmount;
	}
	public int getNewBrightness() {
		return newBrightness;
	}
	public void setNewBrightness(int newBrightness) {
		this.newBrightness = newBrightness;
	}
	
	public String execute() throws Exception {
		Province province = new Province();
		province.setId(prid);
		province.setName(newName);
		province.setCnName(newCnName);
		province.setCnShortName(newCnShortName);
		province.setCapital(newCapital);
		province.setCityAmount(newCityAmount);
		province.setBrightness(newBrightness);
		// get the old name of this province
		String oldName = prService.getProvince(prid).getName();
		// update province folder name
		new FileUtil().renameFolder("archive-activities", oldName, newName);
		// then update database
		prService.update(province);
		
		result = JSONObject.fromObject(prService.getProvince(province.getId())).toString();
		return SUCCESS;
	}

}
