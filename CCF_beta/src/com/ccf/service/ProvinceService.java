package com.ccf.service;

import java.util.List;

import com.ccf.bean.Province;

public interface ProvinceService {

	public List<Province> list();
	
	public void add(Province province);
	
	public Province getLastProvince();
	
	public Province getProvince(int prid);
	
	public void update(Province province);
	
	public void plusCityAmount(int prid, int amount);
	
}
