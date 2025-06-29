package com.example.product_management.controller;

import com.example.product_management.assemblers.productoAssembler;
import com.example.product_management.model.producto;
import com.example.product_management.services.productoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0/productos")
@Tag(name = "Productos", description = "Operaciones para gestionar productos de belleza")
public class productoController {

    @Autowired
    private productoService productoService;

    @Autowired
    private productoAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    public CollectionModel<EntityModel<producto>> getProductos() {
        List<EntityModel<producto>> productos = productoService.getProductos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(productos);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo producto", description = "Registra un nuevo producto en el sistema")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    public ResponseEntity<EntityModel<producto>> agregarProducto(
            @Parameter(description = "Datos del producto a crear", required = true)
            @RequestBody producto producto) {
        productoService.saveProducto(producto);
        return ResponseEntity
                .created(null)
                .body(assembler.toModel(producto));
    }

    @GetMapping("/{idProducto}")
    @Operation(summary = "Obtener un producto por ID", description = "Devuelve un producto específico según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<EntityModel<producto>> getProductoById(
            @Parameter(description = "ID del producto a buscar", required = true)
            @PathVariable int idProducto) {
        producto prod = productoService.getProductoById(idProducto);
        if (prod == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(prod));
    }

    @PutMapping("/{idProducto}")
    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<EntityModel<producto>> updateProducto(
            @Parameter(description = "ID del producto a actualizar", required = true)
            @PathVariable int idProducto,
            @RequestBody producto producto) {
        producto.setIdProducto(idProducto);
        productoService.updateProducto(producto);
        return ResponseEntity.ok(assembler.toModel(producto));
    }

    @DeleteMapping("/{idProducto}")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto existente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> deleteProducto(
            @Parameter(description = "ID del producto a eliminar", required = true)
            @PathVariable int idProducto) {
        producto prod = productoService.getProductoById(idProducto);
        if (prod == null) {
            return ResponseEntity.notFound().build();
        }
        productoService.deleteProducto(idProducto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/marca/{marcaProducto}")
    @Operation(summary = "Obtener productos por marca", description = "Devuelve una lista de productos de una marca específica")
    @ApiResponse(responseCode = "200", description = "Lista de productos por marca obtenida exitosamente")
    public CollectionModel<EntityModel<producto>> getProductosByMarca(
            @Parameter(description = "Marca de los productos a buscar", required = true)
            @PathVariable String marcaProducto) {
        List<EntityModel<producto>> productos = productoService.getProductosByMarca(marcaProducto).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(productos);
    }

    @GetMapping("/vegano")
    @Operation(summary = "Obtener productos veganos", description = "Devuelve una lista de productos veganos")
    @ApiResponse(responseCode = "200", description = "Lista de productos veganos obtenida exitosamente")
    public CollectionModel<EntityModel<producto>> getProductosVeganos() {
        List<EntityModel<producto>> productos = productoService.getProductosVeganos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(productos);
    }

    @GetMapping("/libre-crueldad")
    @Operation(summary = "Obtener productos libres de crueldad", description = "Devuelve una lista de productos libres de crueldad animal")
    @ApiResponse(responseCode = "200", description = "Lista de productos libres de crueldad obtenida exitosamente")
    public CollectionModel<EntityModel<producto>> getProductosLibresDeCrueldad() {
        List<EntityModel<producto>> productos = productoService.getProductosLibresDeCrueldad().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(productos);
    }
}
