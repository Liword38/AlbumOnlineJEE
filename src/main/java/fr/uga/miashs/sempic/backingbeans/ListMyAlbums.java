/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicGroup;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mercadieremeline
 */
@Named
@RequestScoped
public class ListMyAlbums {
    
    private DataModel<SempicAlbum> dataModel;
    
    @Inject
    private AlbumFacade albumDao;
    @Inject
    private SessionTools sessionTools;
    
    public DataModel<SempicAlbum> getDataModel() {
        long currentUserID = sessionTools.getConnectedUser().getId();
        
        if (dataModel == null) {
            System.out.println("appel à findAllByOwner()");
            dataModel = new ListDataModel<>(albumDao.findAllByOwner(currentUserID));
        }
        return dataModel;
    }
    
    
    /*
    public void saveAction() {
        
        for (SempicAlbum album : dataModel) {
            album.setEditable(false);
        }
    }
    
    public void editAction(SempicAlbum album) {
        album.setEditable(true);
    }*/
}
