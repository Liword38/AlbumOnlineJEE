/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.dao;

import fr.uga.miashs.sempic.entities.SempicGroup;
import fr.uga.miashs.sempic.entities.SempicUser;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jerome David <jerome.david@univ-grenoble-alpes.fr>
 */
@Stateless
public class GroupFacade extends AbstractJpaFacade<Long, SempicGroup> {

    public GroupFacade() {
        super(SempicGroup.class);
    }

    public List<SempicGroup> findAllByOwner(long userId) {
        TypedQuery<SempicGroup> q = getEntityManager().createQuery("SELECT DISTINCT g FROM SempicGroup g, SempicUser u WHERE u.id=:userId AND g.grpOwner=u", SempicGroup.class);
        q.setParameter("userId", userId);
        return q.getResultList();
    }

    public void addMember(long groupId, long userId) {
        Query q = getEntityManager().createNativeQuery("INSERT INTO SEMPICGROUP_SEMPICUSER(MEMBERS_ID,MEMBEROF_ID) VALUES (?1,?2)");
        q.setParameter(1, userId);
        q.setParameter(2, groupId);
        q.executeUpdate();
    }

    public void deleteMember(long groupId, long userId) {
        Query q = getEntityManager().createNativeQuery("DELETE FROM SEMPICGROUP_SEMPICUSER WHERE MEMBERS_ID=?1 AND MEMBEROF_ID=?2");
        q.setParameter(1, userId);
        q.setParameter(2, groupId);
        q.executeUpdate();
    }

    //pas testé
    public void addAlbum(long groupId, long albumId) {
        Query q = getEntityManager().createNativeQuery("INSERT INTO SEMPICALBUM_SEMPICUSER (MEMBEROFALBUM_ID,SHAREDWITHGRP_ID) VALUES (?1,?2)");
        q.setParameter(1, albumId);
        q.setParameter(2, groupId);
        q.executeUpdate();
    }

    //Pas testé
    public void deleteAlbum(long groupId, long albumId) {
        Query q = getEntityManager().createNativeQuery("DELETE FROM SEMPICALBUM_SEMPICUSER WHERE MEMBEROFALBUM_ID=?1 AND SHAREDWITHGRP_ID=?2");
        q.setParameter(1, groupId);
        q.setParameter(2, groupId);
        q.executeUpdate();
    }
}
