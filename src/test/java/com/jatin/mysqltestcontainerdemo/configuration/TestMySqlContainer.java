package com.jatin.mysqltestcontainerdemo.configuration;

import org.testcontainers.containers.MariaDBContainer;

public class TestMySqlContainer extends MariaDBContainer<TestMySqlContainer> {


    //    private static final String IMAGE_VERSION = "mysql:latest";
    private static final String IMAGE_VERSION = "mariadb:10.4";
    private static TestMySqlContainer container;

    private TestMySqlContainer() {
        super(IMAGE_VERSION);
    }

    public static TestMySqlContainer getInstance() {
        if (container == null) {
            container = new TestMySqlContainer();
        }
        return container;
    }

    public String getDriverClassName() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return "org.mariadb.jdbc.Driver";
        } catch (ClassNotFoundException var2) {
            return "org.mariadb.jdbc.Driver";
        }
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
        System.setProperty("DB_DRIVER_CLASS", container.getDriverClassName());
    }

    @Override
    public void stop() {
    }
}
