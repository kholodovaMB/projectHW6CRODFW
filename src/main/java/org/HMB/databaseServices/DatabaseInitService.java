package org.HMB.databaseServices;

import org.HMB.Database;
import org.HMB.dataBaseConfigurate.ConfigurateDB;
import org.HMB.fileReaderSQL.FileReaderSQL;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public void initDB(Database database) {

        Flyway flyway = Flyway.configure()
                .dataSource(ConfigurateDB.connectionURL, null, null)
                .load();

        flyway.migrate();
    }
}
