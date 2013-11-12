package com.ccf.service.impl;

import java.util.List;

import com.ccf.bean.City;
import com.ccf.dao.CityDAO;
import com.ccf.service.CityService;

public class CityServiceImpl implements CityService {
	private CityDAO cityDao;

	public CityDAO getCityDao() {
		return cityDao;
	}

	public void setCityDao(CityDAO cityDao) {
		this.cityDao = cityDao;
	}

	public List<City> list() {
		return this.cityDao.list();
	}

	
	public void add(City city) {
		cityDao.add(city);
	}

	
	public City getLastCity() {
		return cityDao.fetchCity("last");
	}

	
	public City getCity(int ctid) {
		return this.cityDao.getCityByCtid(ctid);
	}

	
	public void update(City city) {
		cityDao.update(city);
	}
	
	public void plusCollegeAmount(int ctid, int amount) {
		cityDao.plusCollegeAmount(ctid, amount);
		
	}
	
	public void plusClubAmount(int ctid, int amount) {
		cityDao.plusClubAmount(ctid, amount);
	}
}
