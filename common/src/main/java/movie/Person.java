package movie;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private Float weight; //Поле может быть null, Значение поля должно быть больше 0

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Double getHeight() {
        return height;
    }

    public Float getWeight() {
        return weight;
    }

    private Location location; //Поле может быть null

    public Integer getLocationX() {
        return location.getX();
    }

    public long getLocationY() {
        return location.getY();
    }

    public Integer getLocationZ() {
        return location.getZ();
    }

    public String getLocationName() {
        return location.getName();
    }


    public Person() {
        this.birthday = new Date();
    }

    /**
     * Constructor, creates person
     *
     * @param name     name of person
     * @param height   height of person
     * @param weight   weight of person
     * @param location location of person
     */
    public Person(String name, Double height, Float weight, Location location) {
        if (weight == 0) {
            this.weight = null;
        } else {
            this.weight = weight;
        }
        if (height == 0) {
            this.height = null;
        } else {
            this.height = height;
        }
        this.name = name;
        this.location = location;
        this.birthday = new Date();
    }

    public Person(String name, Double height, Float weight, Location location, Date date) {
        if (weight == 0) {
            this.weight = null;
        } else {
            this.weight = weight;
        }
        if (height == 0) {
            this.height = null;
        } else {
            this.height = height;
        }
        this.name = name;
        this.location = location;
        this.birthday = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return string with information about person
     */
    public String getPerson() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(this.birthday);
        return "Name: " + name + "\nBirthday: " + date + "\nHeight: " + height + "\nWeight: " + weight + location.getLocation();
    }

}