package ru.student.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.student.services.service.DatabaseFunctionalService;


@Controller
public class StartPageController {

    private final DatabaseFunctionalService dbService;

    @FXML
    private TextField dbName;

    @FXML
    private TextField tableName;

    @FXML
    private TextField columnDatatype;

    @FXML
    private Button dbSubmitButton;

    @FXML
    private Button tableSubmitButton;

    @FXML
    private TextField columnName;

    @FXML
    private Button addColumnButton;

    @FXML
    private Button deleteColumnButton;

    @Autowired
    public StartPageController(DatabaseFunctionalService dbService) {
        this.dbService = dbService;
    }

    public void initialize() {
        dbSubmitButton.setOnAction(actionEvent -> dbService.createDatabase(dbName.getText()));
        tableSubmitButton.setOnAction(actionEvent -> dbService.createTable(tableName.getText()));
        addColumnButton.setOnAction(actionEvent -> dbService.addColumn(tableName.getText(), columnName.getText(), columnDatatype.getText()));
        deleteColumnButton.setOnAction(actionEvent -> dbService.deleteColumn(tableName.getText(), columnName.getText()));
    }
}
