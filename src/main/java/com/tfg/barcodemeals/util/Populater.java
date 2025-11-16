package com.tfg.barcodemeals.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.model.*;
import com.tfg.barcodemeals.repository.*;

//@Component
public class Populater implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final PrecioRepository precioRepository;
    private final PlatoRepository platoRepository;
    private final ComidaRepository comidaRepository;
    private final ReaccionAdversaRepository reaccionAdversaRepository;
    private final SupermercadoRepository supermercadoRepository;
    private final ListaCompraRepository listaCompraRepository;
    private final LineaCompraRepository lineaCompraRepository;
    private final CiudadRepository ciudadRepository;
    private final RegistroDiarioRepository registroDiarioRepository;

    public Populater(
            UsuarioRepository usuarioRepository,
            ProductoRepository productoRepository,
            PrecioRepository precioRepository,
            PlatoRepository platoRepository,
            ComidaRepository comidaRepository,
            ReaccionAdversaRepository reaccionAdversaRepository,
            SupermercadoRepository supermercadoRepository,
            ListaCompraRepository listaCompraRepository,
            LineaCompraRepository lineaCompraRepository,
            CiudadRepository ciudadRepository,
            RegistroDiarioRepository registroDiarioRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.precioRepository = precioRepository;
        this.platoRepository = platoRepository;
        this.comidaRepository = comidaRepository;
        this.reaccionAdversaRepository = reaccionAdversaRepository;
        this.supermercadoRepository = supermercadoRepository;
        this.listaCompraRepository = listaCompraRepository;
        this.lineaCompraRepository = lineaCompraRepository;
        this.ciudadRepository = ciudadRepository;
        this.registroDiarioRepository = registroDiarioRepository;
    }

    @Override
    public void run(String... args) {

        // --- Ciudades ---
        Ciudad madrid = new Ciudad();
        madrid.setNombre("Madrid");
        madrid.setProvincia("Madrid");
        madrid.setPais("España");
        madrid.setUsuarios(new ArrayList<>());
        madrid.setSupermercados(new ArrayList<>());

        Ciudad barcelona = new Ciudad();
        barcelona.setNombre("Barcelona");
        barcelona.setProvincia("Cataluña");
        barcelona.setPais("España");
        barcelona.setUsuarios(new ArrayList<>());
        barcelona.setSupermercados(new ArrayList<>());

        ciudadRepository.saveAll(List.of(madrid, barcelona));

        // --- Usuarios ---
        Usuario juan = new Usuario();
        juan.setNombreUsuario("juan123");
        juan.setContrasena("1234");
        juan.setApodo("Juan");
        juan.setEmail("juan@example.com");
        juan.setTelefono("600111222");
        juan.setFechaNacimiento(LocalDate.of(1990,5,20));
        juan.setGenero(Genero.MASCULINO);
        juan.setPeso(70);
        juan.setAltura(1.75);
        juan.setCiudad(madrid);
        juan.setRegistrosDiarios(new ArrayList<>());
        juan.setReaccionesAdversas(new ArrayList<>());
        usuarioRepository.save(juan);
        madrid.getUsuarios().add(juan);
        ciudadRepository.save(madrid);

        Usuario maria = new Usuario();
        maria.setNombreUsuario("maria456");
        maria.setContrasena("abcd");
        maria.setApodo("María");
        maria.setEmail("maria@example.com");
        maria.setTelefono("600333444");
        maria.setFechaNacimiento(LocalDate.of(1995,8,12));
        maria.setGenero(Genero.FEMENINO);
        maria.setPeso(60);
        maria.setAltura(1.65);
        maria.setCiudad(barcelona);
        maria.setRegistrosDiarios(new ArrayList<>());
        maria.setReaccionesAdversas(new ArrayList<>());
        usuarioRepository.save(maria);
        barcelona.getUsuarios().add(maria);
        ciudadRepository.save(barcelona);

        // --- Registro Diario ---
        RegistroDiario registroJuan = new RegistroDiario();
        registroJuan.setFecha(LocalDate.now());
        registroJuan.setKcalTotal(0);
        registroJuan.setGrasaTotal(0);
        registroJuan.setSaturadaTotal(0);
        registroJuan.setNoSaturadaTotal(0);
        registroJuan.setProteinaTotal(0);
        registroJuan.setHidratosCarbonoTotal(0);
        registroJuan.setAzucarTotal(0);
        registroJuan.setSalTotal(0);
        registroJuan.setFibraTotal(0);
        registroJuan.setUsuario(juan);
        registroJuan.setComidas(new ArrayList<>());
        registroDiarioRepository.save(registroJuan);
        juan.getRegistrosDiarios().add(registroJuan);
        usuarioRepository.save(juan);

        // --- Productos ---
        Producto leche = new Producto();
        leche.setNombre("Leche Entera");
        leche.setMarca("LaLechera");
        leche.setPlatos(new ArrayList<>());
        leche.setReaccionesAdversas(new ArrayList<>());
        productoRepository.save(leche);

        Producto pan = new Producto();
        pan.setNombre("Pan Integral");
        pan.setMarca("PanaderiaDelBarrio");
        pan.setPlatos(new ArrayList<>());
        pan.setReaccionesAdversas(new ArrayList<>());
        productoRepository.save(pan);

        // --- Reacciones Adversas ---
        ReaccionAdversa alergiaLactosa = new ReaccionAdversa();
        alergiaLactosa.setNombre("Alergia a la Lactosa");
        alergiaLactosa.setDescripcion("Reacción adversa al consumir productos lácteos.");
        alergiaLactosa.setUsuarios(new ArrayList<>());
        alergiaLactosa.setProductos(new ArrayList<>());
        reaccionAdversaRepository.save(alergiaLactosa);

        ReaccionAdversa intoleranciaGluten = new ReaccionAdversa();
        intoleranciaGluten.setNombre("Intolerancia al Gluten");
        intoleranciaGluten.setDescripcion("Reacción adversa al consumir gluten.");
        intoleranciaGluten.setUsuarios(new ArrayList<>());
        intoleranciaGluten.setProductos(new ArrayList<>());
        reaccionAdversaRepository.save(intoleranciaGluten);

        // --- Asociar reacciones a usuarios ---
        juan.getReaccionesAdversas().add(alergiaLactosa);
        alergiaLactosa.getUsuarios().add(juan);
        usuarioRepository.save(juan);
        reaccionAdversaRepository.save(alergiaLactosa);

        maria.getReaccionesAdversas().add(intoleranciaGluten);
        intoleranciaGluten.getUsuarios().add(maria);
        usuarioRepository.save(maria);
        reaccionAdversaRepository.save(intoleranciaGluten);

        // --- Asociar reacciones a productos ---
        leche.getReaccionesAdversas().add(alergiaLactosa);
        alergiaLactosa.getProductos().add(leche);
        productoRepository.save(leche);
        reaccionAdversaRepository.save(alergiaLactosa);

        pan.getReaccionesAdversas().add(intoleranciaGluten);
        intoleranciaGluten.getProductos().add(pan);
        productoRepository.save(pan);
        reaccionAdversaRepository.save(intoleranciaGluten);

        // --- Fin del populater ---
    }
}