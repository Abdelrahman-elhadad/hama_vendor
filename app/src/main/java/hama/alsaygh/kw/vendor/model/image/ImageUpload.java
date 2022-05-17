package hama.alsaygh.kw.vendor.model.image;

import android.net.Uri;

import java.io.Serializable;


public class ImageUpload implements Serializable {


    private Image image;
    private Uri uri;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


}
