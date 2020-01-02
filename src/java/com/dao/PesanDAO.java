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
import com.model.pojo.Pesan;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class PesanDAO  
{  
    private Pesan pesan;  
    private Pesan newpesan;  
    private List < Pesan > DaoAllPesan;  
    private List < Pesan > DaoSearchPesanList;  
    //Session session;  
    public List < Pesan > AllPesans()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllPesan = session.createCriteria(Pesan.class).list();  
            int count = DaoAllPesan.size();  
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
        return DaoAllPesan;  
    }  
    public Integer getIdPesan()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idPesan) from Pesan U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdPesan = 1;  
        if (results.get(0) != null)  
        {  
            IdPesan = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdPesan;  
    }  
    public List < Pesan > SearchByNama(String Nama)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Pesan > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Pesan U where U.nama =:nama"); //User is the entity not the table  
            qu.setParameter("nama", Nama);  
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
    public void add(Pesan newpesan)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newpesan.getIdPesan());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newpesan);  
            session.flush();  
            System.out.println("NewPesan saved, id: " +  
                newpesan.getIdPesan());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Pesan pesan)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Booking = pesan.getNama();  
            session.beginTransaction();  
            session.delete(pesan);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Pesan pesan)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(pesan);  
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
} 
