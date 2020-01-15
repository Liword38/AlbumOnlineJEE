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
 * @author Martin
 */
@Named
@RequestScoped
public class ListSharedGroups {
    private DataModel<SempicGroup> dataModel;
    
    @Inject
    private GroupFacade groupDao;
    @Inject
    private SessionTools sessionTools;
    
    public DataModel<SempicGroup> getDataModel() {
        long currentUserID = sessionTools.getConnectedUser().getId();
        
        if (dataModel == null) {
            System.out.println("appel à findAllByOwner()");
            dataModel = new ListDataModel<>(groupDao.findSharedGroups(currentUserID));
        }
        return dataModel;
    }
}