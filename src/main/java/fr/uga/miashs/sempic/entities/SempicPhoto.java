///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package fr.uga.miashs.sempic.entities;
//
//import java.io.Serializable;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
///**
// *
// * @author Martin
// */
//@Entity
//@Table(name = "PHOTO")
//public class SempicPhoto implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    public Long getId() {
//        return id;
//    }
//    
//    //TODO: Mettre une vraie photo
//    @NotBlank(message="Une photo doit être donnée (pour le moment une string suffit)")
//    public String content;
//    
//    //TODO: Mapping
//    //@OneToOne(mappedBy = "photos", cascade = CascadeType.REMOVE )
//    private SempicAlbum ownerAlbum;
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof SempicPhoto)) {
//            return false;
//        }
//        SempicPhoto other = (SempicPhoto) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "fr.uga.miashs.sempic.entities.SempicPhoto[ id=" + id + " ]";
//    }
//    
//}
