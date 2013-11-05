package com.ccf.service;

import java.util.List;

import com.ccf.bean.City;

public interface CityService {
	
	public List<City> list();

	public void add(City city);
	
	public City getLastCity();
	
	public City getCity(int ctid);
	
	public void update(City city);
}
