package ru.student.services.service;

public interface DatabaseFunctionalService {
    void createDatabase(String databaseName);
    void createTable(String tableName);
    void addColumn(String tableName, String columnName, String columnDataType);
    void deleteColumn(String tableName, String columnName);
}
