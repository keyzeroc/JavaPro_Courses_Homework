package hometask.bank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<Account>();

    public Client(){}

    public Client(String name, int age){
        this.name=name;
        this.age=age;
    }
    public Double getBalance(String preferredType){
        if(!CurrencyTypes.isType(preferredType)){
            throw new IllegalArgumentException("Wrong argument: \'"+preferredType+"\' not a currency type!");
        }
        double sum=0;
        for (Account acc:accounts) {
            double coef=CourseContainer.searchCourse(acc.getCurrencyType(),preferredType).getCoefficient();
            sum+=coef*acc.getBalance();
        }
        return sum;
    }
    public void addAccount(Account account){
        if(accounts.contains(account))
            return;
        accounts.add(account);
        account.setClient(this);
    }
    public Account getAccount(int index){
        return accounts.get(index);
    }
    public List<Account> getAccounts(){
        return accounts;
    }
    public void clearAccounts(){
        accounts.clear();
    }
    public void setAccounts(List<Account> accounts){
        this.accounts=accounts;
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
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Client{ id=").append(id).append(", name=")
                .append(name).append(", age=").append(age).append(" }").toString();
    }
}
