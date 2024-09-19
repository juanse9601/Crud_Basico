package com.ApiRestBasica.Ejemplo.controlador;


import com.ApiRestBasica.Ejemplo.modelo.Persona;
import com.ApiRestBasica.Ejemplo.servicio.PersonaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/personas")
public class PersonaController {

    ArrayList<Persona> personas = new ArrayList<>();

    PersonaServicio service = new PersonaServicio();

    //mETODOS HTTP
    // GET = READ LECTURA O VISUALIZACION DEDATOS
    //POST = CREATED CREACION
    //PUT = ACTUALIZACION O MODIFICACION
    // DELELE = ELIMINACION


    @GetMapping()
    public ResponseEntity<List<Persona>> listarPersonas(){
        try {
                List<Persona> personas = this.service.listarPersonas();
                if (personas.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(personas, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("{cedula}")
    public ResponseEntity<Persona> obtenerPersonaPorCedula(@PathVariable("cedula") String cedula){
        try {
            Optional<Persona> persona = this.service.obtenerPersonaPorCedula(cedula);
            return persona.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
