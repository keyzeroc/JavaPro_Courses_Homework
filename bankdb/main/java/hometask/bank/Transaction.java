package hometask.bank;

import javax.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "transaction_name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "to_account_id")
    private Account account;

    @Column(name="currency")
    private String currencyType;
    
    @Column(name="funds")
    private Double money;

    public Transaction() {
    }

    public Transaction(String name, Account account, Double money, String currencyType ) {
        setCurrencyType(currencyType);
        if(account==null){
            throw new IllegalArgumentException("****** toAccount parameter can't be null!! ******");
        }
        this.name = name;
        this.account = account;
        account.addTransaction(this);
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account toAcc) {
        this.account = toAcc;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        if(!CurrencyTypes.isType(currencyType)){
            throw new IllegalArgumentException("Not a currency type: \'"+currencyType+"\' !!");
        }
        this.currencyType = currencyType;
    }

    @Override
    public String toString(){
        return new StringBuilder()
                .append("Transaction{ id=").append(id).append(", name=").append(name)
                .append(", account=").append(account).append(", money=").append(money)
                .append(", currency=").append(currencyType).toString();
    }

}
