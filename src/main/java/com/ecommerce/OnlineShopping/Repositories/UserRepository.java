package com.ecommerce.OnlineShopping.Repositories;

import com.ecommerce.OnlineShopping.models.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    public boolean existsByEmail(String email);

    public boolean existsByNombre(String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.email = :email")
    Optional<Usuario> findByNombreAndEmail(@Param("nombre") String nombre, @Param("email") String email);

    @Query("SELECT u.password FROM Usuario u WHERE u.email = :email")
    String findPassword(@Param("email") String email);

}
