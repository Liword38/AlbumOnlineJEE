/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.dao.SempicUserFacade;
import fr.uga.miashs.sempic.entities.SempicUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jerome David <jerome.david@univ-grenoble-alpes.fr>
 */
@Named
@RequestScoped
public class ListUsers {

    private DataModel<SempicUser> dataModel;
    private DataModel<SempicUser> membersOfDataModel;

    @Inject
    private SempicUserFacade userDao;
    
    @Inject
    private GroupFacade groupDao;

    public DataModel<SempicUser> getDataModel() {
        if (dataModel == null) {
            dataModel = new ListDataModel<>(userDao.findAll());
        }
        return dataModel;
    }

    public DataModel<SempicUser> getMembersOfDataModel(long groupId) {
        System.out.println("On cherche des membres");
        if (membersOfDataModel == null) {
            membersOfDataModel = new ListDataModel<>(userDao.findByMemberOfGrp(groupId));
        }
        return membersOfDataModel;
    }
    /*
    public DataModel<SempicUser> getNotInGroup(long groupId) {
        
        if (notMembersDataModel == null) {
            notMembersDataModel = new ListDataModel<>(userDao.findNotInGroup(groupId));
        }
        return notMembersDataModel;
        
    }*/
    

}
