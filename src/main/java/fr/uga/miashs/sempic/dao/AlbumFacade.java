/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.dao;

import fr.uga.miashs.sempic.entities.SempicAlbum;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Martin
 */
@Stateless
public class AlbumFacade extends AbstractJpaFacade<Long,SempicAlbum>{
    
    public AlbumFacade() {
        super(SempicAlbum.class);
    }
    
//Probablement de la merde    
//    //Pas testé
//    public void addGroup(long albumId, long groupId) {
//        Query q = getEntityManager().createNativeQuery("INSERT INTO SEMPICALBUM_SEMPICGROUP(MEMBEROFALBUM_ID,SHAREDWITHGRP_ID) VALUES (?1,?2)");
//        
//        q.setParameter(1,groupId);
//        q.setParameter(2,albumId);
//        q.executeUpdate();
//    
//}
//    
//    //pas testé
//    public void deleteGroup(long albumId, long groupId) {
//        Query q = getEntityManager().createNativeQuery("DELETE FROM SEMPICALBUM_SEMPICGROUP WHERE MEMBEROFALBUM_ID=?1 and SHAREDWITH_ID=?2");
//        q.setParameter(1,groupId);
//        q.setParameter(2,albumId);
//        q.executeUpdate();
//    }
    
    //TODO
    public void findByUser() {
        
    }
    
    //TODO
    public void addPhoto() {
        
    }
    
    //TODO
    public void deletePhoto() {
        
    }
}
