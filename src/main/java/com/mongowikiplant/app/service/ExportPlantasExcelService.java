package com.mongowikiplant.app.service;

import com.mongowikiplant.app.entity.Planta;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@Service
public class ExportPlantasExcelService {

    public StreamingResponseBody exportToExcel(List<Planta> plantas) {
        return outputStream -> {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Plantas");

                Row headerRow = sheet.createRow(0);
                String[] headers = {
                    "Nombre Científico", "Nombre Común", "Familia", "Origen", "Distribución", "Adaptación", "Altitud"
                };
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    CellStyle style = workbook.createCellStyle();
                    Font font = workbook.createFont();
                    font.setBold(true);
                    style.setFont(font);
                    cell.setCellStyle(style);
                }

                // Llenar datos
                int rowNum = 1;
                for (Planta planta : plantas) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(planta.getNomCientifico());
                    row.createCell(1).setCellValue(planta.getNomComun());
                    row.createCell(2).setCellValue(planta.getFamilia());
                    row.createCell(3).setCellValue(planta.getOrigen());
                    row.createCell(4).setCellValue(planta.getDistribucion());
                    row.createCell(5).setCellValue(planta.getAdaptacion());
                    row.createCell(6).setCellValue(planta.getAltitud());
                }

                // Ajustar el ancho de las columnas
                for (int i = 0; i < headers.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                workbook.write(outputStream);
            }
        };
    }
}
