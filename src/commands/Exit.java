package commands;

public class Exit implements ICommand {
    public Exit(){
        Commands.addNewCommand("exit", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        System.exit(0);
    }
}
