package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthService {
   private ArrayList<String> loggedUsers;
   private Connection connection;

    public AuthService() {
        this.loggedUsers = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isUserLogged(String login) {
        if(loggedUsers.contains(login)) {
            return true;
        }
        return false;
    }

    public boolean userExists(String login) {
        try {
            PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement("SELECT * FROM users WHERE login = ?");
            preparedStatement.setString(1, login);
            ResultSet set = preparedStatement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public void registration(String login, String password) {

    }


}

