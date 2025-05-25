package com.example.product_management.services;

import com.example.product_management.model.producto;
import com.example.product_management.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productoService {
    @Autowired
    private productoRepository productoRepository;

    public List<producto> getProductos() {
        return productoRepository.findAll();
    }

    public producto getProductoById(int idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

    public void saveProducto(producto producto) {
        productoRepository.save(producto);
    }

    public void updateProducto(producto producto) {
        productoRepository.save(producto);
    }

    public void deleteProducto(int idProducto) {
        productoRepository.deleteById(idProducto);
    }

    public List<producto> getProductosByMarca(String marcaProducto) {
        return productoRepository.findByMarcaProducto(marcaProducto);
    }

    public List<producto> getProductosVeganos() {
        return productoRepository.findByProductoVeganoTrue();
    }

    public List<producto> getProductosLibresDeCrueldad() {
        return productoRepository.findByLibreCrueldadTrue();
    }
}
