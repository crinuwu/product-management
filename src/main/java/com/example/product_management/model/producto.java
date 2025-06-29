package com.example.product_management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Objeto que representa un producto de belleza")
public class producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del producto", example = "1")
    private int idProducto;

    @Column(nullable = false)
    @Schema(description = "Nombre del producto", example = "Shampoo revitalizante")
    private String nombreProducto;

    @Column(nullable = false)
    @Schema(description = "Marca del producto", example = "L'Oreal")
    private String marcaProducto;

    @Column(nullable = false)
    @Schema(description = "Precio del producto", example = "199.99")
    private double precioProducto;

    @Column(nullable = false)
    @Schema(description = "Fecha de fabricación (DD-MM-YYYY)", example = "01-01-2024")
    private String fechaDeFabricacion;

    @Column(nullable = false)
    @Schema(description = "Fecha de vencimiento (DD-MM-YYYY)", example = "01-01-2026")
    private String fechaDeVencimiento;

    @Column(nullable = false)
    @Schema(description = "Stock disponible", example = "50")
    private int stockProducto;

    @Column(nullable = false)
    @Schema(description = "¿El producto es vegano?", example = "true")
    private boolean productoVegano;

    @Column(nullable = false)
    @Schema(description = "¿El producto es libre de crueldad animal?", example = "true")
    private boolean libreCrueldad;
}
