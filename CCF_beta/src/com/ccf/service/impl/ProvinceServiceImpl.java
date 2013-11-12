package com.ccf.service.impl;

import java.util.List;

import com.ccf.bean.Province;
import com.ccf.dao.ProvinceDAO;
import com.ccf.service.ProvinceService;

public class ProvinceServiceImpl implements ProvinceService {

	private ProvinceDAO provinceDao;
	
	public ProvinceDAO getProvinceDao() {
		return provinceDao;
	}
	public void setProvinceDao(ProvinceDAO provinceDao) {
		this.provinceDao = provinceDao;
	}

	public List<Province> list() {
		return this.provinceDao.list();
	}
	
	public void add(Province province) {
		this.provinceDao.add(province);
	}
	
	public Province getLastProvince() {
		return this.provinceDao.fetchProvince("last");
	}
	
	public Province getProvince(int prid) {
		return provinceDao.getProvinceByPrid(prid);
	}
	
	public void update(Province province) {
		provinceDao.update(province);
	}
	
	public void plusCityAmount(int prid, int amount) {
		provinceDao.plusCityAmount(prid, amount);
	}

}
