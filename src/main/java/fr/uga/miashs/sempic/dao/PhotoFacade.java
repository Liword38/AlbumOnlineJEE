/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.dao;

import fr.uga.miashs.sempic.entities.SempicPhoto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Martin
 */
@Stateless
public class PhotoFacade extends AbstractJpaFacade<Long,SempicPhoto> {
    
    public PhotoFacade() {
        super(SempicPhoto.class);
    }
    
    //Renvoie les photos de l'album d'id albumId (PAS TESTE)
    public List<SempicPhoto> findByAlbum(long albumId) {
        TypedQuery<SempicPhoto> q = getEntityManager().createQuery("SELECT DISTINCT p FROM SempicPhoto p, SempicAlbum a WHERE p.inAlbum=a AND a.id=:albumId",SempicPhoto.class);
        q.setParameter("albumId", albumId);
        return q.getResultList();
    }
    
    
}
