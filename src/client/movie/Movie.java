package client.movie;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Stores movies
 */
public class Movie implements Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer length; //Поле может быть null, Значение поля должно быть больше 0
    private MovieGenre genre; //Поле может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person director; //Поле не может быть null

    /**
     * Constructors, creates new server.movie
     */
    public Movie() {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.creationDate = new Date();
    }

    public Movie(String name, String id, Date date, Coordinates coordinates, int oscarsCount, int length, Person director) {
        this.name = name;
        this.id = Long.parseLong(id);
        this.creationDate = date;
        this.coordinates = coordinates;
        this.oscarsCount = oscarsCount;
        this.length = length;
        this.director = director;
    }

    /**
     * @param rating rating of server.movie
     */
    public void setMpaaRating(String rating) {
        if (rating.equals("G")) {
            this.mpaaRating = MpaaRating.G;
        } else if (rating.equals("PG")) {
            this.mpaaRating = MpaaRating.PG;
        } else if (rating.equals("PG_13")) {
            this.mpaaRating = MpaaRating.PG_13;
        }
    }

    /**
     * @param oscarsCount amount of oscars
     */
    public void setOscarsCount(int oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    /**
     * @param x coordinate x
     * @param y coordinate y
     */
    public void setCoordinates(int x, float y) {
        coordinates = new Coordinates(x, y);
    }

    public void setCoordinates(int x) {
        coordinates = new Coordinates(x);
    }

    /**
     * @param name     name of director
     * @param height   height of director
     * @param weight   weight of director
     * @param location location of director
     */
    public void setDirector(String name, Double height, Float weight, Location location) {
        director = new Person(name, height, weight, location);
    }

    /**
     * @return length of server.movie
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @param length length of server.movie
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return name of server.movie
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return genre of server.movie
     */
    public String getGenre() {
        try {
            return genre.toString();
        } catch (Exception e) {
            return "null";
        }
    }

    /**
     * @param genre genre of server.movie
     */
    public void setGenre(String genre) {
        if (genre.equals("COMEDY")) {
            this.genre = MovieGenre.COMEDY;
        } else if (genre.equals("MUSICAL")) {
            this.genre = MovieGenre.MUSICAL;
        } else if (genre.equals("FANTASY")) {
            this.genre = MovieGenre.FANTASY;
        }
    }

    /**
     * @return string with information about server.movie
     */
    public String getInfo() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(this.creationDate);
        return "Movie: " + name + "\nID: " + id + "\nCoordinates: " + this.coordinates.getCoordinates() +
                "\nDate of creation: " + date + "\nCount of oscars: " + oscarsCount +
                "\nLength: " + length + "\nGenre: " + getGenre() + "\nMPAA rating: " + mpaaRating +
                "\nDirector: " + director.getPerson();
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(this.creationDate);
        return "Movie: " + name + " (ID " + id + ") " + date;
    }

    public long getId() {
        return this.id;
    }
}