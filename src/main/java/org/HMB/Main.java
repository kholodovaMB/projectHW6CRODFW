package org.HMB;

import org.HMB.databaseServices.DatabaseInitService;
import org.HMB.databaseServices.DatabaseQueryService;
import org.HMB.entities.Client;
import org.HMB.entitys.querysSQL.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        Database database = Database.getInstance();

        new DatabaseInitService().initDB(database);

        Connection connection = database.getConnection();

        ClientService clientService = new ClientService(connection);

        System.out.println("name = " + clientService.getById(5L));

        clientService.setName(5, "Adidas");
        clientService.deleteById(6);
        List<Client> clientList= clientService.listAll();
        for (Client client: clientList) {
            System.out.println(client.toString());
        }
        System.out.println("id for new client = " + clientService.create("Reebok"));

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}