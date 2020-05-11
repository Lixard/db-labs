package ru.student.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMainFunctional {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseMainFunctional(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createDatabase(String name) {
        jdbcTemplate.update("CREATE DATABASE " + name);
    }

    public void createTable(String name) {
        jdbcTemplate.update("CREATE TABLE " + name + " ()");
    }

    public void createColumn(String tableName, String fieldName, String fieldDataType) {
        jdbcTemplate.update("ALTER TABLE " + tableName + " ADD COLUMN " + fieldName + " " + fieldDataType);
    }

    public void deleteColumn(String tableName, String fieldName) {
        jdbcTemplate.update("ALTER TABLE " + tableName + " DROP COLUMN " + fieldName);
    }
}
