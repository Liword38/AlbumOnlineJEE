/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jerome David <jerome.david@univ-grenoble-alpes.fr>
 */
@Entity
public class SempicGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Il faut un nom de groupe")
    private String name;

    @NotNull
    @ManyToOne
    private SempicUser grpOwner;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<SempicUser> grpMembers;

    @ManyToMany(mappedBy = "sharedWithGrp", fetch = FetchType.EAGER)
    private Set<SempicAlbum> memberOfAlbums;

    public SempicGroup() {

    }

    public long getId() {
        return id;
    }
    

    public void setId(long id) {
        this.id = id;
    }
    
    public Set<SempicAlbum> getMemberOfAlbums() {
        return memberOfAlbums;
    }

    public void setMemberOfAlbums(Set<SempicAlbum> memberOfAlbums) {
        this.memberOfAlbums = memberOfAlbums;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SempicUser getOwner() {
        return grpOwner;
    }

    public void setOwner(SempicUser owner) {
        this.grpOwner = owner;
        addMember(owner);
    }

    public Set<SempicAlbum> getMemberOfAlbum() {
        return memberOfAlbums;
    }

    public void setMemberOfAlbum(Set<SempicAlbum> memberOfAlbums) {
        this.memberOfAlbums = memberOfAlbums;
    }

    protected void addMember(SempicUser u) {
        if (grpMembers == null) {
            grpMembers = new HashSet<>();
        }
        grpMembers.add(u);
    }

    public Set<SempicUser> getMembers() {
        return grpMembers;
    }

    public void setMembers(Set<SempicUser> members) {
        this.grpMembers = members;
        members.add(grpOwner);
    }

    public SempicUser getGrpOwner() {
        return grpOwner;
    }

    public void setGrpOwner(SempicUser grpOwner) {
        this.grpOwner = grpOwner;
    }

    public Set<SempicUser> getGrpMembers() {
        return grpMembers;
    }

    public void setGrpMembers(Set<SempicUser> grpMembers) {
        this.grpMembers = grpMembers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SempicGroup other = (SempicGroup) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SempicGroup{" + "id=" + id + ", name=" + name + ", grpOwner=" + grpOwner + '}';
    }


}
