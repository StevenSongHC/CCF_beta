package com.ccf.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ccf.bean.College;
import com.ccf.dao.CollegeDAO;

public class CollegeDAOImpl extends HibernateDaoSupport implements CollegeDAO {

	@SuppressWarnings("unchecked")
	public List<College> list() {
		return (List<College>)this.getHibernateTemplate().find("from College college order by college.id asc");
	}

	@Override
	public void add(College college) {
		this.getHibernateTemplate().save(college);
	}

	@Override
	public College fetchCollege(String index) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		String hql = "";
		if (index.equals("first")) {
			hql = "from College co where co.id=1";		// start with 1
		}
		else if (index.equals("last")) {
			hql = "from College co where co.id=(select max(c.id) from College c)";
		}
		else {
			return null;
		}
		Query query = session.createQuery(hql);
		session.getTransaction().commit();
		return (College) query.uniqueResult();
	}

	@Override
	public College getCollegeByCoid(int coid) {
		return (College)this.getHibernateTemplate().get(College.class, coid);
	}

	@Override
	public void update(College college) {
		this.getHibernateTemplate().update(college);
	}
	
}
