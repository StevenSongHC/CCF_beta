package com.ccf.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ccf.bean.Club;
import com.ccf.dao.ClubDAO;

public class ClubDAOImpl extends HibernateDaoSupport implements ClubDAO {

	public void runSql(String sql) {
		System.out.println("sql check:" + sql);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> querySql(String sql) {
		System.out.println("sql check:" + sql);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		//.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP), then use map.get(String) to get the values
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		/*SQLQuery query = session.createSQLQuery(sql);*/
		session.getTransaction().commit();
		return (List<Object[]>) query.list();
	}
	
	public Object executeSql(String sql) {
		System.out.println("sql check:" + sql);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();  
		session.beginTransaction();
		Query query = session.createSQLQuery(sql);  
		session.getTransaction().commit();
		return (Object) query.uniqueResult();
	}
	
	@Override
	public void addClub(Club club) {
		this.getHibernateTemplate().save(club);
		// needs create a new data in join_club_apply table
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Club> showAllClubs() {
		String hql = "from Club club order by club.cid desc";
		return (List<Club>)this.getHibernateTemplate().find(hql);
	}
	
	public Club findClubById (int cid){
		return (Club)this.getHibernateTemplate().get(Club.class, cid);
	}

	public void updateClubBasicInfo(Club club) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();  
		session.beginTransaction();  
		String hql = "update Club club set club.c_pic='" + club.getC_pic()
				+ "',club.c_name='" + club.getC_name() + "',club.c_intro='" 
				+ club.getC_intro() + "',club.c_field='" + club.getC_field()
				+ "' where cid=" + club.getCid();
		//'符号导致数据更新失败
		System.out.println("hql check:" + hql);	//For debug using
		Query query = session.createQuery(hql);  
		query.executeUpdate();  
		session.getTransaction().commit();
		
		/*this.getHibernateTemplate().save(club);*/
	}
	
	public Map<String, Object> clubDetails(int cid) {
		Map <String, Object> info = new HashMap <String, Object>();
		String sql = "SELECT * FROM club WHERE cid=" + cid;
		System.out.println("clubDaoImpl:" + executeSql(sql).toString());
		Map<?, ?> map = (Map<?, ?>) (Object) querySql(sql).get(0);
		if (map != null) {
			info.put("cid", Integer.parseInt(map.get("cid").toString()));
			info.put("name", map.get("c_name").toString());
			info.put("pic", map.get("c_pic").toString());
			info.put("url", map.get("c_homepage").toString());
			info.put("member", map.get("c_members").toString());
			info.put("publisher", map.get("c_code_edit_authority_members").toString());
			info.put("leader", map.get("c_code_current_leader").toString());
			info.put("intro", map.get("c_intro").toString());
			info.put("activity", map.get("c_activities").toString());
			info.put("file", map.get("c_shared_files").toString());
			info.put("xp", map.get("c_xp").toString());
			info.put("province", map.get("c_province").toString());
			info.put("city", map.get("c_city").toString());
			info.put("college", map.get("c_college").toString());
			info.put("tag", map.get("c_field").toString());
		}
		return info;
	}
	
}
