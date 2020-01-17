/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.dao.PhotoFacade;
import fr.uga.miashs.sempic.dao.PhotoStorage;
import fr.uga.miashs.sempic.dao.SempicUserFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicGroup;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class ShareAlbum {
    
    private SempicAlbum target;

    @Inject
    private PhotoFacade photoDao;
    
    @Inject
    private AlbumFacade albumDao;
    @Inject
    private SempicUserFacade userDao;
    
    @Inject
    private GroupFacade groupDao;

    
    public ShareAlbum() {

    }
    
    @PostConstruct
    public void init() {
        target = new SempicAlbum();
    }
    
    public void setOwnerId(String id) {
        System.out.println(id);
        target.setAlbumOwner(userDao.read(Long.valueOf(id)));
    }

    public String getOwnerId() {
        if (target.getAlbumOwner() == null) {
            return "-1";
        }
        return "" + target.getAlbumOwner().getId();
    }

    public SempicAlbum getTarget() {
        return target;
    }

    public void setTarget(SempicAlbum current) {
        this.target = current;
    }
    
    public List<SempicGroup> getGroups() {
        return groupDao.findAll();
    }
    
    
    
    
    public void share() {
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String groups = params.get("groups");
        String albumId = params.get("albumId");
        
        Set<SempicGroup> partage = target.getSharedWith();
        partage.forEach((e) -> groupDao.addAlbum(e.getId(), Long.parseLong(albumId)));
        


        
    }
}
