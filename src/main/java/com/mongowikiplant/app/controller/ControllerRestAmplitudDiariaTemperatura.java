package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.AmplitudDiariaTemperatura;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.AmplitudDiariaTemperaturaRepository;

@RestController
@RequestMapping("/amplitudesDiariasTemperaturas")
public class ControllerRestAmplitudDiariaTemperatura {

    @Autowired
    private AmplitudDiariaTemperaturaRepository amplitudDiariaTemperaturaRepository;

    @GetMapping
    public List<AmplitudDiariaTemperatura> listarAmplitudesDiariasTemperaturas() {
        return amplitudDiariaTemperaturaRepository.findAll();
    }

    @GetMapping("/{id}")
    public AmplitudDiariaTemperatura getAmplitudDiariaTemperaturaById(@PathVariable String id) {
        return amplitudDiariaTemperaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("AmplitudDiariaTemperatura no encontrado"));
    }

    @PostMapping
    public AmplitudDiariaTemperatura crearAmplitudDiariaTemperatura(@RequestBody AmplitudDiariaTemperatura amplitudDiariaTemperatura) {
        return amplitudDiariaTemperaturaRepository.save(amplitudDiariaTemperatura);
    }

    @PostMapping("/map")
    public AmplitudDiariaTemperatura saveAmplitudDiariaTemperaturaFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        AmplitudDiariaTemperatura amplitudDiariaTemperatura = mapper.convertValue(body, AmplitudDiariaTemperatura.class);
        return amplitudDiariaTemperaturaRepository.save(amplitudDiariaTemperatura);
    }

    @PutMapping("/{id}")
    public AmplitudDiariaTemperatura actualizarAmplitudDiariaTemperatura(@PathVariable String id, @RequestBody AmplitudDiariaTemperatura amplitudDiariaTemperaturaActualizada) {
        amplitudDiariaTemperaturaActualizada.setId(id);
        return amplitudDiariaTemperaturaRepository.save(amplitudDiariaTemperaturaActualizada);
    }

    @PutMapping("/map/{id}")
    public AmplitudDiariaTemperatura updateAmplitudDiariaTemperaturaFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        AmplitudDiariaTemperatura amplitudDiariaTemperatura = mapper.convertValue(body, AmplitudDiariaTemperatura.class);
        amplitudDiariaTemperatura.setId(id);
        return amplitudDiariaTemperaturaRepository.save(amplitudDiariaTemperatura);
    }

    
	@DeleteMapping("/{id}")
	public AmplitudDiariaTemperatura deleteAmplitudDiariaTemperatura(@PathVariable String id) {
		AmplitudDiariaTemperatura amplitudDiariaTemperatura = amplitudDiariaTemperaturaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("AmplitudDiariaTemperatura no encontrado"));
		amplitudDiariaTemperaturaRepository.deleteById(id);
		return amplitudDiariaTemperatura;
	}

}
