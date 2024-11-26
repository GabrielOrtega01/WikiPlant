package com.mongowikiplant.app.service;

import com.mongowikiplant.app.entity.Fotoperiodo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


import java.util.List;

@Service
public class ExportFotoperiodoService {

    public StreamingResponseBody exportToExcel(List<Fotoperiodo> fotoperiodos) {
        return outputStream -> {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Fotoperiodos");


                Row headerRow = sheet.createRow(0);
                String[] columnHeaders = {"Fecha", "Estación", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Promedio Anual"};
                for (int i = 0; i < columnHeaders.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columnHeaders[i]);
                    cell.setCellStyle(getHeaderCellStyle(workbook));
                }


                int rowNum = 1;
                for (Fotoperiodo fotoperiodo : fotoperiodos) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(fotoperiodo.getFecha());
                    row.createCell(1).setCellValue(fotoperiodo.getEstacion().getNombre());
                    row.createCell(2).setCellValue(fotoperiodo.getEnero());
                    row.createCell(3).setCellValue(fotoperiodo.getFebrero());
                    row.createCell(4).setCellValue(fotoperiodo.getMarzo());
                    row.createCell(5).setCellValue(fotoperiodo.getAbril());
                    row.createCell(6).setCellValue(fotoperiodo.getMayo());
                    row.createCell(7).setCellValue(fotoperiodo.getJunio());
                    row.createCell(8).setCellValue(fotoperiodo.getJulio());
                    row.createCell(9).setCellValue(fotoperiodo.getAgosto());
                    row.createCell(10).setCellValue(fotoperiodo.getSeptiembre());
                    row.createCell(11).setCellValue(fotoperiodo.getOctubre());
                    row.createCell(12).setCellValue(fotoperiodo.getNoviembre());
                    row.createCell(13).setCellValue(fotoperiodo.getDiciembre());
                    row.createCell(14).setCellValue(fotoperiodo.calcularPromedioAnual());
                }


                for (int i = 0; i < columnHeaders.length; i++) {
                    sheet.autoSizeColumn(i);
                }


                workbook.write(outputStream);
            }
        };
    }

    private CellStyle getHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}
