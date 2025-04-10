package com.proyecto.crud.service;

import com.proyecto.crud.model.Medicine;
import com.proyecto.crud.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    private List<Medicine> medicines = new ArrayList<>();
    private Long nextId = 1L;

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public Optional<Medicine> getMedicineById(Long id){
        return medicines.stream().filter(medicine -> medicine.getId().equals(id)).findFirst();
    }

    public Medicine createMedicine(Medicine medicine){
        medicine.setId(nextId++);
        medicines.add(medicine);
        return medicine;
    }

    public Optional<Medicine> updateMedicine(Long id, Medicine medicineDetails){
        return getMedicineById(id).map(medicine ->{
            medicine.setNombre(medicineDetails.getNombre());
            medicine.setPrincipo_activo(medicineDetails.getPrincipo_activo());
            medicine.setPresentaion(medicineDetails.getPresentaion());
            medicine.setLaboratorio(medicineDetails.getLaboratorio());
            medicine.setCantidad(medicineDetails.getCantidad());
            medicine.setPrecio(medicineDetails.getPrecio());
            return medicine;
        });
    }

    public boolean deleteMedicine(Long id){
        return medicines.removeIf(medicine -> medicine.getId().equals(id));
    }

    public List<String> stockBajo10() {
        List<String> reporte = medicines.stream()
                .filter(m -> m.getCantidad() < 10)
                .map(Medicine::mostrarInformacionReporte01)
                .toList();

        return reporte;
    }


    public List<String> stockPorLaboratorio() {
        List<String> reporte = medicines.stream()
                .collect(Collectors.groupingBy(
                        Medicine::getLaboratorio,
                        Collectors.summarizingInt(Medicine::getCantidad)
                ))
                .entrySet().stream()
                .map(entry -> entry.getKey() + " - cantidad: " + entry.getValue().getSum())
                .collect(Collectors.toList());

        return reporte;
    }

}
