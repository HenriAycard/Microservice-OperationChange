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

    List<OperationChange> findBySourceAndDestAndDate(String source, String dest, String date);

    List<OperationChange> findByCounterpart(String counterpart);

    List<OperationChange> findByCounterpartAndDate(String counterpart,String date);

    List<OperationChange> findByCounterpartAndSourceAndDest(String counterpart,String source,String dest);

    List<OperationChange> findByCounterpartAndSourceAndDestAndDate(String counterpart,String source,String dest,String date);
}
