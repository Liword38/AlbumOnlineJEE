/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.dao.GroupFacade;
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
public class ListGroups {
    
    private DataModel<SempicGroup> dataModel;
    
    @Inject
    private GroupFacade groupDao;
    
    public DataModel<SempicGroup> getDataModel() {
        if (dataModel == null) {
            dataModel = new ListDataModel<>(groupDao.findAll());
        }
        return dataModel;
    }
    
}
