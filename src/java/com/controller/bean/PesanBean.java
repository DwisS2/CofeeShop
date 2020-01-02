/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.PesanDAO;  
import com.model.pojo.Pesan;  
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
  
public class PesanBean implements Serializable  
{  
    private List < Pesan > usersList;  
    private List < Pesan > searchList;  
    private List < Pesan > searchByEmailList;  
    PesanDAO userDao = new PesanDAO();  
    Pesan user = new Pesan();  
    Pesan newuser = new Pesan();  
    public List < Pesan > getUsers()
    {  
        usersList = userDao.AllPesans();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addPesan()  
    {  
        String Nama = newuser.getNama();  
        Integer IdPesan = 0;  
        IdPesan = userDao.getIdPesan();  
        newuser.setIdPesan(IdPesan);  
        String Id = Integer.toString(newuser.getIdPesan());    
        userDao.add(newuser);  
        System.out.println("Events successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Pesan();  
    }  
    public void changeUser(Pesan user)  
    {  
        this.user = user;  
    }  
    public void UpdateUser(Pesan user)  
    {  
        String Nama = user.getNama();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "nama", Nama);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Pesan();  
    }  
    public void deleteUser(Pesan user)  
    {  
        String Pesan = user.getNama();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyEmail()  
    {  
        searchByEmailList = userDao.SearchByNama(user.getNama());  
        int count = searchByEmailList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Pesan getUser()  
    {  
        return user;  
    }  
    public void setUser(Pesan user)  
    {  
        this.user = user;  
    }  
    public Pesan getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Pesan newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Pesan > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Pesan > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Pesan > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Pesan > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Pesan > getSearchByEmailList()  
    {  
        return searchByEmailList;  
    }  
    public void setSearchByEmailList(List < Pesan > searchByEmailList)  
    {  
        this.searchByEmailList = searchByEmailList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Pesan) event.getObject()).getNama());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Pesan editeduser = (Pesan) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Pesan) event.getObject());  
    }  
} 
