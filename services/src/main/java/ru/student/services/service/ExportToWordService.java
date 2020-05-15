package ru.student.services.service;

import java.sql.Date;

public interface ExportToWordService {
    String toWord(Date date);
    String toWord();
}
