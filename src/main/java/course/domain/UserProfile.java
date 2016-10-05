package course.domain;

/**
 * Created by Nox on 05.10.2016.
 */
public class UserProfile {
    private String id;
    private String name;
    private String photoUrl;
    private String socialUrl;
    private String sessionId;

    public UserProfile(){}

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserProfile(String id, String sessionId, String name, String photoUrl, String socialUrl){
        this.id = id;
        this.sessionId = sessionId;
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
