/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicGroup;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
public class DeleteGroup {
    
    private SempicGroup target;
    
    @Inject
    private GroupFacade groupDao;
    
    public DeleteGroup() {
        
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
    
    public String delete(long groupId) {
        System.out.println("suppression");
        try {
            groupDao.deleteById(groupId);
        }
        catch (SempicModelException ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return "failure";
        }
        
        return "success";
    }
    
}
