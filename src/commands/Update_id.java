package commands;

import data.MovieCollection;
import input_output.InputMovie;

/**
 * Replace movie by it's id
 * @author Abay
 */
public class Update_id implements ICommand {
    public Update_id(){
        Commands.addNewCommand("update", this);
    }

    /**
     * Replaces old movie by new movie
     * @param parameter1 key to HashMap and id of new movie
     * @param parameter2 name of new movie
     */
    @Override //Гениальный класс
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        Long key = Long.parseLong(parameter1.trim());
        movieCollection.replaceMovie(key, InputMovie.create(key, parameter2));
    }
}
