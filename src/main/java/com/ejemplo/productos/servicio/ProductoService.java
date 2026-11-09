package com.ejemplo.productos.servicio;

import com.ejemplo.productos.modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.List;
import com.ejemplo.productos.excepcion.ProductoNoEncontradoException;
import com.ejemplo.productos.excepcion.ProductoInvalidoException;
import java.math.BigDecimal;

@Service
public class ProductoService {
    private final Map<Long, Producto> productos = new ConcurrentHashMap<>();
    private final AtomicLong siguienteId = new AtomicLong(1);
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }
    public Producto obtenerProducto(Long id) {
        Producto producto = productos.get(id);

        if (producto == null) {
            throw new ProductoNoEncontradoException("No existe un producto con id " + id);
        }

        return producto;
    }
    public Producto registrarProducto(Producto producto) {
        if (producto == null) {
            throw new ProductoInvalidoException("Debe enviar un producto");
        }
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new ProductoInvalidoException("El nombre es obligatorio");
        }
        if (producto.getPrecio() == null ||
                producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor que cero");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo");
        }

        producto.setId(siguienteId.getAndIncrement());
        productos.put(producto.getId(), producto);
        return producto;
    }
    public void eliminarProducto(Long id) {
        if (productos.remove(id) == null) {
            throw new ProductoNoEncontradoException("No existe un producto con id " + id);
        }
    }
}