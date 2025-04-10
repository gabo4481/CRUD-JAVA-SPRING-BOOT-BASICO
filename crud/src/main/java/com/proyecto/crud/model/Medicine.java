package com.proyecto.crud.model;

import com.proyecto.crud.informacion.Informacion;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicine implements Informacion {
    private Long id;
    private String nombre;
    private String principo_activo;
    private String presentaion;
    private String laboratorio;
    private int cantidad;
    private float precio;

    @Override
    public void mostrarInformacion() {
        System.out.println("Medicamento ID: " + getId() + ", Nombre: " + getNombre() + ", Principio Activo: " + getPrincipo_activo() +
                ", Cantidad: " + getCantidad() + ", Laboratorio: " + getLaboratorio() + ", Precio: " + getPrecio() );
    }

    @Override
    public String mostrarInformacionReporte01(){
        return getId()+". "+getNombre()+" - cantidad: "+getCantidad();
    }
}

