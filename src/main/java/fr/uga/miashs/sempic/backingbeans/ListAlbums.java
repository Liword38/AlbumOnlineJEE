/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
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
public class ListAlbums {

    private DataModel<SempicAlbum> dataModel;
    
    private SempicAlbum soloAlbum;

    public ListAlbums() {
    }

    
    @Inject
    private AlbumFacade albumDao;

    public DataModel<SempicAlbum> getDataModel() {
        if (dataModel == null) {
            dataModel = new ListDataModel<>(albumDao.findAll());
        }
        return dataModel;
    }
    
    public SempicAlbum getSoloAlbum(long albumId) {      
        if (soloAlbum == null) {
        soloAlbum = albumDao.read(albumId);
        }
        return soloAlbum;
    }
    
    
}
