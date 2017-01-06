package com.example.nguyendinh.exam_php;

/**
 * Created by nguyendinh on 06/01/2017.
 */

public class Animal {
    private int id;
    private String name;
    private String image;
    private String voice;

    public Animal() {
    }

    public Animal(int id, String name, String image, String voice) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.voice = voice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
