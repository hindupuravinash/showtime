package in.nash.showtime.model;

import java.util.List;

/**
 * Created by avinash on 9/12/15.
 */
public class Credits {

    public Integer id;

    public List<Person> cast;

    public List<Person> crew;

    public List<Person> guest_stars;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Person> getCast() {
        return cast;
    }

    public void setCast(List<Person> cast) {
        this.cast = cast;
    }

    public List<Person> getCrew() {
        return crew;
    }

    public void setCrew(List<Person> crew) {
        this.crew = crew;
    }

    public List<Person> getGuestStars() {
        return guest_stars;
    }

    public void setGuestStars(List<Person> guestStars) {
        this.guest_stars = guestStars;
    }
}