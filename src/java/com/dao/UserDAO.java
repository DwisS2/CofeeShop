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
import com.model.pojo.User;  
import javax.faces.application.FacesMessage;  
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;  



public class UserDAO  
{  
    private User user;  
    private User newuser;  
    private List < User > DaoAllUsers;  
    private List < User > DaoSearchUserList;  
    //Session session;  
    public List < User > AllUsers()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllUsers = session.createCriteria(User.class).list();  
            int count = DaoAllUsers.size();  
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
        return DaoAllUsers;  
    }  
    public Integer getUserId()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from User U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer userId = 1;  
        if (results.get(0) != null)  
        {  
            userId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return userId;  
    }  
    public List < User > SearchByTelpon(String Telpon)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < User > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From User U where U.Telpon =:Telpon"); //User is the entity not the table  
            qu.setParameter("telpon", Telpon);  
            daoSearchList = qu.list();  
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
    public void add(User newuser)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String UserId = Integer.toString(newuser.getUserId());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newuser);  
            session.flush();  
            System.out.println("NewUser saved, id: " +  
                newuser.getUserId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(User user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String nama = user.getNama();  
            session.beginTransaction();  
            session.delete(user);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(User user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(user);  
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
    
    public boolean validateLogin(User user){
        Transaction transObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        List<User> listuser = new ArrayList<User>();
        try{
            transObj = sessionObj.beginTransaction();
            Query query = sessionObj.createQuery("from User where  nama= :nama and password = :password");
            query.setParameter("nama", user.getNama());
            query.setParameter("password", user.getPassword());
            listuser = query.list();
            
            transObj.commit();
            System.out.println("Sukses");
        }catch(Exception ex){
            ex.printStackTrace();
            transObj.rollback();
        }finally{
            sessionObj.flush();
            sessionObj.close();
        }
        if(listuser.size()>0){
            return true;
        }else{
            return false;
        }
    }
    
} 
