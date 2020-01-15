/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
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
public class EditAlbum {
    
    private SempicAlbum target;
    
    @Inject
    private AlbumFacade albumDao;
    
    public EditAlbum() {
        
    }
    
    @PostConstruct
    public void init() {
        target = new SempicAlbum();
    }
 
    
    public SempicAlbum getTarget() {
        return target;
    }
    
    public void setTarget(SempicAlbum target) {
        this.target = target;
    }
    
    public String update() {
        
        try {
            albumDao.update(target);
        
        }
        catch (SempicModelException ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return "failure";
            
        }
        return "success";
    }
    
    
}
