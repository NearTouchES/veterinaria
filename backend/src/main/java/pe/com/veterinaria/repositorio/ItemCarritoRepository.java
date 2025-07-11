package pe.com.veterinaria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.veterinaria.modelo.ItemCarrito;

import java.util.List;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {
    List<ItemCarrito> findByCarritoId(Long carritoId);
}
