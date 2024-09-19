package com.ApiRestBasica.Ejemplo.servicio;


import com.ApiRestBasica.Ejemplo.modelo.Persona;

import java.util.ArrayList;
import java.util.Optional;

public class PersonaServicio {
    //Crud
    //C = created = creacion
    //r = read    = lectura o visualizacionde los datos
    //u = update  = actualizacion
    //d = delete  =  eliminacion

    //mETODOS HTTP
    // GET = READ LECTURA O VISUALIZACION DEDATOS
    //POST = CREATED CREACION
    //PUT = ACTUALIZACION O MODIFICACION
    // DELELE = ELIMINACION

    //Creacion de lista para simular labase de datos
    ArrayList<Persona> personas = new ArrayList<>();

    //
    //crear un metodo para listar todas las personas
    public ArrayList<Persona> listarPersonas(){
        return this.personas;
    }

    //Obtener una persona por su cedula
    public Optional<Persona> obtenerPersonaPorCedula(String cedula){
        return this.personas
                .stream()
                .filter(p -> p.getDocumento().equals(cedula))
                .findFirst();

    }

    //metodo para la creacion de personas
    public Persona crearPersona(Persona persona){
        this.personas.add(persona);
        return persona;
    }

    //METODO DE ACTUALIZACION
    public Persona modificarPersona(Persona persona){
       Persona personaModificar = this.personas.stream()
               .filter(persona1 -> persona1.getDocumento().equals(persona.getDocumento()))
               .findFirst().get();

       personaModificar.setNombre(persona.getNombre());
       personaModificar.setApellido(persona.getApellido());
       personaModificar.setCiudad(persona.getCiudad());

       return personaModificar;
    }

    //Metodo de eliminacion
    public Persona eliminarPersona(String cedula){
       Optional<Persona> personaEliminar = obtenerPersonaPorCedula(cedula);

       personaEliminar.ifPresent(persona -> this.personas.remove(persona));
       return personaEliminar.get();
    }

}
