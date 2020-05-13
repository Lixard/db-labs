package ru.student.ui.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.student.services.dto.DoctorDto;
import ru.student.services.service.DoctorsService;


@Controller
public class StartPageController {

    private final DoctorsService doctorsService;

    @FXML
    private TextField lastName;

    @FXML
    private TableView<DoctorDto> table;

    @FXML
    private TableColumn<DoctorDto, String> firstNameColumn;

    @FXML
    private TableColumn<DoctorDto, String> lastNameColumn;

    @FXML
    private TableColumn<DoctorDto, String> secondNameColumn;

    @FXML
    private TextField firstName;

    @FXML
    private TextField secondName;

    @FXML
    private Button addLineButton;

    @FXML
    private Button changeLineButton;

    @FXML
    private Button deleteLineButton;

    @Autowired
    public StartPageController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        lastNameColumn.setSortType(TableColumn.SortType.DESCENDING);
        update();

//        addLineButton.setOnAction(actionEvent -> {
//            doctorsService.insert(new DoctorDto(firstName.getText(), lastName.getText(), secondName.getText()));
//            update();
//        });
    }

    public void update() {
        ObservableList<DoctorDto> list = FXCollections.observableList(doctorsService.getDoctors());
        table.setItems(list);
    }


}
