package ru.student.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.student.db.DatabaseMainFunctional;
import ru.student.services.service.DatabaseFunctionalService;

@Service
public class DatabaseFunctionalServiceImpl implements DatabaseFunctionalService {

    private final DatabaseMainFunctional databaseMainFunctional;

    @Autowired
    public DatabaseFunctionalServiceImpl(DatabaseMainFunctional databaseMainFunctional) {
        this.databaseMainFunctional = databaseMainFunctional;
    }

    @Override
    public void createDatabase(String databaseName) {
        databaseMainFunctional.createDatabase(databaseName);
    }

    @Override
    public void createTable(String tableName) {
        databaseMainFunctional.createTable(tableName);
    }

    @Override
    public void addColumn(String tableName, String columnName, String columnDataType) {
        databaseMainFunctional.createColumn(tableName, columnName, columnDataType);
    }

    @Override
    public void deleteColumn(String tableName, String columnName) {
        databaseMainFunctional.deleteColumn(tableName, columnName);
    }
}
