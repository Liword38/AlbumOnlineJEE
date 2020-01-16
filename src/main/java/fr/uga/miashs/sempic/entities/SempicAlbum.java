/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Martin
 */

@Entity
public class SempicAlbum implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private SempicUser albumOwner;
        
    
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<SempicGroup> sharedWithGrp;

    private String description;

    @NotBlank(message = "Il faut un nom à cet album")
    private String name;

   
    @OneToMany(mappedBy = "inAlbum", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY )
    private Set<SempicPhoto> photos;
    

    public SempicAlbum() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public SempicUser getAlbumOwner() {
        return albumOwner;
    }

    public void setAlbumOwner(SempicUser albumOwner) {
        this.albumOwner = albumOwner;
    }

    public Set<SempicGroup> getSharedWith() {
        return sharedWithGrp;
    }

    public void setSharedWith(Set<SempicGroup> sharedWith) {
        this.sharedWithGrp = sharedWith;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SempicGroup> getSharedWithGrp() {
        return sharedWithGrp;
    }

    public void setSharedWithGrp(Set<SempicGroup> sharedWithGrp) {
        this.sharedWithGrp = sharedWithGrp;
    }

    public Set<SempicPhoto> getPhotos() {
        return photos;
    }

    public void addPhoto (SempicPhoto p) {
        if (photos==null) {
            photos = new HashSet<>();
        }
        photos.add(p);
    }
    
    public void setPhotos(Set<SempicPhoto> photos) {
        this.photos = photos;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final SempicAlbum other = (SempicAlbum) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SempicAlbum{" + "id=" + id + ", description=" + description + ", name=" + name + '}';
    }

 

 
  
}
