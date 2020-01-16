/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicException;
import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.dao.PhotoFacade;
import fr.uga.miashs.sempic.dao.PhotoStorage;
import fr.uga.miashs.sempic.dao.SempicUserFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicGroup;
import fr.uga.miashs.sempic.entities.SempicPhoto;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private PhotoFacade photoDao;
    @Inject
    private AlbumFacade albumDao;
    @Inject
    private SempicUserFacade userDao;
    @Inject
    private GroupFacade groupDao;
    @Inject
    private PhotoStorage photoStorage;

    public CreateAlbum() {

    }

    //Crée l'entité SempicAlbum ainsi que les SempicPhotos associées
    @PostConstruct
    public void init() {
        current = new SempicAlbum();
    }

    //Ajoute à l'album séléctionné les photos ajoutées par l'user
//    public String addPhotos() {
//    for(Part p : photoFiles){
//            try {
//                SempicPhoto currentPhoto = new SempicPhoto();
//                String name = p.getSubmittedFileName();
//                currentPhoto.setName(name);
//                photoDao.create(currentPhoto);
//                try {
//                    photoStorage.savePicture(Paths.get(Long.toString(current.getId()), Long.toString(currentPhoto.getId())), p.getInputStream());
//                } catch (IOException | SempicException ex) {
//                    Logger.getLogger(CreateAlbum.class.getName()).log(Level.SEVERE, null, ex);
//                return "failure";
//                }
//            } catch (SempicModelException ex) {
//                Logger.getLogger(CreateAlbum.class.getName()).log(Level.SEVERE, null, ex);
//                return "failure";
//            }
//        }
//    return "succes";
//    }
    public void setOwnerId(String id) {
        System.out.println(id);
        current.setAlbumOwner(userDao.read(Long.valueOf(id)));
    }

    public String getOwnerId() {
        if (current.getAlbumOwner() == null) {
            return "-1";
        }
        return "" + current.getAlbumOwner().getId();
    }

    public SempicAlbum getCurrent() {
        return current;
    }

    public void setCurrent(SempicAlbum current) {
        this.current = current;
    }

    public List<Part> getPhotoFiles() {
        return photoFiles;
    }

    public void setPhotoFiles(List<Part> photoFiles) {
        this.photoFiles = photoFiles;
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

//    public String create() {
//        System.out.println(current);
//
//        for (Part p : photoFiles) {
//            try {
//                SempicPhoto currentPhoto = new SempicPhoto();
//                String name = p.getSubmittedFileName();
//                currentPhoto.setName(name);
//                photoDao.create(currentPhoto);
//                albumDao.create(current);
//                try {
//                    photoStorage.savePicture(Paths.get(Long.toString(current.getId()), Long.toString(currentPhoto.getId())), p.getInputStream());
//                } catch (IOException | SempicException ex) {
//                    Logger.getLogger(CreateAlbum.class.getName()).log(Level.SEVERE, null, ex);
//                    return "failure";
//                }
//            } catch (SempicModelException ex) {
//                Logger.getLogger(CreateAlbum.class.getName()).log(Level.SEVERE, null, ex);
//                return "failure";
//            }
//        }
//        return "succes";
//    }

}
