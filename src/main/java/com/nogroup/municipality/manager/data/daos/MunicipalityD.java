package com.nogroup.municipality.manager.data.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nogroup.municipality.manager.data.entities.Municipality;
import com.nogroup.municipality.manager.data.utils.HUtils;
import com.nogroup.municipality.manager.data.utils.Logger;

public class MunicipalityD{
    /**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;

	public Integer create(Municipality e) {
	    /*
		 * Insert new Category record in the database.
		 */
		e.setdCreated(new Date());
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

	public List<Municipality> read() {
		/*
		 * Select all Category records from the database.
		 */
		List<Municipality> tmps = new ArrayList<Municipality>();
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   tmps = session.createQuery("FROM Municipality").list();

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
			        "select count(*) from Municipality");
		   count = (Long)query.uniqueResult();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Municipality> Found " + count);
		return count;
	}


	public void delete(Integer id) {
		/*
		 * Delete Category record by id.
		 */
		Municipality e = findByID(id);

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
		Logger.print("<Municipality>Successfully deleted " + e.toString());
	}

	public Municipality findByID(Integer id) {
		/*
		 * Find Category by id.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   Municipality e = (Municipality) session.load(Municipality.class, id);

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
		   Query query = session.createQuery("DELETE FROM Municipality ");
		   query.executeUpdate();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Municipality>Successfully deleted all records.");
	}

	public void hard_update(Municipality e) {
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
		Logger.print("<Municipality>Successfully updated " + e.toString());
	}

	public void delete(Municipality e) {
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
		Logger.print("<Municipality>Successfully deleted " + e.toString());
	}
}