package com.algasensors.temperature.processing.api.controller;

import com.algasensors.temperature.processing.api.model.TemperatureLogOutput;
import com.algasensors.temperature.processing.common.IdGenerator;
import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures/data")
public class TemperatureProcessingConroller {

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void processData(@PathVariable("sensorId") TSID sensorId, @RequestBody String data, @RequestHeader Map<String, Object> headers) {
        if (StringUtils.isBlank(data)) {
            log.error("Métrica de temperatura nula ou em branco");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        double temperature;

        try {
            temperature = Double.parseDouble(data);
        } catch (NumberFormatException ex) {
            log.error("Métrica de temperatura não é um número");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        var output = new TemperatureLogOutput(
                IdGenerator.generateTimeBasedUUID(),
                sensorId,
                OffsetDateTime.now(),
                temperature
        );
        
        log.info(output.toString());
    }
}
