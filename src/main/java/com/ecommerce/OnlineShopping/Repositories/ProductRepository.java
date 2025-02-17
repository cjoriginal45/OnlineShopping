package com.ecommerce.OnlineShopping.Repositories;

import com.ecommerce.OnlineShopping.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p JOIN p.categoria c WHERE LOWER(TRIM(c.nombre)) = LOWER(TRIM(:categoria))")
    Page<Producto> findByCategoriaNombre(@Param("categoria") String categoria, Pageable pageable);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    @Override
    Page<Producto> findAll(Pageable pageable);
}
