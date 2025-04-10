package com.proyecto.crud.controller;


import com.proyecto.crud.model.Medicine;
import com.proyecto.crud.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MedicineController {

    private final MedicineService medicineService;


    @Autowired
    public MedicineController(MedicineService medicineService) { this.medicineService = medicineService;
    }

    @GetMapping
    public List<Medicine> getAllMedicines(){
        return medicineService.getMedicines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id){
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        return medicine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineService.createMedicine(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicineDetails){
        Optional<Medicine> medicine = medicineService.updateMedicine(id, medicineDetails);
        return medicine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id){
        if (medicineService.deleteMedicine(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/report/stock")
    public List<String> getStockBajo10() {
        return medicineService.stockBajo10();
    }

    @GetMapping("/report/laboratorio")
    public List<String> getStockPorLaboratorio() {
        return medicineService.stockPorLaboratorio();
    }
}
