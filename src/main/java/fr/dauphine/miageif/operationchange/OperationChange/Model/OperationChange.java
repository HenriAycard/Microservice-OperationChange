package fr.dauphine.miageif.operationchange.OperationChange.Model;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// Classe persistente representant  un "Operation Change"
@Entity
public class OperationChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_transaction;

    @Column(name="devise_src")
    private String source;

    @Column(name="devise_dest")
    private String dest;

    @Column(name="montant")
    private Integer montant;

    @Column(name="taux")
    private BigDecimal taux;

    @Column(name="date_cours")
    private String date;

    public OperationChange(){
    }

    public OperationChange(Long id_transaction, String source, String dest, Integer montant, BigDecimal taux, String date){
        super();
        this.id_transaction = id_transaction;
        this.montant = montant;
        this.source = source;
        this.dest = dest;
        this.taux = taux;
        this.date = date;
    }

    public OperationChange(String source, String dest, Integer montant, BigDecimal taux, String date){
        super();
        this.montant = montant;
        this.source = source;
        this.dest = dest;
        this.taux = taux;
        this.date = date;
    }

    public OperationChange(String source, String dest, Integer montant, String date){
        super();
        this.montant = montant;
        this.source = source;
        this.dest = dest;
        this.date = date;
    }

    // GETTER

    public Integer getMontant() {
        return montant;
    }

    public String getSource() {
        return source;
    }

    public String getDest() {
        return dest;
    }

    public BigDecimal getTaux() {
        return taux;
    }

    public String getDate() { return date; }

    // SETTER

    public void setMontant(Integer montant) { this.montant = montant; }

    public void setSource(String source) { this.source = source; }

    public void setDestination(String dest) { this.dest = dest; }

    public void setTaux(BigDecimal taux) { this.taux = taux; }

    public void setDate(String date) { this.date = date; }


}
