/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.dao.PhotoFacade;
import fr.uga.miashs.sempic.entities.SempicPhoto;
import java.nio.file.Paths;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Martin
 */
@Named
@RequestScoped
public class ListPhotos {

    private DataModel<SempicPhoto> dataModel;
    private DataModel<SempicPhoto> dataModelByAlbum;
    private DataModel<SempicPhoto> dataModelBySharedWithUser;

    @Inject
    private PhotoFacade photoDao;

    public ListPhotos() {
    }

    public DataModel<SempicPhoto> getDataModel() {
        if (dataModel == null) {
            dataModel = new ListDataModel<>(photoDao.findAll());
        }
        return dataModel;
    }

    public DataModel<SempicPhoto> getDataModelByAlbum(String albumId) {
        if (dataModelByAlbum == null) {
            dataModelByAlbum = new ListDataModel<>(photoDao.findByAlbum(Long.valueOf(albumId)));
        }
        return dataModelByAlbum;
    }
    
    public DataModel<SempicPhoto> getDataModelBySharedWithUser(String userId) {
        if (dataModelBySharedWithUser == null) {
            dataModelBySharedWithUser = new ListDataModel<>(photoDao.findBySharedWithUser(Long.valueOf(userId)));
        }
        return dataModelBySharedWithUser;
    }

}
