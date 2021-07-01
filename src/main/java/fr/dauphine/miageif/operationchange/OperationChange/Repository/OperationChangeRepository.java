package fr.dauphine.miageif.operationchange.OperationChange.Repository;

import fr.dauphine.miageif.operationchange.OperationChange.Model.OperationChange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

import java.util.Optional;

public interface OperationChangeRepository extends JpaRepository<OperationChange, Long> {
    List<OperationChange> findByMontant(Integer montant);

    List<OperationChange> findByDate(String date);

    List<OperationChange> findByTaux(BigDecimal taux);

    List<OperationChange> findBySourceAndDest(String source, String dest);

}
