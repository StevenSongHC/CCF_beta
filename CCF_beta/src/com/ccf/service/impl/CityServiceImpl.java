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
}