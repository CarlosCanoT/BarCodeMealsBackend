package com.tfg.barcodemeals.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.barcodemeals.model.CategoriaProducto;
import com.tfg.barcodemeals.model.Ciudad;
import com.tfg.barcodemeals.model.Envase;
import com.tfg.barcodemeals.model.Genero;
import com.tfg.barcodemeals.model.LineaCompra;
import com.tfg.barcodemeals.model.ListaCompra;
import com.tfg.barcodemeals.model.ObjetivoDiario;
import com.tfg.barcodemeals.model.Plato;
import com.tfg.barcodemeals.model.Producto;
import com.tfg.barcodemeals.model.ReaccionAdversa;
import com.tfg.barcodemeals.model.RegistroDiario;
import com.tfg.barcodemeals.model.Supermercado;
import com.tfg.barcodemeals.model.UnidadMedida;
import com.tfg.barcodemeals.model.Usuario;
import com.tfg.barcodemeals.repository.CiudadRepository;
import com.tfg.barcodemeals.repository.ComidaRepository;
import com.tfg.barcodemeals.repository.LineaCompraRepository;
import com.tfg.barcodemeals.repository.ListaCompraRepository;
import com.tfg.barcodemeals.repository.ObjetivoDiarioRepository;
import com.tfg.barcodemeals.repository.PlatoRepository;
import com.tfg.barcodemeals.repository.ProductoRepository;
import com.tfg.barcodemeals.repository.ReaccionAdversaRepository;
import com.tfg.barcodemeals.repository.RegistroDiarioRepository;
import com.tfg.barcodemeals.repository.SupermercadoRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository; 

@Component
public class Populater implements CommandLineRunner {

    private final ReaccionAdversaRepository reaccionAdversaRepository;
    private final CiudadRepository ciudadRepository;
    private final ProductoRepository productoRepository;
    private final PlatoRepository platoRepository;
    private final ComidaRepository comidaRepository;
    private final RegistroDiarioRepository registroDiarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final SupermercadoRepository supermercadoRepository;
    private final ListaCompraRepository listaCompraRepository;
    private final ObjetivoDiarioRepository objetivoDiarioRepository;
    
    
    public Populater(
            ReaccionAdversaRepository reaccionAdversaRepository, 
            CiudadRepository ciudadRepository,
            ProductoRepository productoRepository,
            PlatoRepository platoRepository,
            ComidaRepository comidaRepository, 
            RegistroDiarioRepository registroDiarioRepository, 
            UsuarioRepository usuarioRepository,
            SupermercadoRepository supermercadoRepository,
            LineaCompraRepository lineaCompraRepository,
            ListaCompraRepository listaCompraRepository,
            ObjetivoDiarioRepository objetivoDiarioRepository
            
    ) {
    	this.reaccionAdversaRepository = reaccionAdversaRepository;      
    	this.ciudadRepository = ciudadRepository;
    	this.productoRepository = productoRepository;
    	this.platoRepository = platoRepository;
        this.comidaRepository = comidaRepository; 
        this.registroDiarioRepository = registroDiarioRepository; 
        this.usuarioRepository = usuarioRepository; 
        this.supermercadoRepository = supermercadoRepository;
        this.listaCompraRepository = listaCompraRepository;
        this.objetivoDiarioRepository = objetivoDiarioRepository;
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
        supermercadoRepository.deleteAll();
        ciudadRepository.deleteAll();
        listaCompraRepository.deleteAll();
        // ---------------------------------------------------
        
        // --- CREACIÓN EN ORDEN DE DEPENDENCIA ---
        crearReaccionesAdversas();
    	crearCiudades();   
    	crearUsuarios();
//    	crearProductos();          
    	crearPlatos();
//      crearComidasYRegistros(); 
        crearSupermercados();
        crearListasCompra();
    }

    // =======================================================================
    // --- FUNCIÓN DE CREACIÓN DE COMIDAS Y REGISTROS CORREGIDA Y MEJORADA ---
    // =======================================================================

//    private void crearComidasYRegistros() {
//        
//        // 1. Crear Usuario Base
//        Ciudad ciudadBase = ciudadRepository.findAll().stream().findFirst().orElse(null);
//        Usuario usuarioBase = new Usuario(
//            null, "dmo", "pass", "Demo", "demo@tfg.com", "600123456", 
//            LocalDate.of(1990, 1, 1), Genero.MASCULINO, 75.0, 180.0, ciudadBase, new ArrayList<>(), new ArrayList<>(), new ArrayList<>() 
//        );
//        usuarioBase = usuarioRepository.save(usuarioBase);
//
//        // 2. Crear dos Registros Diarios
//        RegistroDiario registroDiarioHoy = new RegistroDiario(
//            null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
//            LocalDate.now(), false, usuarioBase, new ArrayList<>() 
//        );
//        RegistroDiario registroDiarioAyer = new RegistroDiario(
//            null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
//            LocalDate.now().minusDays(1), false, usuarioBase, new ArrayList<>() 
//        );
//        
//        // 3. Obtener Componentes necesarios
//        Optional<Plato> desayunoPlatoOpt = platoRepository.findByNombre("Desayuno Completo");
//        Optional<Plato> cenaPlatoOpt = platoRepository.findByNombre("Cena de Pollo");
//        
//        Optional<Producto> lecheOpt = productoRepository.findByBarcode("8412345678605");
//        Optional<Producto> panOpt = productoRepository.findByBarcode("8455544433321");
//        Optional<Producto> aceiteOpt = productoRepository.findByBarcode("8437000371004");
//        
//        if (desayunoPlatoOpt.isEmpty() || cenaPlatoOpt.isEmpty() || lecheOpt.isEmpty() || panOpt.isEmpty() || aceiteOpt.isEmpty()) {
//            System.err.println("Advertencia: No se encontraron todos los Platos/Productos necesarios.");
//            return;
//        }
//
//        Plato desayunoPlato = desayunoPlatoOpt.get();
//        Plato cenaPlato = cenaPlatoOpt.get();
//        Producto lecheProducto = lecheOpt.get();
//        Producto panProducto = panOpt.get();
//        Producto aceiteProducto = aceiteOpt.get();
//
//        // 4. Comida de DESAYUNO: 2 Platos y 2 Productos
//        Comida desayuno = new Comida(
//            null, 
//            TipoComida.DESAYUNO, 
//            LocalDate.now(), 
//            registroDiarioHoy, 
//            List.of(desayunoPlato, cenaPlato), // 2 Platos
//            List.of(lecheProducto, panProducto) // 2 Productos
//        );
//        
//        // 5. Comida de CENA: 1 Plato y 1 Producto
//        Comida cena = new Comida(
//            null, 
//            TipoComida.CENA, 
//            LocalDate.now(), 
//            registroDiarioHoy, 
//            List.of(cenaPlato), // 1 Plato
//            List.of(aceiteProducto) // 1 Producto
//        );
//        
//        // 6. Vincular y Guardar Registros
//        registroDiarioHoy.getComidas().add(desayuno); 
//        registroDiarioHoy.getComidas().add(cena); 
//        
//        registroDiarioHoy.recalcularTotales(); 
//        registroDiarioRepository.save(registroDiarioHoy); 
//        
//        registroDiarioRepository.save(registroDiarioAyer); // Guardar el registro vacío.
//    }
    
    // =======================================================================
    // --- MÉTODOS AUXILIARES ---
    // =======================================================================
    private void crearUsuarios() {
        // Obtener una ciudad base
        Ciudad ciudadBase = ciudadRepository.findAll().stream().findFirst().orElse(null);
        
        if (ciudadBase == null) {
            System.err.println("No hay ciudades disponibles para asignar a los usuarios.");
            return;
        }

        // Crear usuarios de ejemplo
        List<Usuario> usuarios = List.of(
        		 new Usuario(
        	            null, "dmo", "pass", "Demo", "demo@tfg.com", "600123456", 
       	            LocalDate.of(1990, 1, 1), Genero.MASCULINO, 75.0, 180.0, ciudadBase, new ArrayList<>(), new ArrayList<>(), new ArrayList<>() 
      	        ),
            new Usuario(null, "anaPerez", "password123", "Ana Pérez", "ana@tfg.com", "600654321",
                    LocalDate.of(1995, 6, 15), Genero.FEMENINO, 60.0, 165.0,
                    ciudadBase, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),
            new Usuario(null, "juanLopez", "1234abcd", "Juan López", "juan@tfg.com", "600987654",
                    LocalDate.of(1988, 3, 22), Genero.MASCULINO, 82.0, 175.0,
                    ciudadBase, new ArrayList<>(), new ArrayList<>(), new ArrayList<>())
        );

        // Guardar usuarios y crear registros diarios vacíos
        for (Usuario usuario : usuarios) {
            Usuario u = usuarioRepository.save(usuario);
            
            ObjetivoDiario obj = new ObjetivoDiario(null,0,0,0,0,0,0,u);
            // Crear registro diario vacío
            RegistroDiario registro = new RegistroDiario(
                null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                LocalDate.now(), false, u, new ArrayList<>()
            );
            objetivoDiarioRepository.save(obj);
            registroDiarioRepository.save(registro);
        }
    }
    private void crearReaccionesAdversas() {
        List<String> reacciones = List.of(
        		"intolerancia a la lactosa",
        		"intolerancia al gluten",
        		"intolerancia a la fructosa",
        		"intolerancia a la glucosa",
        		"intolerancia al huevo",
        		"intolerancia a la soja",
        		"intolerancia al trigo",
        		"intolerancia a la proteína de la leche",

        		"alergia a la leche",
        		"alergia al huevo",
        		"alergia al pescado",
        		"alergia a los mariscos",
        		"alergia a los frutos secos",
        		"alergia al cacahuete",
        		"alergia a la mostaza",
        		"alergia al sésamo",
        		"alergia al apio",
        		"alergia a los sulfitos",
        		"alergia al trigo",

        		"alergia a la manzana",
        		"alergia a la fresa",
        		"alergia al tomate",
        		"alergia a la mandarina",
        		"alergia a la zanahoria",
        		"alergia al kiwi",

        		"alergia al chocolate",
        		"alergia al café"
        );

        for (String nombre : reacciones) {
            Optional<ReaccionAdversa> existing = reaccionAdversaRepository.findByNombre(nombre);
            if (existing.isEmpty()) {
                ReaccionAdversa ra = new ReaccionAdversa();
                ra.setNombre(nombre);
                ra.setDescripcion(nombre); 
                ra.setUsuarios(new ArrayList<>());
                ra.setProductos(new ArrayList<>());
                reaccionAdversaRepository.save(ra);
            }
        }
    }

    
    private void crearSupermercados() {
        // Crear ciudad Almendralejo si no existe
        Ciudad almendralejo = ciudadRepository.findAll()
                .stream()
                .filter(c -> c.getNombre().equals("Almendralejo"))
                .findFirst()
                .orElseGet(() -> {
                    Ciudad nuevaCiudad = new Ciudad(null, "Almendralejo", "Badajoz", "España", "06200", new ArrayList<>(), new ArrayList<>());
                    return ciudadRepository.save(nuevaCiudad);
                });

        // Lista de supermercados
        List<Supermercado> supermercados = List.of(
            new Supermercado(null, "Aldi", "Calle Falsa 1", "600111222", "www.aldi.es", LocalTime.of(9,0), LocalTime.of(21,0), almendralejo),
            new Supermercado(null, "Dia", "Calle Falsa 2", "600222333", "www.dia.es", LocalTime.of(9,0), LocalTime.of(21,0), almendralejo),
            new Supermercado(null, "Mercadona", "Calle Falsa 3", "600333444", "www.mercadona.es", LocalTime.of(9,0), LocalTime.of(21,0), almendralejo),
            new Supermercado(null, "Lidl", "Calle Falsa 4", "600444555", "www.lidl.es", LocalTime.of(9,0), LocalTime.of(21,0), almendralejo),
            new Supermercado(null, "Carrefour", "Calle Falsa 5", "600555666", "www.carrefour.es", LocalTime.of(9,0), LocalTime.of(22,0), almendralejo),
            new Supermercado(null, "Eroski", "Calle Falsa 6", "600666777", "www.eroski.es", LocalTime.of(9,0), LocalTime.of(21,0), almendralejo)
        );

        // Guardar todos los supermercados
        supermercados.forEach(supermercadoRepository::save);
    }
    
    private void crearPlatos() {
    	// Lácteos
    	Producto lecheEntera = crearProductoData("8412345678905", "Leche Entera UHT", "Marca Blanca", CategoriaProducto.LACTEOS, 1000.0, 100.0, 60.0, 3.2, 1.8, 1.4, 4.7, 4.7, 3.1, 0.1, 0.0, UnidadMedida.MILILITROS, Envase.CARTÓN, List.of());
    	Producto yogurNatural = crearProductoData("8412345678912", "Yogur Natural", "Marca Blanca", CategoriaProducto.LACTEOS, 150.0, 100.0, 75.0, 4.0, 2.5, 1.5, 5.0, 5.0, 4.0, 0.1, 0.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, List.of());
    	Producto quesoCurado = crearProductoData("8412345678929", "Queso Curado", "Marca Quesera", CategoriaProducto.LACTEOS, 200.0, 100.0, 200.0, 16.0, 10.0, 6.0, 1.5, 1.5, 12.0, 1.2, 0.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, List.of());

    	// Carnes y pescados
    	Producto polloPecho = crearProductoData("2000000000018", "Pechuga de Pollo Fresca", "Avícola Premium", CategoriaProducto.CARNE, 500.0, 100.0, 165.0, 3.6, 1.0, 2.6, 0.0, 0.0, 31.0, 0.4, 0.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);
    	Producto salmonFresco = crearProductoData("2000000000025", "Salmón Fresco", "Pescados del Norte", CategoriaProducto.PESCADO, 400.0, 100.0, 208.0, 13.0, 3.0, 10.0, 0.0, 0.0, 20.0, 0.1, 0.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);
    	Producto ternera = crearProductoData("2000000000032", "Filete de Ternera", "Ganadería Local", CategoriaProducto.CARNE, 300.0, 150.0, 100.0, 15.0, 6.0, 9.0, 0.0, 0.0, 26.0, 0.2, 0.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);

    	// Pan y cereales
    	Producto panIntegral = crearProductoData("8455544433321", "Pan Integral Masa Madre", "Panadería Rústica", CategoriaProducto.PAN, 400.0, 100.0, 230.0, 3.0, 0.5, 2.5, 45.0, 3.0, 10.0, 0.5, 6.5, UnidadMedida.GRAMOS, Envase.PLÁSTICO, List.of());
    	Producto arrozIntegral = crearProductoData("8430000000041", "Arroz Integral", "Marca Blanca", CategoriaProducto.CEREALES_Y_GRANOS, 1000.0, 100.0, 290.0, 2.5, 0.5, 2.0, 62.0, 0.5, 7.0, 0.0, 5.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);
    	Producto pasta = crearProductoData("8430000000058", "Pasta Integral", "Marca Blanca", CategoriaProducto.CEREALES_Y_GRANOS, 500.0, 80.0, 100.0, 1.5, 0.3, 1.2, 58.0, 2.0, 10.0, 0.0, 5.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);

    	// Aceites y grasas
    	Producto aceiteOliva = crearProductoData("8437000371004", "Aceite de Oliva Virgen Extra", "Oleum", CategoriaProducto.ACEITES_Y_GRASAS, 500.0, 100.0, 900.0, 100.0, 15.0, 85.0, 0.0, 0.0, 0.0, 0.0, 0.0, UnidadMedida.MILILITROS, Envase.PLÁSTICO, null);
    	Producto mantequilla = crearProductoData("8437000371011", "Mantequilla Sin Sal", "DairyBest", CategoriaProducto.ACEITES_Y_GRASAS, 250.0, 100.0, 740.0, 82.0, 51.0, 31.0, 0.6, 0.6, 1.0, 1.5, 0.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);

    	// Verduras y frutas
    	Producto tomate = crearProductoData("8431000000001", "Tomate Raf", "Huerta Fresca", CategoriaProducto.VERDURAS_Y_HORTALIZAS, 500.0, 100.0, 20.0, 0.2, 0.05, 0.15, 4.0, 2.5, 1.0, 0.01, 1.5, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);
    	Producto lechuga = crearProductoData("8431000000002", "Lechuga Romana", "Huerta Fresca", CategoriaProducto.VERDURAS_Y_HORTALIZAS, 100.0, 100.0, 15.0, 0.2, 0.05, 0.15, 2.9, 0.9, 1.0, 0.01, 1.3, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);
    	Producto zanahoria = crearProductoData("8431000000003", "Zanahoria", "Huerta Fresca", CategoriaProducto.VERDURAS_Y_HORTALIZAS, 400.0, 100.0, 41.0, 0.2, 0.05, 0.15, 10.0, 4.5, 0.9, 0.05, 2.8, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);
    	Producto p1 = new Producto(  
    			null,// id
    		    "1234567890123",        // barcode
    		    "Yogur Natural Proteico",
    		    "LactaFit",
    		    false,                  // esFraccion
    		    false,                  // esPublico
    		    CategoriaProducto.ACEITES_Y_GRASAS,
    		    150,                    // pesoEmpaque
    		    100,                    // pesoConsumido
    		    90,                     // kcal
    		    2.5,                    // grasa
    		    1.2,                    // saturada
    		    1.3,                    // no saturada
    		    6.0,                    // hidratosCarbono
    		    4.5,                    // azucar
    		    15.0,                   // proteina
    		    0.2,                    // sal
    		    0.8,                    // fibra
    		    UnidadMedida.GRAMOS,
    		    Envase.PLÁSTICO,
    		    null,   // reacciones adversas
    		    null,   // platos
    		    null,   // comidas
    		    null    // usuario → producto privado sin creador aún
    		);
    	
    	Producto p2 = new Producto(
    		    null,                   // id
    		    null,                   // sin barcode porque es una fracción
    		    "Pollo Asado (Fracción)",
    		    "Campofresco",
    		    true,                   // esFraccion
    		    true,                   // esPublico
    		    CategoriaProducto.CARNE,
    		    200,                    // pesoEmpaque original
    		    100,                     // pesoConsumido → fracción
    		    165,                    // kcal por 100g
    		    6.2,                    // grasa
    		    1.8,                    // saturada
    		    4.4,                    // no saturada
    		    0.0,                    // hidratos
    		    0.0,                    // azucar
    		    27.0,                   // proteina
    		    0.7,                    // sal
    		    0.0,                    // fibra
    		    UnidadMedida.GRAMOS,
    		    Envase.CARTÓN,
    		    null,   // reacciones adversas
    		    null,   // platos
    		    null,   // comidas
    		    null    // usuario → público, accesible por todos
    		);


    	// Guardarlos
    	List<Producto> productos = List.of(
    	    lecheEntera, yogurNatural, quesoCurado, polloPecho, salmonFresco, ternera,
    	    panIntegral, arrozIntegral, pasta, aceiteOliva, mantequilla, tomate, lechuga, zanahoria, p1, p2
    	);
    	productos.forEach(productoRepository::save);
    	List<Plato> platos = List.of(
    		    new Plato(null, "PRUEBA VISIBILIDAD", "AAAAAAAAAAAAAAAAAAAAAA", false, List.of(lecheEntera, panIntegral, aceiteOliva),null),
    		    new Plato(null, "Desayuno Energético", "Leche, pan integral y aceite de oliva", true, List.of(lecheEntera, panIntegral, aceiteOliva),null),
    		    new Plato(null, "Tostada con Yogur", "Pan integral con yogur natural", true, List.of(panIntegral, yogurNatural), null),
    		    new Plato(null, "Ensalada César", "Lechuga, pollo y tomate con aceite de oliva", true, List.of(lechuga, polloPecho, tomate, aceiteOliva), null),
    		    new Plato(null, "Pollo a la Plancha", "Pechuga de pollo con un toque de aceite", true, List.of(polloPecho, aceiteOliva), null),
    		    new Plato(null, "Salmón al Horno", "Salmón fresco con aceite de oliva", true, List.of(salmonFresco, aceiteOliva), null),
    		    new Plato(null, "Bocadillo de Pollo", "Pan integral con pechuga de pollo fresca", true, List.of(panIntegral, polloPecho), null),
    		    new Plato(null, "Arroz con Verduras", "Arroz integral con zanahoria y tomate", true, List.of(arrozIntegral, zanahoria, tomate), null),
    		    new Plato(null, "Pasta con Pollo", "Pasta integral con pechuga de pollo", true, List.of(pasta, polloPecho), null),
    		    new Plato(null, "Tortilla de Zanahoria", "Zanahoria y huevos (no añadimos huevo ahora, ejemplo)", true, List.of(zanahoria), null),
    		    new Plato(null, "Queso con Pan", "Queso curado y pan integral", true, List.of(quesoCurado, panIntegral), null),
    		    new Plato(null, "Leche con Mantequilla", "Leche entera con un toque de mantequilla", true, List.of(lecheEntera, mantequilla), null),
    		    new Plato(null, "Ensalada Mixta", "Lechuga, tomate y zanahoria", true, List.of(lechuga, tomate, zanahoria), null),
    		    new Plato(null, "Bowl Proteico", "Pollo, arroz integral y aceite", true, List.of(polloPecho, arrozIntegral, aceiteOliva), null),
    		    new Plato(null, "Sopa de Verduras", "Zanahoria, tomate y lechuga", true, List.of(zanahoria, tomate, lechuga), null),
    		    new Plato(null, "Pasta con Salmón", "Pasta integral con salmón fresco", true, List.of(pasta, salmonFresco), null),
    		    new Plato(null, "Arroz con Pollo", "Arroz integral con pechuga de pollo", true, List.of(arrozIntegral, polloPecho), null),
    		    new Plato(null, "Salmón con Aceite", "Salmón fresco con aceite de oliva", true, List.of(salmonFresco, aceiteOliva), null),
    		    new Plato(null, "Arroz Simple", "Arroz integral cocido", true, List.of(arrozIntegral), null),
    		    new Plato(null, "Pasta Simple", "Pasta integral cocida", true, List.of(pasta), null),
    		    new Plato(null, "Pan con Aceite", "Pan integral con aceite de oliva", true, List.of(panIntegral, aceiteOliva), null),
    		    new Plato(null, "Leche Simple", "Leche entera sin acompañamiento", true, List.of(lecheEntera), null),
    		    new Plato(null, "Yogur Simple", "Yogur natural sin acompañamiento", true, List.of(yogurNatural), null)
    		);
    		platos.forEach(platoRepository::save);

    }
    
    private void crearProductos() {
    	ReaccionAdversa lactosa = reaccionAdversaRepository.findByNombre("intolerancia a la lactosa").orElse(null);
        ReaccionAdversa trigo = reaccionAdversaRepository.findByNombre("intolerancia al trigo").orElse(null);
        ReaccionAdversa huevo = reaccionAdversaRepository.findByNombre("alergia al huevo").orElse(null);
        List<ReaccionAdversa> alergenosPan = new ArrayList<>();
        if (trigo != null) alergenosPan.add(trigo);
        if (huevo != null) alergenosPan.add(huevo);
        
        List<ReaccionAdversa> alergenosLeche = lactosa != null ? List.of(lactosa) : new ArrayList<>();
        
        Producto leche = crearProductoData("8412345678905", "Leche Entera UHT", "Marca Blanca", CategoriaProducto.LACTEOS, 1000.0, 100.0, 60.0, 3.2, 1.8, 1.4, 4.7, 4.7, 3.1, 0.1, 0.0, UnidadMedida.MILILITROS, Envase.CARTÓN, alergenosLeche); 
        
        Producto pollo = crearProductoData("2000000000018", "Pechuga de Pollo Fresca", "Avícola Premium", CategoriaProducto.CARNE, 500.0, 100.0, 165.0, 3.6, 1.0, 2.6, 0.0, 0.0, 31.0, 0.4, 0.0, UnidadMedida.GRAMOS, Envase.PLÁSTICO, null);
        
        Producto pan = crearProductoData("8455544433321", "Pan Integral Masa Madre", "Panadería Rústica", CategoriaProducto.PAN, 400.0, 100.0, 100.0, 3.0, 0.5, 2.5, 45.0, 3.0, 10.0, 0.5, 6.5, UnidadMedida.GRAMOS, Envase.PLÁSTICO, alergenosPan );
        
        // Nuevo producto
        Producto aceite = crearProductoData("8437000371004", "Aceite de Oliva Virgen Extra", "Oleum", CategoriaProducto.ACEITES_Y_GRASAS, 500.0, 100.0, 900.0, 100.0, 15.0, 85.0, 0.0, 0.0, 0.0, 0.0, 0.0, UnidadMedida.MILILITROS, Envase.PLÁSTICO, null);

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
    
    private void crearListasCompra() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Producto> productos = productoRepository.findAll();

        if (usuarios.isEmpty() || productos.isEmpty()) {
            System.err.println("No hay usuarios o productos disponibles para crear listas de compra.");
            return;
        }

        // Crear listas de ejemplo para distintos usuarios
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            // Número de listas aleatorio entre 1 y 3 para este ejemplo
            int numListas = switch (i) {
                case 0 -> 2; // primer usuario tiene 2 listas
                case 1 -> 1; // segundo usuario tiene 1 lista
                default -> 3; // resto tienen 3 listas
            };

            for (int j = 0; j < numListas; j++) {
                ListaCompra lista = new ListaCompra();
                lista.setNombre("Lista de " + usuario.getNombreUsuario() + " #" + (j + 1));
                lista.setFechaCreacion(LocalDate.now().minusDays(j));
                lista.setUsuario(usuario);

                // Agregar entre 1 y 5 líneas de compra aleatorias
                int numLineas = 1 + (int)(Math.random() * 5);
                for (int k = 0; k < numLineas; k++) {
                    Producto producto = productos.get((i + j + k) % productos.size()); // para no salirse del array
                    LineaCompra linea = new LineaCompra();
                    linea.setCantidad(1 + (int)(Math.random() * 3)); // cantidad 1-3
                    linea.setComprado(Math.random() > 0.5); // comprado al azar
                    linea.setProducto(producto);
                    linea.setListaCompra(lista);

                    lista.getLineas().add(linea);
                }

                // Guardar lista con cascada de líneas
                // Como cascade = ALL, al guardar lista se guardan las líneas automáticamente
                listaCompraRepository.save(lista);
            }
        }
    }

    
    private Producto crearProductoData(String barcode, String nombre, String marca, CategoriaProducto categoria,
            double pesoEmpaque, double pesoConsumido, double kcal, double grasa,
            double saturada, double noSaturada, double hidratosCarbono, double azucar,
            double proteina, double sal, double fibra, UnidadMedida unidad, Envase envase, List<ReaccionAdversa> reacciones) {

    		return new Producto(null, barcode, nombre, marca, false, true,categoria, pesoEmpaque, 100, 
    		kcal, grasa, saturada, noSaturada, hidratosCarbono, azucar, 
    		proteina, sal, fibra, unidad, envase, 
    		reacciones, null, null, null); 
    }
}