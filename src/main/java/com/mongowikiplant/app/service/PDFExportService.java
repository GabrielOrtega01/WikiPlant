package com.mongowikiplant.app.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.util.List;

import com.mongowikiplant.app.entity.Cliente;

@Service
public class PDFExportService {

    public StreamingResponseBody exportToPDF(List<Cliente> clientes) {
        return outputStream -> {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, outputStream);
                document.open();


                document.add(new Phrase("Reporte de Clientes WikiPlant\n\n"));


                for (Cliente cliente : clientes) {
                    document.add(new Phrase("Documento: " + cliente.getTipoDocumento() + " " + cliente.getNumeroDocumento() + "\n"));
                    document.add(new Phrase("Nombre: " + cliente.getPrimerNombre() + " " + cliente.getPrimerApellido() + "\n"));
                    document.add(new Phrase("Usuario: " + cliente.getUsuario() + "\n"));
                    document.add(new Phrase("Correo: " + cliente.getCorreoElectronico() + "\n"));
                    document.add(new Phrase("Telefono: " + cliente.getNumeroTelefonico() + "\n\n"));

                }

            } catch (DocumentException e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
        };
    }
}
