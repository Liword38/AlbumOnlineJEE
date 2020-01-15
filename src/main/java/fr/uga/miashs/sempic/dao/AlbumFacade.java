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
        TypedQuery<SempicAlbum> q = getEntityManager().createQuery("SELECT DISTINCT a FROM SempicAlbum a, SempicUser u WHERE u.id=:userId AND a.albumOwner=u", SempicAlbum.class);
        q.setParameter("userId", userId);
        return q.getResultList();
    }
    
    //Pas testé probablement faux
    public List<SempicAlbum> findAllByUser(long userId) {
        TypedQuery<SempicAlbum> q;
        q = getEntityManager().createQuery("SELECT DISTINCT a "
                                        + " FROM SempicAlbum a, SempicGroup g, SempicUser u "
                                        + " WHERE  a.id=g.memberOfAlbums AND g.id=u.grpMembers_id",SempicAlbum.class);
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
