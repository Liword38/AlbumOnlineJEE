/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.PhotoFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicPhoto;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Martin
 */
@Named
@RequestScoped
public class DeletePhoto implements Serializable {

    private SempicPhoto target;

    @Inject
    private PhotoFacade photoDao;

    public DeletePhoto() {

    }

    @PostConstruct
    public void init() {
        target = new SempicPhoto();
    }

    public SempicPhoto getTarget() {
        return target;
    }

    public void setTarget(SempicPhoto target) {
        this.target = target;
    }

    public String deletePhoto(long photoId) {
        System.out.println("On essaie de supprimer une photo  d'id" + photoId);

        target = photoDao.read(photoId);
        
        try {
            photoDao.delete(target);
        } catch (SempicModelException ex) {
            Logger.getLogger(DeletePhoto.class.getName()).log(Level.SEVERE, null, ex);
            return "failure"; 
        }
        return "success?faces-redirect=true&includeViewparams=true&albumId=" + target.getInAlbum().getId().toString();
    }
}
