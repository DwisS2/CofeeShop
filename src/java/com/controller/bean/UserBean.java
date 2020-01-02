/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.UserDAO;  
import com.model.pojo.User;  
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
  
public class UserBean implements Serializable  
{  
    private List < User > usersList;  
    private List < User > searchList;  
    private List < User > searchByTelponList;  
    UserDAO userDao = new UserDAO();  
    User user = new User();  
    User newuser = new User();  
    public List < User > getUsers()  
    {  
        usersList = userDao.AllUsers();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addUser()  
    {  
        String Email = newuser.getEmail();  
        Integer userId = 0;  
        userId = userDao.getUserId();  
        newuser.setUserId(userId);  
        String UserId = Integer.toString(newuser.getUserId());  
        newuser.setTelpon(userId);
        String Telpon = Integer.toString(newuser.getTelpon());
        userDao.add(newuser);  
        System.out.println("User successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new User();  
    }  
    public void changeUser(User user)  
    {  
        this.user = user;  
    }  
    public void UpdateUser(User user)  
    {  
        String Nama = user.getNama();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nama", Nama);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new User();  
    }  
    public void deleteUser(User user)  
    {  
        String Nama = user.getNama();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public User getUser()  
    {  
        return user;  
    }  
    public void setUser(User user)  
    {  
        this.user = user;  
    }  
    public User getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(User newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < User > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < User > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < User > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < User > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < User > getSearchByTelponList()  
    {  
        return searchByTelponList;  
    }  
    public void setSearchByTelponList(List < User > searchByTelponList)  
    {  
        this.searchByTelponList = searchByTelponList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((User) event.getObject()).getNama());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        User editeduser = (User) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((User) event.getObject());  
    }  
} 
