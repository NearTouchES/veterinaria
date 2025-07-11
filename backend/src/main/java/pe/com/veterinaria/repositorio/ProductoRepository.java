package pe.com.veterinaria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.veterinaria.modelo.Producto;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long> {
}
