package com.example.product_management.assemblers;

import com.example.product_management.controller.productoController;
import com.example.product_management.model.producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class productoAssembler implements RepresentationModelAssembler<producto, EntityModel<producto>> {

    @Override
    public EntityModel<producto> toModel(producto prod) {
        return EntityModel.of(prod,
                linkTo(methodOn(productoController.class).getProductoById(prod.getIdProducto())).withSelfRel(),
                linkTo(methodOn(productoController.class).getProductos()).withRel("all-products"),
                linkTo(methodOn(productoController.class).deleteProducto(prod.getIdProducto())).withRel("delete")
        );
    }
}
