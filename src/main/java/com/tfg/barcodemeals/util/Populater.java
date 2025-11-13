package com.tfg.barcodemeals.util;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sun.tools.javac.util.List;
import com.tfg.barcodemeals.model.Comida;
import com.tfg.barcodemeals.model.Genero;
import com.tfg.barcodemeals.model.LineaCompra;
import com.tfg.barcodemeals.model.ListaCompra;
import com.tfg.barcodemeals.model.Plato;
import com.tfg.barcodemeals.model.Precio;
import com.tfg.barcodemeals.model.Producto;
import com.tfg.barcodemeals.model.ReaccionAdversa;
import com.tfg.barcodemeals.model.Supermercado;
import com.tfg.barcodemeals.model.Usuario;
import com.tfg.barcodemeals.repository.ComidaRepository;
import com.tfg.barcodemeals.repository.LineaCompraRepository;
import com.tfg.barcodemeals.repository.ListaCompraRepository;
import com.tfg.barcodemeals.repository.PlatoRepository;
import com.tfg.barcodemeals.repository.PrecioRepository;
import com.tfg.barcodemeals.repository.ProductoRepository;
import com.tfg.barcodemeals.repository.ReaccionAdversaRepository;
import com.tfg.barcodemeals.repository.SupermercadoRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository;

@Component
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

    public Populater(
            UsuarioRepository usuarioRepository,
            ProductoRepository productoRepository,
            PrecioRepository precioRepository,
            PlatoRepository platoRepository,
            ComidaRepository comidaRepository,
            ReaccionAdversaRepository reaccionAdversaRepository,
            SupermercadoRepository supermercadoRepository,
            ListaCompraRepository listaCompraRepository,
            LineaCompraRepository lineaCompraRepository
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
    }

    @Override
    public void run(String... args) throws Exception {
        // --- Usuarios ---
        Usuario usuario1 = new Usuario();
        usuario1.setNombreUsuario("juan123");
        usuario1.setContrasena("1234");
        usuario1.setApodo("Juan");
        usuario1.setEmail("juan@email.com");
        usuario1.setTelefono("123456789");
        usuario1.setFechaNacimiento(LocalDate.of(1990, 5, 20));
        usuario1.setGenero(Genero.MASCULINO);
        usuario1.setPeso(70);
        usuario1.setAltura(1.75);
        usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNombreUsuario("maria456");
        usuario2.setContrasena("abcd");
        usuario2.setApodo("María");
        usuario2.setEmail("maria@email.com");
        usuario2.setTelefono("987654321");
        usuario2.setFechaNacimiento(LocalDate.of(1995, 8, 12));
        usuario2.setGenero(Genero.FEMENINO);
        usuario2.setPeso(60);
        usuario2.setAltura(1.65);
        usuarioRepository.save(usuario2);

        // --- Productos ---
        Producto producto1 = new Producto();
        producto1.setNombre("Leche");
        producto1.setCategoria("Lácteo");
        producto1.setEnvase(Producto.Envase.CARTÓN);
        productoRepository.save(producto1);

        Producto producto2 = new Producto();
        producto2.setNombre("Pan");
        producto2.setCategoria("Panadería");
        producto2.setEnvase(Producto.Envase.ORGÁNICO);
        productoRepository.save(producto2);

        // --- Precios ---
        Precio precio1 = new Precio();
        precio1.setProducto(producto1);
        precio1.setPrecio(1.5f);
        precio1.setOferta(false);
        precio1.setDescuento(0f);
        precioRepository.save(precio1);

        Precio precio2 = new Precio();
        precio2.setProducto(producto2);
        precio2.setPrecio(2.0f);
        precio2.setOferta(true);
        precio2.setDescuento(0.2f);
        precioRepository.save(precio2);

        // --- Platos ---
        Plato plato1 = new Plato();
        plato1.setNombre("Desayuno Básico");
        plato1.setDescripcion("Leche y Pan");
        plato1.setProductos(List.of(producto1, producto2));
        platoRepository.save(plato1);

        // --- Comidas ---
        Comida comida1 = new Comida();
        comida1.setTipo(Comida.Tipo.DESAYUNO);
        comida1.setFecha(LocalDate.now());
        comida1.setPlatos(List.of(plato1));
        comidaRepository.save(comida1);

        // --- Reacciones Adversas ---
        ReaccionAdversa reaccion1 = new ReaccionAdversa();
        reaccion1.setTipo(ReaccionAdversa.Tipo.ALERGIA);
        reaccion1.setDescripcion("Alergia a la leche");
        reaccion1.setProductos(List.of(producto1));
        reaccionAdversaRepository.save(reaccion1);

        // --- Supermercados ---
        Supermercado supermercado1 = new Supermercado();
        supermercado1.setNombre("Supermercado Central");
        supermercado1.setHoraApertura(LocalTime.of(9, 0));
        supermercado1.setHoraCierre(LocalTime.of(21, 0));
        supermercado1.setRating(4.5f);
        supermercadoRepository.save(supermercado1);

        // --- Listas de compra ---
        ListaCompra listaCompra1 = new ListaCompra();
        listaCompra1.setUsuario(usuario1);
        listaCompra1.setPrecioTotal(0f);
        listaCompraRepository.save(listaCompra1);

        LineaCompra linea1 = new LineaCompra();
        linea1.setListaCompra(listaCompra1);
        linea1.setProducto(producto1);
        linea1.setCantidad(2);
        linea1.setPrecioLinea(precio1.getPrecio() * linea1.getCantidad());
        lineaCompraRepository.save(linea1);

        LineaCompra linea2 = new LineaCompra();
        linea2.setListaCompra(listaCompra1);
        linea2.setProducto(producto2);
        linea2.setCantidad(1);
        linea2.setPrecioLinea(precio2.getPrecio() * linea2.getCantidad());
        lineaCompraRepository.save(linea2);

        listaCompra1.setPrecioTotal(linea1.getPrecioLinea() + linea2.getPrecioLinea());
        listaCompraRepository.save(listaCompra1);
    }
}