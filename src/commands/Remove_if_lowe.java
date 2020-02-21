package commands;

import data.MovieCollection;
import movie.Movie;

import java.util.Iterator;
import java.util.Map;

/**
 * Remove movie if it's id lower than my
 * @author Vladislav
 */
public class Remove_if_lowe implements ICommand {
    public Remove_if_lowe(){
        Commands.addNewCommand("remove_lower_key", this);
    }

    /**
     * Iterates through all elements of collection and removes movies by their id's
     * @param parameter1 id, all movie's which has lower id's than this will be removed from collection
     * @param parameter2 just ignore this
     */
    @Override
    public void Do(String parameter1, String parameter2) {
        MovieCollection movieCollection = new MovieCollection();
        Iterator it = movieCollection.getMap().entrySet().iterator();
        long givenId = Long.parseLong(parameter1);
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Movie movie = (Movie)pair.getValue();
            long currentId = movie.getId();
            if (currentId < givenId) {
                it.remove(); // avoids a ConcurrentModificationException
                movieCollection.removeMovie((String)pair.getKey());
            }
        }
    }
}
