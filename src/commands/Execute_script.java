package commands;

/**
 * Executes script
 * @author Not Yet
 */
public class Execute_script implements ICommand {
    public Execute_script(){
        Commands.addNewCommand("execute_script", this);
    }

    /**
     * TODO ДОБАВИТЬ ДЖАВАДОК СЮДА
     * @param parameter1
     * @param parameter2
     */
    @Override
    public void Do(String parameter1, String parameter2){

    }
}
