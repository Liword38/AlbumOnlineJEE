/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.SempicUserFacade;
import fr.uga.miashs.sempic.entities.SempicUser;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Martin
 */
@Named
@ViewScoped
public class DeleteUser implements Serializable {
    
    private SempicUser target;
    
    @Inject
    private SempicUserFacade userDao;
    
    public DeleteUser() {
        
    }
    
    @PostConstruct
    public void init() {
        target = new SempicUser();
    }
    
     public SempicUser getTarget() {
        return target;
    }

    public void setTarget(SempicUser target) {
        this.target = target;
    }
    
    public String delete(long userId) {
        try {
            userDao.deleteById(userId);
        }
        catch (SempicModelException ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return "failure";
        }
        
        return "success";
    }
    

}
