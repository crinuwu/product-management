package com.example.product_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Column(nullable = false)
    private String nombreProducto;

    @Column(nullable = false)
    private String marcaProducto;

    @Column(nullable = false)
    private double precioProducto;

    @Column(nullable = false)
    private String fechaDeFabricacion; // formato DD-MM-YYYY

    @Column(nullable = false)
    private String fechaDeVencimiento; // formato DD-MM-YYYY

    @Column(nullable = false)
    private int stockProducto;

    @Column(nullable = false)
    private boolean productoVegano;

    @Column(nullable = false)
    private boolean libreCrueldad;
}
