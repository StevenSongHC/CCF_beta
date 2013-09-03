package com.ccf.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ccf.bean.User;
import com.ccf.dao.StevDAO;

public class StevDAOImpl extends HibernateDaoSupport implements StevDAO {
	
	private static final String STEVS_ACCOUNT = "654826249";  
	
	public boolean isStev(User user) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		String hql = "From User stev where stev.u_account=" + STEVS_ACCOUNT;
		System.out.println("hql check:" + hql);	//For debug using
		Query query = session.createQuery(hql);  
		session.getTransaction().commit();
		User stev = (User) query.uniqueResult();
		if(user.getU_password().equals(stev.getU_password()) && user.getU_code().equals(stev.getU_code())) 
			return true;
		/*List<Object[]> obj = query.list();
		for(Object[] o : obj) {
			if(user.getU_password().equals(o[0]) && user.getU_code().equals(o[1])) 
				return true;
		}*/
		return false;
	}

	public void addUser(User user) {
		this.getHibernateTemplate().save(user);
	}
	
	public void updateLeaderCode(int cid, String newLeaderCode) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();  
		session.beginTransaction();  
		String hql = "update Club club set club.c_code_current_leader='" + newLeaderCode 
				+ "' where cid=" + cid;
		System.out.println("hql check:" + hql);	//For debug using
		Query query = session.createQuery(hql);  
		query.executeUpdate();  
		session.getTransaction().commit();
	}

	@Override
	public void memberModified(int cid, String c_members,
			String c_code_edit_authority_members, String c_code_current_leader) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();  
		session.beginTransaction();  
		String hql = "update Club club set club.c_members='" + c_members + "',club.c_code_edit_authority_members='"
						+ c_code_edit_authority_members + "',club.c_code_current_leader='" + c_code_current_leader
						+ "' where cid=" + cid;
		System.out.println("hql check:" + hql);	//For debug using
		Query query = session.createQuery(hql);  
		query.executeUpdate();  
		session.getTransaction().commit();
	}
	
}
