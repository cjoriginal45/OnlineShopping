
package com.ecommerce.OnlineShopping.Repositories;

import com.ecommerce.OnlineShopping.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    @Query("SELECT COUNT(m) > 0 FROM Marca m WHERE LOWER(m.nombre) = LOWER(:marca)")
    boolean getMarcaName(@Param("marca") String marca);
    
    @Query("SELECT m FROM Marca m WHERE LOWER(m.nombre) = LOWER(:marca)")
    Marca getMarca(@Param("marca") String marca);
    
}
