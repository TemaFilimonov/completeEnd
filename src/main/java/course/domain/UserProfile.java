package course.domain;

/**
 * Created by Nox on 05.10.2016.
 */
public class UserProfile {
    private String name;
    private String photoUrl;
    private String socialUrl;

    public UserProfile(){}
    public UserProfile(String name, String photoUrl, String socialUrl){
        this.name = name;
        this.photoUrl = photoUrl;
        this.socialUrl = socialUrl;
    }

    public String getSocialUrl() {
        return socialUrl;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setSocialUrl(String socialUrl) {
        this.socialUrl = socialUrl;
    }
}
