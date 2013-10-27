package com.ccf.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ccf.bean.Province;
import com.ccf.dao.ProvinceDAO;

public class ProvinceDAOImpl extends HibernateDaoSupport implements ProvinceDAO {

	@SuppressWarnings("unchecked")
	public List<Province> list() {
		return (List<Province>)this.getHibernateTemplate().find("from Province province order by province.id asc");	
		//return (List<Province>)this.getHibernateTemplate().find("from Province province order by province.id desc");
	}
	
	public void add(Province province) {
		this.getHibernateTemplate().save(province);
	}
	
	public Province fetchProvince(String index) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		String hql = "";
		if (index.equals("first")) {
			hql = "from Province pr where pr.id=1";		// start with 1
		}
		else if (index.equals("last")) {
			hql = "from Province pr where pr.id=(select max(p.id) from Province p)";
		}
		else {
			return null;
		}
		Query query = session.createQuery(hql);
		session.getTransaction().commit();
		return (Province) query.uniqueResult();
	}
	
	public Province getProvinceByPrid(int prid) {
		return (Province)this.getHibernateTemplate().get(Province.class, prid);
	}
	
	public void update(Province province) {
		String hql = "update Province province set province.name='" + province.getName() + "',province.cnName='" + province.getCnName() + "',province.cnShortName='" + 
				province.getCnShortName() + "',province.capital='" + province.getCapital() + "',province.cityAmount=" + province.getCityAmount() + ",province.brightness=" + 
				province.getBrightness() + " where province.id=" + province.getId();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();  
		session.beginTransaction();  
		session.createQuery(hql).executeUpdate(); 
		session.getTransaction().commit();
	}

}
