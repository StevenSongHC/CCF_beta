package com.ccf.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ccf.bean.City;
import com.ccf.dao.CityDAO;

public class CityDAOImpl extends HibernateDaoSupport implements CityDAO {

	@SuppressWarnings("unchecked")
	public List<City> list() {
		return (List<City>)this.getHibernateTemplate().find("from City city order by city.id asc");	
	}

}
