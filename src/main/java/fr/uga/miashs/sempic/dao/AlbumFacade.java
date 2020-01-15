/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.dao;

import fr.uga.miashs.sempic.entities.SempicAlbum;
import java.util.List;
import javax.ejb.Stateless;
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
    
    //Récupère tous les albums dont l'User propriétaire a userId comme id
   public List<SempicAlbum> findAllByOwner(long userId) {
        TypedQuery<SempicAlbum> q = getEntityManager().createQuery("SELECT DISTINCT a FROM SempicAlbum a, SempicUser u WHERE u.id=:userId AND a.albumOwner=u", SempicAlbum.class);
        q.setParameter("userId", userId);
        return q.getResultList();
    }
    
   //Récupère tous les albums partagés dans un groupe auquel appartient l'User d'id userId
    public List<SempicAlbum> findAllByUser(long userId) {
        TypedQuery<SempicAlbum> q;
        q = getEntityManager().createQuery("SELECT DISTINCT a FROM SempicAlbum a LEFT JOIN a.sharedWithGrp g LEFT JOIN g.grpMembers u WHERE u.id=:userId" ,SempicAlbum.class);
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
