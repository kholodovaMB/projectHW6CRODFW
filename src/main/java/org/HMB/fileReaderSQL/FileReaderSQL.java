package org.HMB.fileReaderSQL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderSQL {
    public String readSQLFile(String filePath) {
        StringBuilder sqlFile = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sqlFile.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlFile.toString();
    }
}
