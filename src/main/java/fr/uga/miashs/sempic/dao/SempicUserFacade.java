/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.dao;

import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.entities.SempicUser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Jerome David <jerome.david@univ-grenoble-alpes.fr>
 */
@Stateless
public class SempicUserFacade extends AbstractJpaFacade<Long, SempicUser> {

    @Inject
    private transient Pbkdf2PasswordHash hashAlgo;

    public SempicUserFacade() {
        super(SempicUser.class);
    }

    @Override
    public Long create(SempicUser user) throws SempicModelException {
        if (user.getPassword() != null) {
            user.setPasswordHash(hashAlgo.generate(user.getPassword().toCharArray()));
        }
        return super.create(user);
    }

    @Override
    public List<SempicUser> findAll() {
        EntityGraph entityGraph = this.getEntityManager().getEntityGraph("graph.SempicUser.groups-memberOf");
        return getEntityManager().createQuery(this.findAllQuery())
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

    //Retrouve les Users membres du groupe d'id groupId (PAS TESTE)
    public List<SempicUser> findByMemberOfGrp(long groupId) {
        TypedQuery<SempicUser> q = getEntityManager().createQuery("SELECT DISTINCT u FROM SempicUser u LEFT JOIN u.memberOf g WHERE g.id=:groupId", SempicUser.class);
        q.setParameter("groupId", groupId);
        System.out.println("On vérifie la nullité des membres");
        if (q.getResultList() == null) {
            System.out.println("On a pas toruvé de membre...");
            List<SempicUser> members = new ArrayList<SempicUser>();
            return members;
        }
        System.out.println("on a trouvé un/des membre(s) !");
        return q.getResultList();
    }

    public SempicUser login(String email, String password) throws SempicModelException {
        Query q = getEntityManager().createNamedQuery("query.SempicUser.readByEmail");
        q.setParameter("email", email);
        SempicUser u = (SempicUser) q.getSingleResult();
        if (hashAlgo.verify(password.toCharArray(), u.getPasswordHash())) {
            return u;
        }
        throw new SempicModelException("login failed");
    }

    public SempicUser readByEmail(String email) {
        Query q = getEntityManager().createNamedQuery("query.SempicUser.readByEmail");
        q.setParameter("email", email);
        return (SempicUser) q.getSingleResult();
    }

}
