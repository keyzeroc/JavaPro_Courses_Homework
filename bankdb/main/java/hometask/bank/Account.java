package hometask.bank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Accounts")
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String currencyType; // USD , EUR, UAH
    private Double balance; // in type of above

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public Account(){}
    public Account(String currencyType, Client client){
        if(!CurrencyTypes.isType(currencyType)){
            System.out.println("Wrong argument: \'"+currencyType+"\' not a currency type!");
        }
        this.client=client;
        client.addAccount(this);
        this.currencyType = currencyType;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String type) {
        if(!CurrencyTypes.isType(type)){
            throw new IllegalArgumentException("Wrong argument: \'"+type+"\' not a currency type!");
        }
        balance = balance* CourseContainer.searchCourse(currencyType,type).getCoefficient();
        this.currencyType = type;
    }
    public void clearTransactions(){
        transactions.clear();
    }
    public Transaction getTransaction(int index){
        return transactions.get(index);
    }
    public List<Transaction> getTransactions(){
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions){
        this.transactions=transactions;
    }
    public void addTransaction(Transaction t){
        if(transactions.contains(t)) return;
        transactions.add(t);
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Double getBalance() {
        if(balance==null) return 0.;
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Account{ id=").append(id).append(", currencyType=").append(currencyType)
                .append(", balance=").append(balance).append(", client=").append(client)
                .append(" }").toString();
    }

}
