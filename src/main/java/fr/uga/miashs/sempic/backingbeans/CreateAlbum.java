/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.dao.SempicUserFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicGroup;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author mercadieremeline
 */
@Named
@ViewScoped
public class CreateAlbum implements Serializable {
    
    private SempicAlbum current;
    private List<Part> photoFiles;
    
    @Inject
    private AlbumFacade albumDao;
    
    @Inject
    private SempicUserFacade userDao;
    
//    @Inject 
//    private GroupFacade groupDao;

    public CreateAlbum(){
        
    }
    
    @PostConstruct
    public void init() {   
        current=new SempicAlbum();
        
    }
    
    public void setOwnerId(String id) {
        System.out.println(id); 
        current.setAlbumOwner(userDao.read(Long.valueOf(id)));
    }
    
    public String getOwnerId() {
        if (current.getAlbumOwner()==null)
            return "-1";
        return ""+current.getAlbumOwner().getId();
    }
    
    public SempicAlbum getCurrent() {
        return current;
    }
    
    public void setCurrent(SempicAlbum current) {
        this.current = current;
    }
    
    public String create() {
        System.out.println(current);
        
        try {
            albumDao.create(current);
        } catch (SempicModelException ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return "failure";
        }
        
        return "success";
    }
    
    
    
}
