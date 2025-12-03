package com.tfg.barcodemeals.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.barcodemeals.model.CategoriaProducto;
import com.tfg.barcodemeals.model.Ciudad;
import com.tfg.barcodemeals.model.Comida; 
import com.tfg.barcodemeals.model.Envase;
import com.tfg.barcodemeals.model.Plato;
import com.tfg.barcodemeals.model.Producto;
import com.tfg.barcodemeals.model.ReaccionAdversa;
import com.tfg.barcodemeals.model.RegistroDiario; 
import com.tfg.barcodemeals.model.TipoComida; 
import com.tfg.barcodemeals.model.TipoReaccion;
import com.tfg.barcodemeals.model.UnidadMedida;
import com.tfg.barcodemeals.model.Usuario; 
import com.tfg.barcodemeals.repository.CiudadRepository;
import com.tfg.barcodemeals.repository.ComidaRepository; 
import com.tfg.barcodemeals.repository.PlatoRepository;
import com.tfg.barcodemeals.repository.ProductoRepository;
import com.tfg.barcodemeals.repository.ReaccionAdversaRepository;
import com.tfg.barcodemeals.repository.RegistroDiarioRepository; 
import com.tfg.barcodemeals.repository.UsuarioRepository; 
import com.tfg.barcodemeals.model.Genero; 

@Component
public class Populater implements CommandLineRunner {

    private final ReaccionAdversaRepository reaccionAdversaRepository;
    private final CiudadRepository ciudadRepository;
    private final ProductoRepository productoRepository;
    private final PlatoRepository platoRepository;
    private final ComidaRepository comidaRepository;
    private final RegistroDiarioRepository registroDiarioRepository;
    private final UsuarioRepository usuarioRepository;
    
    public Populater(
            ReaccionAdversaRepository reaccionAdversaRepository, 
            CiudadRepository ciudadRepository,
            ProductoRepository productoRepository,
            PlatoRepository platoRepository,
            ComidaRepository comidaRepository, 
            RegistroDiarioRepository registroDiarioRepository, 
            UsuarioRepository usuarioRepository 
    ) {
    	this.reaccionAdversaRepository = reaccionAdversaRepository;      
    	this.ciudadRepository = ciudadRepository;
    	this.productoRepository = productoRepository;
    	this.platoRepository = platoRepository;
        this.comidaRepository = comidaRepository; 
        this.registroDiarioRepository = registroDiarioRepository; 
        this.usuarioRepository = usuarioRepository; 
    }

    @Override
    @Transactional
    public void run(String... args) {
        // --- LIMPIEZA TOTAL EN ORDEN INVERSO DE DEPENDENCIA ---
        comidaRepository.deleteAll();
        registroDiarioRepository.deleteAll();
        usuarioRepository.deleteAll();
        platoRepository.deleteAll();
        productoRepository.deleteAll();
        reaccionAdversaRepository.deleteAll(); 
        ciudadRepository.deleteAll();
        // ---------------------------------------------------
        
        // --- CREACIÓN EN ORDEN DE DEPENDENCIA ---
        crearReaccionesAdversas();
    	crearCiudades();           
    	crearProductos();          
    	crearPlatos();
        crearComidasYRegistros(); 
    }

    // =======================================================================
    // --- FUNCIÓN DE CREACIÓN DE COMIDAS Y REGISTROS CORREGIDA Y MEJORADA ---
    // =======================================================================

    private void crearComidasYRegistros() {
        
        // 1. Crear Usuario Base
        Ciudad ciudadBase = ciudadRepository.findAll().stream().findFirst().orElse(null);
        Usuario usuarioBase = new Usuario(
            null, "demoUser", "password", "Demo", "demo@tfg.com", "600123456", 
            LocalDate.of(1990, 1, 1), Genero.MASCULINO, 75.0, 180.0, 
            35, ciudadBase, new ArrayList<>(), new ArrayList<>(), new ArrayList<>() 
        );
        usuarioBase = usuarioRepository.save(usuarioBase);

        // 2. Crear dos Registros Diarios
        RegistroDiario registroDiarioHoy = new RegistroDiario(
            null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
            LocalDate.now(), false, usuarioBase, new ArrayList<>() 
        );
        RegistroDiario registroDiarioAyer = new RegistroDiario(
            null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
            LocalDate.now().minusDays(1), false, usuarioBase, new ArrayList<>() 
        );
        
        // 3. Obtener Componentes necesarios
        Optional<Plato> desayunoPlatoOpt = platoRepository.findByNombre("Desayuno Completo");
        Optional<Plato> cenaPlatoOpt = platoRepository.findByNombre("Cena de Pollo");
        
        Optional<Producto> lecheOpt = productoRepository.findByBarcode("8412345678905");
        Optional<Producto> panOpt = productoRepository.findByBarcode("8455544433321");
        Optional<Producto> aceiteOpt = productoRepository.findByBarcode("8437000371004");
        
        if (desayunoPlatoOpt.isEmpty() || cenaPlatoOpt.isEmpty() || lecheOpt.isEmpty() || panOpt.isEmpty() || aceiteOpt.isEmpty()) {
            System.err.println("Advertencia: No se encontraron todos los Platos/Productos necesarios.");
            return;
        }

        Plato desayunoPlato = desayunoPlatoOpt.get();
        Plato cenaPlato = cenaPlatoOpt.get();
        Producto lecheProducto = lecheOpt.get();
        Producto panProducto = panOpt.get();
        Producto aceiteProducto = aceiteOpt.get();

        // 4. Comida de DESAYUNO: 2 Platos y 2 Productos
        Comida desayuno = new Comida(
            null, 
            TipoComida.DESAYUNO, 
            LocalDate.now(), 
            registroDiarioHoy, 
            List.of(desayunoPlato, cenaPlato), // 2 Platos
            List.of(lecheProducto, panProducto) // 2 Productos
        );
        
        // 5. Comida de CENA: 1 Plato y 1 Producto
        Comida cena = new Comida(
            null, 
            TipoComida.CENA, 
            LocalDate.now(), 
            registroDiarioHoy, 
            List.of(cenaPlato), // 1 Plato
            List.of(aceiteProducto) // 1 Producto
        );
        
        // 6. Vincular y Guardar Registros
        registroDiarioHoy.getComidas().add(desayuno); 
        registroDiarioHoy.getComidas().add(cena); 
        
        registroDiarioHoy.recalcularTotales(); 
        registroDiarioRepository.save(registroDiarioHoy); 
        
        registroDiarioRepository.save(registroDiarioAyer); // Guardar el registro vacío.
    }
    
    // =======================================================================
    // --- MÉTODOS AUXILIARES ---
    // =======================================================================
    
    private void crearReaccionesAdversas() {
        for (TipoReaccion tipo : TipoReaccion.values()) {
            Optional<ReaccionAdversa> existing = reaccionAdversaRepository.findByTipo(tipo);
            if (existing.isEmpty()) {
                ReaccionAdversa ra = new ReaccionAdversa();
                ra.setTipo(tipo);
                ra.setDescripcion(tipo.toString()); 
                ra.setUsuarios(new ArrayList<>()); 
                ra.setProductos(new ArrayList<>());
                reaccionAdversaRepository.save(ra); 
            }
        }
    }
    
    private void crearPlatos() {
        Optional<Producto> lecheOpt = productoRepository.findByBarcode("8412345678905");
        Optional<Producto> polloOpt = productoRepository.findByBarcode("2000000000018");
        Optional<Producto> panOpt = productoRepository.findByBarcode("8455544433321");
        
        if (lecheOpt.isEmpty() || polloOpt.isEmpty() || panOpt.isEmpty()) {
            return;
        }

        Producto leche = lecheOpt.get();
        Producto pollo = polloOpt.get();
        Producto pan = panOpt.get();

        List<Producto> ingredientesDesayuno = List.of(leche, pan);
        Plato desayuno = new Plato(null, "Desayuno Completo", "Tazón de leche y pan.", ingredientesDesayuno);
        
        List<Producto> ingredientesAlmuerzo = List.of(pollo);
        Plato almuerzo = new Plato(null, "Almuerzo Proteico", "Pechuga de pollo a la plancha.", ingredientesAlmuerzo);

        // Nuevo plato
        List<Producto> ingredientesCena = List.of(pollo);
        Plato cena = new Plato(null, "Cena de Pollo", "Pechuga de pollo a la plancha con hierbas.", ingredientesCena);
        
        List<Plato> platosToCreate = List.of(desayuno, almuerzo, cena); 
        
        for (Plato nuevoPlato : platosToCreate) {
             platoRepository.save(nuevoPlato);
        }
    }
    
    private void crearProductos() {
    	ReaccionAdversa lactosa = reaccionAdversaRepository.findByTipo(TipoReaccion.INTOLERANCIA_LACTOSA).orElse(null);
        ReaccionAdversa trigo = reaccionAdversaRepository.findByTipo(TipoReaccion.ALERGIA_TRIGO).orElse(null);
        ReaccionAdversa huevo = reaccionAdversaRepository.findByTipo(TipoReaccion.ALERGIA_HUEVO).orElse(null);
        List<ReaccionAdversa> alergenosPan = new ArrayList<>();
        if (trigo != null) alergenosPan.add(trigo);
        if (huevo != null) alergenosPan.add(huevo);
        
        List<ReaccionAdversa> alergenosLeche = lactosa != null ? List.of(lactosa) : new ArrayList<>();
        
        Producto leche = crearProductoData("8412345678905", "Leche Entera UHT", "Marca Blanca", CategoriaProducto.LACTEOS, 1000.0, 250.0, 60.0, 3.2, 1.8, 1.4, 4.7, 4.7, 3.1, 0.1, 0.0, UnidadMedida.MILILITROS, Envase.CARTÓN, alergenosLeche); 
        
        Producto pollo = crearProductoData("2000000000018", "Pechuga de Pollo Fresca", "Avícola Premium", CategoriaProducto.CARNE, 500.0, 150.0, 165.0, 3.6, 1.0, 2.6, 0.0, 0.0, 31.0, 0.4, 0.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);
        
        Producto pan = crearProductoData("8455544433321", "Pan Integral Masa Madre", "Panadería Rústica", CategoriaProducto.PAN_Y_BOLLERIA, 400.0, 100.0, 230.0, 3.0, 0.5, 2.5, 45.0, 3.0, 10.0, 0.5, 6.5, UnidadMedida.GRAMOS, Envase.PLÁSTICO, alergenosPan );
        
        // Nuevo producto
        Producto aceite = crearProductoData("8437000371004", "Aceite de Oliva Virgen Extra", "Oleum", CategoriaProducto.ACEITES_Y_GRASAS, 500.0, 10.0, 900.0, 100.0, 15.0, 85.0, 0.0, 0.0, 0.0, 0.0, 0.0, UnidadMedida.MILILITROS, Envase.PLÁSTICO, null);

        List<Producto> productosToCreate = List.of(leche, pollo, pan, aceite); // Añadido
        
        for (Producto nuevoProducto : productosToCreate) {
             productoRepository.save(nuevoProducto);
        }
    }
    
    private void crearCiudades() {
        List<Ciudad> ciudadesToCreate = List.of(
        	crearCiudadData("Mérida", "Badajoz", "España", "06800"),
        	crearCiudadData("Gijón", "Asturias", "España", "33200"),
            crearCiudadData("Elche", "Alicante", "España", "03200"),
            crearCiudadData("Vigo", "Pontevedra", "España", "36200"),
            crearCiudadData("Jerez de la Frontera", "Cádiz", "España", "11400")
        );
        
        for (Ciudad nuevaCiudad : ciudadesToCreate) {
            ciudadRepository.save(nuevaCiudad);
        }
    }

    private Ciudad crearCiudadData(String nombre, String provincia, String pais, String codigoPostal) {
        return new Ciudad(null, nombre, provincia, pais, codigoPostal, new ArrayList<>(), new ArrayList<>());
    }
    
    private Producto crearProductoData(String barcode, String nombre, String marca, CategoriaProducto categoria,
            double pesoEmpaque, double pesoConsumido, double kcal, double grasa,
            double saturada, double noSaturada, double hidratosCarbono, double azucar,
            double proteina, double sal, double fibra, UnidadMedida unidad, Envase envase, List<ReaccionAdversa> reacciones) {

    		return new Producto(null, barcode, nombre, marca, categoria, pesoEmpaque, pesoConsumido, 
    		kcal, grasa, saturada, noSaturada, hidratosCarbono, azucar, 
    		proteina, sal, fibra, unidad, envase, 
    		reacciones, null, null, null); 
    }
}