package ua.com.alevel.entity;

import ua.com.alevel.entity.enumeration.TransactionCategory;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column
    private Instant date;

    private TransactionCategory category;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany
    @JoinTable(
            name = "transactions_incomes",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "income_id")
    )
    private Set<Income> incomes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "transactions_expenses",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "expense_id")
    )
    private Set<Expense> expenses = new HashSet<>();

    public Transaction(Double amount, Instant date, TransactionCategory category, Account account,
                       Set<Income> incomes, Set<Expense> expenses) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.account = account;
        this.incomes = incomes;
        this.expenses = expenses;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }
}
