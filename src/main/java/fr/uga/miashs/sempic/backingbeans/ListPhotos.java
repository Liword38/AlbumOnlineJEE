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
//            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//            System.out.println("Les params de la requête:" + params.toString());
//            String albumId = params.get("albumId");

            System.out.println("On essaie de lister les photos de l'album d'id" + albumId);
            dataModelByAlbum = new ListDataModel<>(photoDao.findByAlbum(Long.valueOf(albumId)));
        }
        return dataModelByAlbum;
    }

}
