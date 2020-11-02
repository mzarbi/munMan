package com.nogroup.municipality.manager.data.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nogroup.municipality.manager.data.entities.Trajectory;
import com.nogroup.municipality.manager.data.utils.HUtils;
import com.nogroup.municipality.manager.data.utils.Logger;

public class TrajectoryD{
    /**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;

	public Integer create(Trajectory e) {
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

	public List<Trajectory> read() {
		/*
		 * Select all Category records from the database.
		 */
		List<Trajectory> tmps = new ArrayList<Trajectory>();
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   tmps = session.createQuery("FROM Trajectory").list();

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
			        "select count(*) from Trajectory");
		   count = (Long)query.uniqueResult();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Trajectory> Found " + count);
		return count;
	}


	public void delete(Integer id) {
		/*
		 * Delete Category record by id.
		 */
		Trajectory e = findByID(id);

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
		Logger.print("<Trajectory>Successfully deleted " + e.toString());
	}

	public Trajectory findByID(Integer id) {
		/*
		 * Find Category by id.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   Trajectory e = (Trajectory) session.load(Trajectory.class, id);

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
		   Query query = session.createQuery("DELETE FROM Trajectory ");
		   query.executeUpdate();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<Trajectory>Successfully deleted all records.");
	}

	public void hard_update(Trajectory e) {
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
		Logger.print("<Trajectory>Successfully updated " + e.toString());
	}

	public void delete(Trajectory e) {
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
		Logger.print("<Trajectory>Successfully deleted " + e.toString());
	}
}