package com.mongowikiplant.app.service;

import com.mongowikiplant.app.entity.Planta;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


import java.util.List;

@Service
public class ExportPlantasPDFService {

    public StreamingResponseBody exportToPDF(List<Planta> plantas) {
        return outputStream -> {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, outputStream);
                document.open();


                document.add(new Paragraph("Reporte de Plantas- WikiPlant", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
                document.add(new Paragraph(" ")); // 

     
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.addCell("Nombre Común");
                table.addCell("Nombre Científico");
                table.addCell("Familia");


                for (Planta planta : plantas) {
                    table.addCell(planta.getNomComun());
                    table.addCell(planta.getNomCientifico());
                    table.addCell(planta.getFamilia());
                }

                document.add(table);
            } catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                document.close();
            }
        };
    }
}
