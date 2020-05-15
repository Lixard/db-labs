package ru.student.services.service.impl;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;
import ru.student.services.dto.AppointmentDto;
import ru.student.services.service.AppointmentsService;
import ru.student.services.service.ExportToWordService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Service
public class ExportToWordServiceImpl implements ExportToWordService  {

    private final XWPFDocument document;
    private final AppointmentsService service;
    private int rownum = 0;

    public ExportToWordServiceImpl(AppointmentsService service) {
        this.service = service;
        this.document = new XWPFDocument();
    }

    @Override
    public String toWord(Date date) {
        XWPFTable table = document.createTable();
        fillColumnNames(table);
        fillData(table, service.getAppointmentsWithDate(date));
        return saveToFile(new File(new File(System.getProperty("user.dir")), "exported.docx"));
    }

    private void fillColumnNames(XWPFTable table) {
        XWPFTableRow tableRow = table.getRow(0);
        tableRow.getCell(0).setText("patient's last name");
        tableRow.addNewTableCell().setText("doctor's last name");
        tableRow.addNewTableCell().setText("appointment place");
        tableRow.addNewTableCell().setText("appointment date");
    }

    private void fillData(XWPFTable table, List<AppointmentDto> data) {
        data.forEach(appointmentDto -> {
            rownum++;

            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText(appointmentDto.getPatientLastName());
            tableRow.getCell(1).setText(appointmentDto.getDoctorLastName());
            tableRow.getCell(2).setText(appointmentDto.getAppointmentPlace());
            tableRow.getCell(3).setText(appointmentDto.getAppointmentDate().toString());
        });
    }

    private String saveToFile(File file) {
        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            document.write(out);
            document.close();
            out.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
