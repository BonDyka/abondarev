package ru.job4j.carstore.persistence;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;

import java.sql.Connection;
import java.util.Properties;

public class MigrationUtil {
    public static void setMigration() {
        Properties info = new Properties();
        info.put("user", "sa");
        info.put("password", "");
        try {
            Connection con = new org.hsqldb.jdbcDriver().connect("jdbc:hsqldb:mem:carstore", info);
            liquibase.database.Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(con));
            Liquibase liquibase = new Liquibase(
                    "src/test/resources/liquibase/test.xml",
                    new FileSystemResourceAccessor(),
                    database
            );
            liquibase.update("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
