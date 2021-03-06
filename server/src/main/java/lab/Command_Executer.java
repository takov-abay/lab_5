package lab;

import DB.DBWorker;
import DB.Login;
import commands.Commands;
import data.FabricOfShell;
import data.Shell;
import data.UserShell;
import serializer.Serializer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class Command_Executer implements Callable<String> {
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    int flag;
    Serializer serializer;
    byte[] byteArray;

    public Command_Executer(int flag, Serializer serializer, byte[] byteArray) {
        this.flag = flag;
        this.serializer = serializer;
        this.byteArray = byteArray;
    }

    public String call() throws IOException {
        if (flag == 1) {
            return handle_command();
        } else if (flag == 2) {
            return handle_login();
        } else if (flag == 0) {
            return handle_script();
        } else return null;
    }

    private String handle_command() throws IOException {
        Shell shell = serializer.fromByteArray(byteArray, Shell.class);
        try {
        Statement statement = DBWorker.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from users");
        boolean flag = false;
        while (rs.next()) {
            String name = rs.getString("username");
            String pass = rs.getString("password");
            if (shell.getPassword().equals(pass) && shell.getUser().equals(name)) flag = true;
        }
        if (flag) {

            Commands useCommands = new Commands(shell.getName(), shell.getParameter(), shell.getMovie(), shell.getUser());
            LOGGER.info("Получена команда: " + shell.getName());
            return useCommands.execute();
        } else {
            return "Вы не можете выполнить команду, т.к. пароли не совпадают";
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Не удалось выполнить команду";
        }
    }

    private String handle_login() {
        UserShell userShell = serializer.fromByteArray(byteArray, UserShell.class);
        Login login = new Login(userShell);
        login.login();
        LOGGER.info("Получен пользователь: " + userShell.getLogin());
        return login.getInfo();
    }

    private String handle_script() throws IOException {
        String result = "";
        FabricOfShell fabricOfShell = serializer.fromByteArray(byteArray, FabricOfShell.class);
        LOGGER.info("Начало работы со скриптом");
        for (int i = 0; i < fabricOfShell.getSize(); i++) {
            Shell shell = fabricOfShell.getShell(i);
            Commands useCommands = new Commands(shell.getName(), shell.getParameter(), shell.getMovie(), shell.getUser());
            LOGGER.info("Получена команда: " + shell.getName());
            result += useCommands.execute();
        }
        return result;
    }
}
