package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Cliente;
import com.mongowikiplant.app.entity.Fotoperiodo;
import com.mongowikiplant.app.entity.Planta;
import com.mongowikiplant.app.repository.ClienteRepository;
import com.mongowikiplant.app.repository.FotoperiodoRepository;
import com.mongowikiplant.app.repository.PlantaRepository;
import com.mongowikiplant.app.service.ExportPlantasExcelService;
import com.mongowikiplant.app.service.ExportPlantasPDFService;
import com.mongowikiplant.app.service.ExportService;
import com.mongowikiplant.app.service.PDFExportService;
import com.mongowikiplant.app.service.ExportFotoperiodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @Autowired
    private PDFExportService pdfExportService;

    @Autowired
    private ExportFotoperiodoService exportFotoperiodoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FotoperiodoRepository fotoperiodoRepository;
    
    @Autowired
    private ExportPlantasExcelService exportPlantasExcelService;

    @Autowired
    private ExportPlantasPDFService exportPlantasPDFService;
   

    @Autowired
    private PlantaRepository plantaRepository; 

    @GetMapping("/excel")
    public ResponseEntity<StreamingResponseBody> exportToExcel() {
        List<Cliente> clientes = clienteRepository.findAll();
        StreamingResponseBody stream = exportService.exportToExcel(clientes);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=clientes.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(stream);
    }

    @GetMapping("/pdf")
    public ResponseEntity<StreamingResponseBody> exportToPDF() {
        List<Cliente> clientes = clienteRepository.findAll();
        StreamingResponseBody stream = pdfExportService.exportToPDF(clientes);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=clientes.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(stream);
    }

    @GetMapping("/fotoperiodo/excel")
    public ResponseEntity<StreamingResponseBody> exportFotoperiodoToExcel() {
        List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findAll();
        StreamingResponseBody stream = exportFotoperiodoService.exportToExcel(fotoperiodos);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=fotoperiodos.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(stream);
    }
    
    @GetMapping("/plantas/excel")
    public ResponseEntity<StreamingResponseBody> exportPlantasToExcel() {
        List<Planta> plantas = plantaRepository.findAll();
        StreamingResponseBody stream = exportPlantasExcelService.exportToExcel(plantas);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=plantas.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(stream);
    }

    @GetMapping("/plantas/pdf")
    public ResponseEntity<StreamingResponseBody> exportPlantasToPDF() {
        List<Planta> plantas = plantaRepository.findAll();
        StreamingResponseBody stream = exportPlantasPDFService.exportToPDF(plantas);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=plantas.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(stream);
    }
    
    
    
    
    
    
    
    
    
    
}


