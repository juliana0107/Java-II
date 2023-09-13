package ps.ja15.Entregable.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ps.ja15.Entregable.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia,Long> {

}
