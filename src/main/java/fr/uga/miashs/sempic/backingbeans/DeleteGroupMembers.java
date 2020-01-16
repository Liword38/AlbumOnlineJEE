/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.dao.SempicUserFacade;
import fr.uga.miashs.sempic.entities.SempicGroup;
import fr.uga.miashs.sempic.entities.SempicUser;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mercadieremeline
 */
@Named
@RequestScoped
public class DeleteGroupMembers {
    
    private SempicGroup target;
    
    @Inject
    private GroupFacade groupDao;
    
    @Inject
    private SempicUserFacade memberDao;
    
    
    public DeleteGroupMembers() {
        
    }
    
    @PostConstruct
    public void init() {
        target = new SempicGroup();
    }
    
    public SempicGroup getTarget() {
        return target;
    }
    
    public void setTarget(SempicGroup target) {
        this.target = target;
    }
    
    
    public void setOwnerId(String id) {
        System.out.println(id);
        target.setOwner(memberDao.read(Long.valueOf(id)));
    }

    public String getOwnerId() {
        if(target.getOwner() == null) {
            return "-1";
        }
        return "" + target.getOwner().getId();
    }

    
    public String delete(long memberId) {
        System.out.println("ID membre = " + memberId);
        try {
            memberDao.deleteById(memberId);
        }
        catch (SempicModelException ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return "failure";
        }
        
        return "success";
    }
   
}
