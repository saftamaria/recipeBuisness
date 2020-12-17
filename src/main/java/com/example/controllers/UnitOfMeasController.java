package com.example.controllers;

import com.example.commands.UnitOfMeasurementCommand;
import com.example.services.UnitOfMeasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/api")
public class UnitOfMeasController {

    //private static final Logger log = LoggerFactory.getLogger(UnitOfMeasController.class);
    private final UnitOfMeasService unitOfMeasService;

    public UnitOfMeasController(UnitOfMeasService unitOfMeasService) {
        this.unitOfMeasService = unitOfMeasService;
    }

    @GetMapping("/uoms")
    public ResponseEntity<Set<UnitOfMeasurementCommand>> unitOfMeasurements() {
        Set<UnitOfMeasurementCommand> uom = unitOfMeasService.listOfUom();
        log.info("success");
        return ResponseEntity.ok(uom);
    }
}
