package com.ccf.dao;

import java.util.List;

import com.ccf.bean.Province;

public interface ProvinceDAO {
	
	public List<Province> list();
	
	public void add(Province province);
	
	public Province fetchProvince(String index);		// index = first || last, only accept string index
	
	public Province getProvinceByPrid(int prid);
	
	public void update(Province province);
	
	public void plusCityAmount(int prid, int amount);
	
}
