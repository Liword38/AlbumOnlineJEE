/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.uga.miashs.sempic.entities;

import java.io.File;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Martin
 */
@Entity
public class SempicPhoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private File image;
    
    @NotBlank(message="La photo doit avoir un nom")
    private String name;
    
    @ManyToOne
    private SempicAlbum inAlbum;

    
    public SempicPhoto() {
    }
    
    public Long getId() {
        return id;
    }
     public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public SempicAlbum getInAlbum() {
        return inAlbum;
    }

    public void setInAlbum(SempicAlbum inAlbum) {
        this.inAlbum = inAlbum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final SempicPhoto other = (SempicPhoto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SempicPhoto{" + "id=" + id + ", name = " + name + '}';
    }



    
    
}
