package ru.erachat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "ru.erachat")
public class SpringContext {

    @Bean
    public Server server(ConnectBase connectBase) {
        Server server = new Server();
        server.setConnectBase(connectBase);
        server.setServerPort(8087);
        return server;
    }
    @Bean("connect")
    public ConnectBase connectBase() {
        ConnectBase cb = new ConnectBase();
        cb.setDb_Driver("com.mysql.cj.jdbc.Driver");
        cb.setDb_URL("jdbc:mysql://localhost/chatserver1?serverTimezone=Europe/Moscow&useSSL=false");
        cb.setUsername("root");
        cb.setPassword("my18Erasql");
        return cb;
    }
}
