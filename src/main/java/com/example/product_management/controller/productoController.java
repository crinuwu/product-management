package com.example.product_management.controller;

import com.example.product_management.model.producto;
import com.example.product_management.services.productoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/productos")
public class productoController {

    @Autowired
    private productoService productoService;

    @GetMapping
    public List<producto> getProductos() {
        return productoService.getProductos();
    }

    @PostMapping
    public producto agregarProducto(@RequestBody producto producto) {
        productoService.saveProducto(producto);
        return producto;
    }

    @GetMapping("/{idProducto}")
    public producto getProductoById(@PathVariable int idProducto) {
        return productoService.getProductoById(idProducto);
    }

    @PutMapping("/{idProducto}")
    public producto updateProducto(@PathVariable int idProducto, @RequestBody producto producto) {
        producto.setIdProducto(idProducto);
        productoService.updateProducto(producto);
        return producto;
    }

    @DeleteMapping("/{idProducto}")
    public void deleteProducto(@PathVariable int idProducto) {
        productoService.deleteProducto(idProducto);
    }

    @GetMapping("/marca/{marcaProducto}")
    public List<producto> getProductosByMarca(@PathVariable String marcaProducto) {
        return productoService.getProductosByMarca(marcaProducto);
    }

    @GetMapping("/vegano")
    public List<producto> getProductosVeganos() {
        return productoService.getProductosVeganos();
    }

    @GetMapping("/libre-crueldad")
    public List<producto> getProductosLibresDeCrueldad() {
        return productoService.getProductosLibresDeCrueldad();
    }
}
