package org.HMB;

import org.HMB.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createSt;
    private PreparedStatement getByNameSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement setNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement listAllSt;

    public ClientService(Connection connection){
        try {
            createSt = connection.prepareStatement("INSERT INTO client(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            getByIdSt = connection.prepareStatement("SELECT name FROM client WHERE id = ?");
            setNameSt = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
            deleteByIdSt = connection.prepareStatement("DELETE FROM client WHERE id = ?");
            listAllSt = connection.prepareStatement("SELECT * FROM client");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long create(String name) {
        Long id = null;
        try {
            createSt.setString(1, name);
            createSt.executeUpdate();
            ResultSet resultSet = createSt.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public String getById(long id){
        try {
            getByIdSt.setLong(1, id);
            ResultSet resultSet = getByIdSt.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            String name = resultSet.getString("name");
            return name;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setName(long id, String name){
        try {
            setNameSt.setString(1, name);
            setNameSt.setLong(2, id);
            setNameSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteById(long id){
        try {
            deleteByIdSt.setLong(1, id);
            deleteByIdSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Client> listAll(){
        List<Client> clients = new ArrayList<>();
        try (ResultSet resultSet = listAllSt.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Client client = new Client(name, id);
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }
}
