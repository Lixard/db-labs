package ru.student.services.service.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.student.services.dto.AppointmentDto;
import ru.student.services.service.AppointmentsService;
import ru.student.services.service.ExportToExcelService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportToExcelServiceImpl implements ExportToExcelService {

    private final AppointmentsService service;
    private final XSSFWorkbook workbook;
    private int rownum = 0;


    @Autowired
    public ExportToExcelServiceImpl(AppointmentsService service) {
        this.service = service;
        workbook = new XSSFWorkbook();
    }

    @Override
    public String toExcel() {
        List<AppointmentDto> list = service.getAppointments();
        XSSFSheet sheet = workbook.createSheet("export");
        fillColumnNames(sheet);
        fillData(sheet, list);
        File path = new File(System.getProperty("user.dir"));
        File file = new File(path, "exported.xlsx");
        return saveToFile(file);
    }

    private void fillColumnNames(XSSFSheet sheet) {
        Row row;
        row = sheet.createRow(rownum);

        createCell(row, 0, "Patient's last name");
        createCell(row, 1, "Doctor's last name");
        createCell(row, 2, "Appointment place");
        createCell(row, 3, "Appointment date");
    }

    private void fillData(XSSFSheet sheet, List<AppointmentDto> data) {
        data.forEach(appointmentDto -> {
            rownum++;

            Row row = sheet.createRow(rownum);

            createCell(row, 0, appointmentDto.getPatientLastName());
            createCell(row, 1, appointmentDto.getDoctorLastName());
            createCell(row, 2, appointmentDto.getAppointmentPlace());
            createCell(row, 3, appointmentDto.getAppointmentDate().toString());
        });
    }

    private void createCell(Row row, int column, String data) {
        Cell cell = row.createCell(column, CellType.STRING);
        cell.setCellValue(data);
    }

    private String saveToFile(File file) {
        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            workbook.close();
            out.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
