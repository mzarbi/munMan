package com.nogroup.municipality.manager.data.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nogroup.municipality.manager.data.entities.Sponsor;
import com.nogroup.municipality.manager.data.utils.HUtils;
import com.nogroup.municipality.manager.data.utils.Logger;

public class SponsorD {
    
	private static final long serialVersionUID = 1L;

	public Integer create(Sponsor e) {
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

	public List<Sponsor> read() {
		/*
		 * Select all Category records from the database.
		 */
		List<Sponsor> tmps = new ArrayList<Sponsor>();
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   tmps = session.createQuery("FROM Sponsor").list();

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
			        "select count(*) from Sponsor");
		   count = (Long)query.uniqueResult();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Sponsor> Found " + count);
		return count;
	}


	public void delete(Integer id) {
		/*
		 * Delete Category record by id.
		 */
		Sponsor e = findByID(id);

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
		Logger.print("<Sponsor>Successfully deleted " + e.toString());
	}

	public Sponsor findByID(Integer id) {
		/*
		 * Find Category by id.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   Sponsor e = (Sponsor) session.load(Sponsor.class, id);

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
		   Query query = session.createQuery("DELETE FROM Sponsor ");
		   query.executeUpdate();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Sponsor>Successfully deleted all records.");
	}

	public void hard_update(Sponsor e) {
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
		Logger.print("<Sponsor>Successfully updated " + e.toString());
	}

	public void delete(Sponsor e) {
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
		Logger.print("<Sponsor>Successfully deleted " + e.toString());
	}
}
