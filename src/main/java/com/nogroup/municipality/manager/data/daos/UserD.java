package com.nogroup.municipality.manager.data.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nogroup.municipality.manager.business.PasswordUtils;
import com.nogroup.municipality.manager.data.entities.User;
import com.nogroup.municipality.manager.data.utils.HUtils;
import com.nogroup.municipality.manager.data.utils.Logger;
import com.vaadin.ui.PasswordField;

public class UserD{
    /**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;

	public Integer create(User e) {
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

	public List<User> read() {
		/*
		 * Select all Category records from the database.
		 */
		List<User> tmps = new ArrayList<User>();
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   tmps = session.createQuery("FROM User").list();

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
			        "select count(*) from User");
		   count = (Long)query.uniqueResult();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<User> Found " + count);
		return count;
	}


	public void delete(Integer id) {
		/*
		 * Delete Category record by id.
		 */
		User e = findByID(id);

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
		Logger.print("<User>Successfully deleted " + e.toString());
	}

	public User findByID(Integer id) {
		/*
		 * Find Category by id.
		 */
		Session session = HUtils.getSessionFactory().getCurrentSession() ;
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   User e = (User) session.load(User.class, id);

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
		   Query query = session.createQuery("DELETE FROM User ");
		   query.executeUpdate();

		   tx.commit();
		}
		catch (Exception ex) {
		   if (tx!=null) tx.rollback();
		   ex.printStackTrace();
		}
		Logger.print("<User>Successfully deleted all records.");
	}

	public void hard_update(User e) {
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
		Logger.print("<User>Successfully updated " + e.toString());
	}

	public void delete(User e) {
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
		Logger.print("<User>Successfully deleted " + e.toString());
	}

	public User authenticate(String usrnm, String pwd) {
		/*
		 * authenticate.
		 */
		for(User tmp : read()) {
			String salt = tmp.getSalt() ;
			String pss = new String(PasswordUtils.hash(pwd.toCharArray(), salt.getBytes())) ;
			if(pss.equalsIgnoreCase(tmp.getPassword())) {
				return tmp;
			}
		}
		return null;
	}

	public User getUserByEmail(String email) {
		for(User tmp : read()) {
			if(tmp.getEmail().equals(email)) {
				return tmp ;
			}
		}
		return null ;
	}
}
