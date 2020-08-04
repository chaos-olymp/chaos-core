package de.chaosolymp.core.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseProvider {

    private final HikariConfig config;
    private final HikariDataSource dataSource;

    public DatabaseProvider(String jdbcUrl, String username, String password) {
        this.config = new HikariConfig();
        this.config.setJdbcUrl(jdbcUrl);
        this.config.setUsername(username);
        this.config.setPassword(password);
        this.config.addDataSourceProperty("cachePrepStmts" , "true");
        this.config.addDataSourceProperty("prepStmtCacheSize" , "250");
        this.config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");
        this.dataSource = new HikariDataSource(this.config);
    }

    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

    public HikariConfig getConfig() {
        return this.config;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
