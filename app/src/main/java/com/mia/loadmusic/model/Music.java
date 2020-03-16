package com.mia.loadmusic.model;

public class Music {
    private String id;
    private String name;
    private String image;
    private String artist;
    private String filePath;
    private long duration;
    private String nameAlbum;
    private String size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public Music setSize(String size) {
        this.size = size;
        return this;
    }

    public String getName() {
        return name;
    }

    public Music setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Music setImage(String image) {
        this.image = image;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public Music setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public Music setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public long getDuration() {
        return duration;
    }

    public Music setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public Music setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
        return this;
    }
}
