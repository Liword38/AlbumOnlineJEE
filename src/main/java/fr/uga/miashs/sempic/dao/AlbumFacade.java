/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.dao;

import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicGroup;
import fr.uga.miashs.sempic.entities.SempicUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Martin
 */
@Stateless
public class AlbumFacade extends AbstractJpaFacade<Long, SempicAlbum> {

    public AlbumFacade() {
        super(SempicAlbum.class);
    }
    
   public List<SempicAlbum> findAllByOwner(long userId) {
        System.out.println("A l'intérieur de findAllByOwner()");
        TypedQuery<SempicAlbum> q = getEntityManager().createQuery("SELECT DISTINCT a FROM SempicAlbum a, SempicUser u WHERE u.id=:userId AND a.albumOwner=u", SempicAlbum.class);
        q.setParameter("userId", userId);
        return q.getResultList();
    }
    
    //Pas testé probablement faux
    public List<SempicAlbum> findByUser(Long userId) {
        Query q;
        q = getEntityManager().createNativeQuery("SELECT DISTINCT a FROM SempicAlbum a WHERE a.sempicalbum_id IN(SELECT g FROM SempicGroup g WHERE g.members_id=:userId) AND a.sempicalbum_id=g.memberofalbum_id");
        q.setParameter("userId", userId);
        return q.getResultList();

    }

    //TODO
    public void addPhoto() {

    }

    //TODO
    public void deletePhoto() {

    }
}
