package com.nogroup.municipality.manager.data.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nogroup.municipality.manager.data.entities.Agent;
import com.nogroup.municipality.manager.data.utils.HUtils;
import com.nogroup.municipality.manager.data.utils.Logger;

public class AgentD {
    
	private static final long serialVersionUID = 1L;

	public Integer create(Agent e) {
	    /*
		 * Insert new Category record in the database.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(e);

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Category> Successfully created " + e.toString());
		return e.getId();
	}

	public List<Agent> read() {
		/*
		 * Select all Category records from the database.
		 */
		List<Agent> tmps = new ArrayList<Agent>();
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   tmps = session.createQuery("FROM Agent").list();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Category> Found " + tmps.size());
		return tmps;
	}

	public Long count() {
	    /*
		 * Return Category record count.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		Long count = (long) 0 ;
		try {
		   tx = session.beginTransaction();
		   Query query = session.createQuery(
			        "select count(*) from Agent");
		   count = (Long)query.uniqueResult();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Agent> Found " + count);
		return count;
	}


	public void delete(Integer id) {
		/*
		 * Delete Category record by id.
		 */
		Agent e = findByID(id);

		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.delete(e);

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Agent>Successfully deleted " + e.toString());
	}

	public Agent findByID(Integer id) {
		/*
		 * Find Category by id.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   Agent e = (Agent) session.load(Agent.class, id);

		   tx.commit();
		   return e ;
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}

		return null;
	}

	public void deleteAll() {
		/*
		 * Purge all Category records.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   Query query = session.createQuery("DELETE FROM Agent ");
		   query.executeUpdate();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Agent>Successfully deleted all records.");
	}

	public void hard_update(Agent e) {
		/*
		 * Update Category record.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.update(e);

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Agent>Successfully updated " + e.toString());
	}

	public void delete(Agent e) {
		/*
		 * Delete Category record.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.delete(e);

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Agent>Successfully deleted " + e.toString());
	}
}