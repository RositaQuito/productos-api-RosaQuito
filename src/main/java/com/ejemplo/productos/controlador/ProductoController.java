package com.ejemplo.productos.controlador;

import com.ejemplo.productos.servicio.ProductoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ejemplo.productos.modelo.Producto;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable("id") Long id) {
        return productoService.obtenerProducto(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto registrarProducto(@RequestBody Producto producto) {
        return productoService.registrarProducto(producto);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
    }
}