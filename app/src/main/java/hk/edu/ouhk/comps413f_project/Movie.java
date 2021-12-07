package hk.edu.ouhk.comps413f_project;

import java.io.Serializable;

public class Movie implements Serializable{
    private final String title;
    private final String overview;
    private final String releasedate;
    private final String icon;
    public Movie(String title, String overview, String releasedate, String icon) {

        this.title = title;
        this.overview = overview;
        this.releasedate = releasedate;
        this.icon = icon;
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

    public String geticon() {
        return icon;
    }

}
