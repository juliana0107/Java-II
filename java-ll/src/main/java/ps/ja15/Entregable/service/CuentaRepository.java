package ps.ja15.Entregable.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ps.ja15.Entregable.model.Cuenta;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    void deleteByNumeroCuenta(String numeroCuenta);


}
