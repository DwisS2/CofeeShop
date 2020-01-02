/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.BookingDAO;  
import com.model.pojo.Booking;  
import java.io.Serializable;  
import org.primefaces.event.RowEditEvent;  
import javax.faces.context.FacesContext;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext; 


/**
 *
 * @author FACULTY
 */
@ManagedBean
@ViewScoped
  
public class BookingBean implements Serializable  
{  
    private List < Booking > usersList;  
    private List < Booking > searchList;  
    private List < Booking > searchByMejaList;  
    BookingDAO userDao = new BookingDAO();  
    Booking user = new Booking();  
    Booking newuser = new Booking();  
    public List < Booking > getUsers()
    {  
        usersList = userDao.AllBookings();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addBooking()  
    {  
        String Nama = newuser.getNama();  
        Integer IdBooking = 0;  
        IdBooking = userDao.getIdBooking();  
        newuser.setIdBooking(IdBooking);  
        String Id = Integer.toString(newuser.getIdBooking());    
        userDao.add(newuser);  
        System.out.println("Events successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Booking();  
    }  
    public void changeUser(Booking user)  
    {  
        this.user = user;  
    }  
    public void UpdateUser(Booking user)  
    {  
        String Nama = user.getNama();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nama", Nama);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Booking();  
    }  
    public void deleteUser(Booking user)  
    {  
        String Booking = user.getNama();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyMeja()  
    {  
        searchByMejaList = userDao.SearchByMeja(user.getNama());  
        int count = searchByMejaList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Booking getUser()  
    {  
        return user;  
    }  
    public void setUser(Booking user)  
    {  
        this.user = user;  
    }  
    public Booking getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Booking newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Booking > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Booking > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Booking > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Booking > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Booking > getSearchByMejaList()  
    {  
        return searchByMejaList;  
    }  
    public void setSearchByMejaList(List < Booking > searchByEmailList)  
    {  
        this.searchByMejaList = searchByMejaList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Booking) event.getObject()).getNama());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Booking editeduser = (Booking) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Booking) event.getObject());  
    }  
} 
