/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.entities.SempicGroup;
import fr.uga.miashs.sempic.entities.SempicUser;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mercadieremeline
 */
@Named
@RequestScoped
public class DeleteGroupMembers {

    
    private SempicGroup target;
    
    @Inject
    private GroupFacade groupDao;
    
    private Set<SempicUser> grpMembers;
    
    public DeleteGroupMembers() {
        
    }
    
    @PostConstruct
    public void init() {
        target = new SempicGroup();
    }
    
    
    
}
