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
public class EditAlbum implements Serializable {

    private SempicAlbum target;

    @Inject
    private AlbumFacade albumDao;
    @Inject
    private SempicUserFacade userDao;
    @Inject
    private GroupFacade groupDao;


    public EditAlbum() {

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

    public void setTarget(SempicAlbum target) {
        this.target = target;
    }

    public String update() {

        try {
            
            albumDao.update(target);

        } catch (SempicModelException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return "failure";

        }
        return "success";
    }

}
