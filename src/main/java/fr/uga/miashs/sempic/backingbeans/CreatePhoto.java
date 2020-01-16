/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.backingbeans;

import fr.uga.miashs.sempic.SempicException;
import fr.uga.miashs.sempic.SempicModelException;
import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.dao.PhotoFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicPhoto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Martin
 */
@Named
@ViewScoped
public class CreatePhoto implements Serializable {

    private SempicPhoto current;
    private Part photoPart;
    private File photoFile;

    @Inject
    private PhotoFacade photoDao;
    @Inject
    private AlbumFacade albumDao;

    public CreatePhoto() {
    }

    @PostConstruct
    public void init() {
        current = new SempicPhoto();
    }

    public void setInAlbum(long albumId) {
        //TODO
    }

    public SempicAlbum getInAlbum() {
        //TODO
        return null;
    }

    public File getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }

    public SempicPhoto getCurrent() {
        return current;
    }

    public void setCurrent(SempicPhoto current) {
        this.current = current;
    }

    public Part getPhotoPart() {
        return photoPart;
    }

    public void setPhotoPart(Part photoFile) {
        this.photoPart = photoFile;
        System.out.println("Photo part added:" + photoFile.toString());
    }

    public String create() {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println("Les params de la requête:" + params.toString());
        String albumId = params.get("albumId");
        System.out.println("On essaie de créer une photo dans l'album d'id"+ albumId);

        SempicAlbum album = albumDao.read(Long.parseLong(albumId));

        try {
            InputStream in = photoPart.getInputStream();
            System.out.println("My img: " + photoPart);
            photoFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + photoPart.getSubmittedFileName());
            photoFile.createNewFile();
            FileOutputStream out = new FileOutputStream(photoFile);
            System.out.println("My file: " + photoFile.getAbsoluteFile());
            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", photoFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return "failure";
        }
        String name = photoPart.getSubmittedFileName();
        current.setName(name);
        current.setInAlbum(album);
        current.setImage(photoFile);
        try {
            photoDao.create(current);
        } catch (SempicModelException ex) {
            ex.printStackTrace(System.out);
            return "failure";

        }

        return "success";

    }

}
