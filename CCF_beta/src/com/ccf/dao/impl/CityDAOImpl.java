package com.ccf.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ccf.bean.City;
import com.ccf.dao.CityDAO;

public class CityDAOImpl extends HibernateDaoSupport implements CityDAO {

	public void runSql(String sql) {
		System.out.println("sql check:" + sql);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<City> list() {
		return (List<City>)this.getHibernateTemplate().find("from City city order by city.id asc");	
	}

	public void add(City city) {
		this.getHibernateTemplate().save(city);
	}

	public City fetchCity(String index) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		String hql = "";
		if (index.equals("first")) {
			hql = "from City ct where ct.id=1";		// start with 1
		}
		else if (index.equals("last")) {
			hql = "from City ct where ct.id=(select max(c.id) from City c)";
		}
		else {
			return null;
		}
		Query query = session.createQuery(hql);
		session.getTransaction().commit();
		return (City) query.uniqueResult();
	}

	public City getCityByCtid(int ctid) {
		return (City)this.getHibernateTemplate().get(City.class, ctid);
	}

	public void update(City city) {
		String hql = "update City city set city.name='" + city.getName() + "',city.cnName='" + city.getCnName() + "',city.collegeAmount='" + 
						city.getCollegeAmount() + "',city.clubAmount=" + city.getClubAmount() + ",city.prid=" + city.getPrid() +  
						" where city.id=" + city.getId();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();  
		session.beginTransaction();  
		session.createQuery(hql).executeUpdate(); 
		session.getTransaction().commit();
	}

	// update ct_college_amount
	public void plusCollegeAmount(int ctid, int amount) {
		runSql("UPDATE city SET ct_college_amount=ct_college_amount+" + amount + " WHERE ctid=" + ctid);
	}

	// update ct_club_amount
	public void plusClubAmount(int ctid, int amount) {
		runSql("UPDATE city SET ct_club_amount=ct_club_amount+" + amount + " WHERE ctid=" + ctid);
	}

}
