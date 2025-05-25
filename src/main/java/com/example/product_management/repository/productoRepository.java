package com.example.product_management.repository;

import com.example.product_management.model.producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productoRepository extends JpaRepository<producto, Integer> {
    List<producto> findByMarcaProducto(String marcaProducto);
    List<producto> findByProductoVeganoTrue();
    List<producto> findByLibreCrueldadTrue();
}
