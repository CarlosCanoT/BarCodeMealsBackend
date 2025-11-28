package com.tfg.barcodemeals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudController<Rs, Rq> {

    @GetMapping
    List<Rs> obtenerTodos();

    @GetMapping("/{id}")
    ResponseEntity<Rs> obtenerPorId(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Rs> crear(@RequestBody Rq request);

    @PutMapping("/{id}")
    ResponseEntity<Rs> actualizar(@PathVariable Long id, @RequestBody Rq request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminar(@PathVariable Long id);
}
