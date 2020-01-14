/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.dao.PhotoFacade;
import fr.uga.miashs.sempic.entities.SempicPhoto;
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
public class ListPhotos {
    
    private DataModel<SempicPhoto> dataModel;
    
    @Inject
    private PhotoFacade photoDao;
    
    public DataModel<SempicPhoto> getDataModel() {
        if (dataModel == null) {
            dataModel = new ListDataModel<>(photoDao.findAll());
        }
        return dataModel; 
    }
    
}
