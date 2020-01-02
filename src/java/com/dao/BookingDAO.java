/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import com.util.HibernateUtil;  
import com.model.pojo.Booking;  
import java.io.Serializable;
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class BookingDAO
{  
    private Booking booking;  
    private Booking newbooking;  
    private List < Booking > DaoAllBookings;  
    
    //Session session;  
    public List < Booking > AllBookings()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllBookings = session.createCriteria(Booking.class).list();  
            int count = DaoAllBookings.size();  
            // FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "List Size", Integer.toString(count));//Debugging Purpose  
            //RequestContext.getCurrentInstance().showMessageInDialog(message1);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllBookings;  
    }  
    public Integer getIdBooking()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idBooking) from Booking U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdBooking = 1;  
        if (results.get(0) != null)  
        {  
            IdBooking = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdBooking;  
    }  
    public List < Booking > SearchByBookings(String Booking)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Booking > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Booking U where U.nama =:nama"); //User is the entity not the table  
            qu.setParameter("Booking",Booking );  
            daoSearchList = qu.list();  
            int count = daoSearchList.size();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        finally  
        {  
            session.close();  
        }  
        return daoSearchList;  
    }  
    public void add(Booking newbooking)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newbooking.getIdBooking());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newbooking);  
            session.flush();  
            System.out.println("Booking saved, id: " +  
                newbooking.getIdBooking());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Booking booking)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Booking = booking.getNama();  
            session.beginTransaction();  
            session.delete(booking);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Booking booking)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(booking);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  

    public List<Booking> SearchByMeja(String nama) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
} 
