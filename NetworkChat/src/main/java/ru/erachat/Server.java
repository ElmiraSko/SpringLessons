package ru.erachat;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private int serverPort;
    private ConnectBase connectBase;  // ссылка на объект ConnectBase
    private List<ClientHandler> clients; //clients- ссылка на список(ArrayList) будущих клиентов

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    public int getServerPort() {
        return serverPort;
    }
    public void setConnectBase(ConnectBase connection) {
        connectBase = connection;
    }
    public ConnectBase getConnectBase() {
        return connectBase;
    }

    public void starting() {
        try (ServerSocket server = new ServerSocket(serverPort)) {
            // объект connectBase должен выполнить подключение к базе через метод buildConnect()
            connectBase.buildConnect(); // в момент выполнения этой строчки,
            // Spring дастает из мапы(котекста) созданный ранее объект(been) класса ConnectBase
            //создаём список clients, для хранения объектов типа ClientHandler
            clients = new ArrayList<>();
            // заходим в цикл ожидания клиентов
            while (true) {
                System.out.println("Сервер ожидает подключение на порту: " + serverPort);
                Socket socket = server.accept(); // подключенного клиента отдаем ClientHandler-у
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket); //создали держателя ClientHandler
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
            e.printStackTrace();
        } finally {
            if (connectBase != null) { //здесь надо закрыть базу
                connectBase.close();
            }
        }
    }
    //проверяем, есть ли среди подключившихся клиентов этот ник
    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }
    //отправка сообщения всем клиентам подряд
    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }
    //отправка сообщения (msg) конкретному клиенту с именем forClient от from
    public synchronized void sendOnly(String msg, String forClient, String from){
        for (ClientHandler o : clients) {
            if (o.getName().equals(forClient)){
                o.sendMsg("От " + from + ": " + msg);
            }
        }

    }

    public synchronized void unsubscribe(ClientHandler o) { //удаление клиента
        clients.remove(o);
    }

    public synchronized void subscribe(ClientHandler o) { //добавление клиента
        clients.add(o);
    }
}
