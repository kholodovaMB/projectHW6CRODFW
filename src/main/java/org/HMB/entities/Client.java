package org.HMB.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class Client {
    private String name;
    private Long id;

    public Client(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id= '" + id + '\'' +
                ", name= " + name +
                '}';
    }
}
