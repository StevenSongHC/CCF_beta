package com.ccf.dao;

import java.util.List;

import com.ccf.bean.City;

public interface CityDAO {
	
	public List<City> list();
	
	public void add(City city);
	
	public City fetchCity(String index);		// index = first || last, only accept string index
	
	public City getCityByCtid(int ctid);
	
	public void update(City city);
}
