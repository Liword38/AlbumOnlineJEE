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
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
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
public class ListSharedAlbums implements Serializable{
    
     private DataModel<SempicAlbum> dataModel;
    
    @Inject
    private AlbumFacade albumDao;    
    @Inject
    private SessionTools sessionTools;
    
    //Pas testé, marche surement pas
    public DataModel<SempicAlbum> getDataModel() {
        long currentUserId = sessionTools.getConnectedUser().getId();
        if (dataModel == null) {
            dataModel = new ListDataModel<>(albumDao.findAllByUser(currentUserId));
        }
        return dataModel;
    }
}
