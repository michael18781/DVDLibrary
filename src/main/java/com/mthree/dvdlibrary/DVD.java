package com.mthree.dvdlibrary;

public class DVD {
    private String title;
    private String releaseDate;
    private double mpaaRating;
    private String directorsName;
    private String studio;
    private String userNote;

    public DVD(String title, String releaseDate, double mpaaRating, String directorsName, String studio, String userNote) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.mpaaRating = mpaaRating;
        this.directorsName = directorsName;
        this.studio = studio;
        this.userNote = userNote;
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(double mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    // toString method so we can see the DVD data when displaying it in console
    @Override
    public String toString() {
        return "DVD{" +
                "title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mpaaRating=" + mpaaRating +
                ", directorsName='" + directorsName + '\'' +
                ", studio='" + studio + '\'' +
                ", userNote='" + userNote + '\'' +
                '}';
    }
}
