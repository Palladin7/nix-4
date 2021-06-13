package ua.com.alevel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "incomes")
public class Income {

    @Id
    private String type;

    @ManyToMany(mappedBy = "incomes")
    private Set<Transaction> transactions = new HashSet<>();

    public Income(String type, Set<Transaction> transactions) {
        this.type = type;
        this.transactions = transactions;
    }

    public Income() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
