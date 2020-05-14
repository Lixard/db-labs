package ru.student.ui.controller;

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
import ru.student.services.dto.AppointmentDto;
import ru.student.services.dto.DoctorDto;
import ru.student.services.service.DoctorsService;

import java.sql.Timestamp;


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

    @FXML
    private Button exportToExcelButton;

    @FXML
    private TableView<AppointmentDto> appointmentTable;

    @FXML
    private TableColumn<AppointmentDto, String> patientLastName;

    @FXML
    private TableColumn<AppointmentDto, String> doctorLastName;

    @FXML
    private TableColumn<AppointmentDto, String> appointmentPlace;

    @FXML
    private TableColumn<AppointmentDto, Timestamp> appointmentDate;

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
        changeLineButton.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        deleteLineButton.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());

        table.getSelectionModel().selectedItemProperty().addListener((observableValue, doctorDto, t1) -> {
            DoctorDto doctor = table.getSelectionModel().getSelectedItem();
            if (doctor != null) {
                firstName.setText(doctor.getFirstName());
                lastName.setText(doctor.getLastName());
                secondName.setText(doctor.getSecondName());
            } else {
                firstName.setText("");
                lastName.setText("");
                secondName.setText("");
            }
        });

        addLineButton.setOnAction(actionEvent -> {
            doctorsService.insert(new DoctorDto(firstName.getText(), lastName.getText(), secondName.getText()));
            firstName.clear();
            secondName.clear();
            lastName.clear();
            update();
        });

        changeLineButton.setOnAction(actionEvent -> {
            DoctorDto doctorDto = table.getSelectionModel().getSelectedItem();
            doctorDto.setFirstName(firstName.getText());
            doctorDto.setLastName(lastName.getText());
            doctorDto.setSecondName(secondName.getText());
            doctorsService.update(doctorDto);
            update();
        });

        deleteLineButton.setOnAction(actionEvent -> {
            doctorsService.delete(table.getSelectionModel().getSelectedItem().getId());
            update();
        });
    }

    public void update() {
        ObservableList<DoctorDto> list = FXCollections.observableList(doctorsService.getDoctors());
        table.setItems(list);
    }
}
