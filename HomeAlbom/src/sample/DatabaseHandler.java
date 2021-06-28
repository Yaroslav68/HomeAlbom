package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysq;//" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(String surname, String name, String patronymic, String dom, String street,
                           String hata) {
        String insert = "INSERT INTO " + Const.IMAGES_TABLE + "(" + Const.IMAGES_ID + "," + Const.IMAGES_NAME + "," + ")" + "VALUES(?,?)";

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?user=root&password=319764Ertv30&serverTimezone=UTC");
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, idimages);
            prSt.setString(2, images);


            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}