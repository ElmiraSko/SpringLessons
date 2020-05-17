package ru.erachat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

//@Component("connect")
public class ConnectBase {

    private Connection connection = null;
    private Statement state;

//    @Value("root")
    private String username;
//    @Value("my18Erasql")
    private String password;
//    @Value("jdbc:mysql://localhost/chatserver1?serverTimezone=Europe/Moscow&useSSL=false")
    private String db_URL;
//    @Value("com.mysql.cj.jdbc.Driver")
    private String db_Driver;

    // Сетторы которыми пользуется Spring для инициализации полей класса
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setDb_URL(String db_URL) {
        this.db_URL = db_URL;
    }
    public void setDb_Driver(String db_Driver) {
        this.db_Driver = db_Driver;
    }


    // объект ConnectBase создается 1 раз на сервере через Spring, когда
    // вызывается метод buildConnect() для подключения к базе данных на сервере MySQL
    public void buildConnect() {
        try {
            Class.forName(db_Driver);
            connection = DriverManager.getConnection(db_URL, username, password);
            System.out.println("Все отлично! Мы подключились к БД");
        } catch (ClassNotFoundException ex) {
            System.out.println("Не удалось соединиться с базой: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Что-то не так, надо разобраться! ");
            e.printStackTrace();
        }
    }
    // метод для закрытия connection
    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Не закрыли" + e.getMessage());
        }
    }
    //  получение ника по логину и паролю
    public String getNickByLoginPass(String login, String pass) {
        String nick = null;
        try {
            String query = "SELECT * FROM auth WHERE login = '" + login + "'" + " AND password = '" + pass + "'";
            state = connection.createStatement();
            ResultSet res = state.executeQuery(query);
            while (res.next()){
                nick = res.getString("nick");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return nick;
    }
    //  метод добавляет в базу нового пользователя
    public String registration(String login, String pass, String nick){
        String sss = null;
        String queryALL = "SELECT * FROM auth";
        int count = 0;
        try {
            state = connection.createStatement();
            ResultSet res = state.executeQuery(queryALL);
            while (res.next()){
                String log_  = res.getString("login");
                String pass_  = res.getString("password");
                String nick_  = res.getString("nick");
                if (!log_.equals(login) && !pass_.equals(pass) && !nick_.equals(nick)){
                }else count++;
            }
            if (count == 0){
                String query = "INSERT INTO auth (login, password, nick) VALUES ('" + login + "', '" + pass + "', '" + nick + "');";
                state.executeUpdate(query);
                System.out.println("Регистрация прошла успешно");
                ResultSet rs = state.executeQuery("SELECT * FROM auth WHERE login = '" + login + "'" + " AND password = '" + pass + "'");
                sss = rs.getString("nick");
            }else {
                System.out.println("Регистрация не выполнена");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return sss;
    }
    //  метод для смены ника
    String getNewNick(String login, String pass, String nick){
        String nickName = null;
        String query = "UPDATE auth SET nick = '" + nick + "' WHERE login = '" + login + "' AND password = '" + pass + "'";
        try {
            state = connection.createStatement();
            state.executeUpdate(query);
            ResultSet getNewNick = state.executeQuery("SELECT nick FROM auth WHERE login = '" + login + "'" + " AND password = '" + pass + "'");
            while (getNewNick.next()){
                nickName = getNewNick.getString("nick");}
        }catch (SQLException ex){
            ex.printStackTrace();}
        return nickName;
    }
}

