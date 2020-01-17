/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.dao.SempicUserFacade;
import fr.uga.miashs.sempic.entities.SempicGroup;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mercadieremeline
 */
@Named
@RequestScoped
public class AddGroupMembers {
    
    private SempicGroup target;

    @Inject
    private GroupFacade groupDao;

    @Inject
    private SempicUserFacade memberDao;
    

    public AddGroupMembers() {

    }

    @PostConstruct
    public void init() {
        target = new SempicGroup();
    }

    public SempicGroup getTarget() {
        return target;
    }

    public void setTarget(SempicGroup target) {
        this.target = target;
    }
    
    
    public void setOwnerId(String id) {
        System.out.println(id);
        target.setOwner(memberDao.read(Long.valueOf(id)));
    }

    public String getOwnerId() {
        if(target.getOwner() == null) {
            return "-1";
        }
        return "" + target.getOwner().getId();
    }

    
    public void add(long memberId) {
        System.out.println("Ajout membre");
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println("Les params de la requête:" + params.toString());
        String groupId = params.get("groupId");
        System.out.println("On essaie d'ajouter un membre dans le groupe d'id"+ groupId);
        
        System.out.println("ID membre = " + memberId);
        groupDao.addMember(Long.parseLong(groupId), memberId);

        
    }
}
