package pe.com.veterinaria.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.veterinaria.modelo.Carrito;
import pe.com.veterinaria.modelo.ItemCarrito;
import pe.com.veterinaria.repositorio.CarritoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {
    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public List<Carrito> listarTodos() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> obtenerPorId(Long id) {
        return carritoRepository.findById(id);
    }

    public Carrito guardar(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void eliminar(Long id) {
        carritoRepository.deleteById(id);
    }

    public List<Carrito> listarPorClienteId(Long clienteId) {
        return carritoRepository.findByClienteId(clienteId);
    }

    @Transactional
    public Carrito actualizar(Long id, Carrito carritoActualizado) {
        return carritoRepository.findById(id).map(carritoExistente -> {
            carritoExistente.setNombre(carritoActualizado.getNombre());
            carritoExistente.setFecha(carritoActualizado.getFecha());
            carritoExistente.setCliente(carritoActualizado.getCliente());

            carritoExistente.getItems().clear();

            for (ItemCarrito item : carritoActualizado.getItems()) {
                item.setCarrito(carritoExistente);
                carritoExistente.getItems().add(item);
            }

            return carritoRepository.save(carritoExistente);
        }).orElseThrow(() -> new RuntimeException("Carrito no encontrado con id " + id));
    }
}
