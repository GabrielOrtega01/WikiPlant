package com.mongowikiplant.app.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.List;

import com.mongowikiplant.app.entity.Cliente;

@Service
public class ExportService {

    public StreamingResponseBody exportToExcel(List<Cliente> clientes) {
        return outputStream -> {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Clientes");

                // Crear encabezados
                Row headerRow = sheet.createRow(0);
                String[] headers = {
                    "Tipo Documento", "Numero de Documento", "Primer Nombre", 
                    "Primer Apellido", "Usuario", "Correo Electrónico"
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
                for (Cliente cliente : clientes) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(cliente.getTipoDocumento());
                    row.createCell(1).setCellValue(cliente.getNumeroDocumento());
                    row.createCell(2).setCellValue(cliente.getPrimerNombre());
                    row.createCell(3).setCellValue(cliente.getPrimerApellido());
                    row.createCell(4).setCellValue(cliente.getUsuario());
                    row.createCell(5).setCellValue(cliente.getCorreoElectronico());
                }

                // Ajustar el ancho de las columnas
                for (int i = 0; i < headers.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                workbook.write(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
