package fr.dauphine.miageif.operationchange.OperationChange.Model;
import java.math.BigDecimal;
import java.util.Objects;
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

    @Column(name="counterpart")
    private String counterpart;

    public OperationChange(){
    }

    public OperationChange(Long id_transaction, String source, String dest, Integer montant, BigDecimal taux, String date,String counterpart){
        super();
        this.id_transaction = id_transaction;
        this.montant = montant;
        this.source = source;
        this.dest = dest;
        this.taux = taux;
        this.date = date;
        this.counterpart = counterpart;
    }

    public OperationChange(String source, String dest, Integer montant, BigDecimal taux, String date,String counterpart){
        super();
        this.montant = montant;
        this.source = source;
        this.dest = dest;
        this.taux = taux;
        this.date = date;
        this.counterpart = counterpart;
    }

    public OperationChange(String source, String dest, Integer montant, String date,String counterpart){
        super();
        this.montant = montant;
        this.source = source;
        this.dest = dest;
        this.date = date;
        this.counterpart = counterpart;
    }

    // GETTER
    public Long getId() {
        return id_transaction;
    }

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

    public String getCounterpart() { return counterpart; }

    // SETTER


    public void setId(Long id_transaction) { this.id_transaction = id_transaction; }

    public void setMontant(Integer montant) { this.montant = montant; }

    public void setSource(String source) { this.source = source; }

    public void setDestination(String dest) { this.dest = dest; }

    public void setTaux(BigDecimal taux) { this.taux = taux; }

    public void setDate(String date) { this.date = date; }

    public void setCounterpart(String counterpart) { this.counterpart = counterpart; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationChange that = (OperationChange) o;
        return Objects.equals(id_transaction, that.id_transaction) && Objects.equals(source, that.source) && Objects.equals(dest, that.dest) && Objects.equals(montant, that.montant) && Objects.equals(taux, that.taux) && Objects.equals(date, that.date) && Objects.equals(counterpart, that.counterpart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_transaction, source, dest, montant, taux, date, counterpart);
    }
}
