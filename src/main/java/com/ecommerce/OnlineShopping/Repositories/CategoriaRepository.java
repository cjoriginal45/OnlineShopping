package com.ecommerce.OnlineShopping.Repositories;
import com.ecommerce.OnlineShopping.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
    @Query("SELECT COUNT(c) > 0 FROM Categoria c WHERE c.nombre = :nombre")
    boolean existeCategoria(@Param("nombre") String nombre);
}
