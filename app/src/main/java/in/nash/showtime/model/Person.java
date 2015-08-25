package in.nash.showtime.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Avinash Hindupur on 25/06/15.
 */
public class Person {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profilePath;

    @SerializedName("credit_id")
    private String creditId;

    @SerializedName("character")
    private String character;

    @SerializedName("job")
    private String job;

    @SerializedName("department")
    private String department;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
