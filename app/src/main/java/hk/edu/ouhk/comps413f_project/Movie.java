package hk.edu.ouhk.comps413f_project;

import java.io.Serializable;

public class Movie implements Serializable {
    private final String title;
    private final String overview;
    private final String releasedate;
    public Movie(String title, String overview, String releasedate) {

        this.title = title;
        this.overview = overview;
        this.releasedate = releasedate;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleasedate() {
        return releasedate;
    }
}
