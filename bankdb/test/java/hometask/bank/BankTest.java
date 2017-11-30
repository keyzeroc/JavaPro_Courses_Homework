package hometask.bank;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

public class BankTest extends BaseTest {

    private Client saveTestClient(Client client){
        return performTransaction(()->{
            em.persist(client);
            return client;
        });
    }
    private Account saveAccount(Account account){
        return performTransaction(()->{
            em.persist(account);
            return account;
        });
    }
    private Account mergeAccount(Account account){
        return performTransaction(()->{
            em.merge(account);
            return account;
        });
    }
    private Client mergeClient(Client client){
        return performTransaction(()->{
            em.merge(client);
            return client;
        });
    }
    private Transaction saveTransaction(Transaction transaction){
        return performTransaction(()->{
            em.persist(transaction);
            return transaction;
        });
    }
    /** returns converted money in (to) currency **/
    private Double convertCurrency(Double money, String from, String to){
        if(!CurrencyTypes.isType(from)||!CurrencyTypes.isType(to)) {
            throw new IllegalArgumentException("Not a currency types: \'" + to + "\' ; \'" + from + "\' !");
        }
        Double coefficient = CourseContainer.searchCourse(from,to).getCoefficient();
        return coefficient*money;
    }
    private Transaction makeATransaction(Account fromAcc, Account toAcc, Double money, String currencyType){
        if(!CurrencyTypes.isType(currencyType)){
            throw new IllegalArgumentException("Not a currency type: \'"+currencyType+"\' !!");
        }
        if(toAcc==null){
            throw new IllegalArgumentException("Illegal Argument: \'"+toAcc+"\'. field \'toAcc\' can't be null!");
        }else if(money==0||money==null){
            throw new IllegalArgumentException("Wrong argument: \'"+money+"\'. field \'money\' can't be 0 or null");
        }
        Transaction retTransac;
        if(fromAcc==null){
            Double convertedCurrency = convertCurrency(money,currencyType,toAcc.getCurrencyType());
            toAcc.setBalance(toAcc.getBalance()+convertedCurrency);
            if(money<0) {
                retTransac = new Transaction("Money taken from: "+toAcc.getId(), toAcc, money, currencyType);
            }else{
                retTransac = new Transaction("Money added to: "+toAcc.getId(), toAcc, money, currencyType);
            }

            mergeAccount(toAcc);

        }else{

            Double convertedMoney = convertCurrency(money, currencyType, fromAcc.getCurrencyType());
            if(fromAcc.getBalance()<convertedMoney){
                throw new IllegalArgumentException("Not enough money at account: "+fromAcc);
            }

            fromAcc.setBalance(fromAcc.getBalance()-convertedMoney);
            convertedMoney = convertCurrency(convertedMoney, fromAcc.getCurrencyType(), toAcc.getCurrencyType());
            toAcc.setBalance(toAcc.getBalance()+convertedMoney);

            retTransac=new Transaction("Transfer money from account: "+fromAcc.getId()+" to "+toAcc.getId(),toAcc,money,currencyType);
            fromAcc.addTransaction(retTransac);
            toAcc.addTransaction(retTransac);

            mergeAccount(fromAcc);
            mergeAccount(toAcc);
        }

//        saveTransaction(retTransac);
        return retTransac;
    }
    //-----------------------------------TESTS--------------------------------------------------------
    @Test
    public void createClientAndAccountTest() {
        Client client = saveTestClient(new Client("Vasya",20));
        Account account = saveAccount(new Account("USD",client));

        em.clear();

        final Client clientRef = em.getReference(Client.class, client.getId());
        assertNotNull(clientRef);
        assertEquals(client.getId(),clientRef.getId());
        
        System.out.println(clientRef.getAccounts());
        System.out.println(client.getAccounts());

        Account accountRef = em.getReference(Account.class,account.getId());
        assertNotNull(accountRef);
        assertEquals(client.getId(), accountRef.getClient().getId());

    }

    @Test
    public void changeCurrencyTypeTest(){
        Client client = saveTestClient(new Client("Vasya",20));
        Account account = saveAccount(new Account("USD",client));

        account.setBalance(20.); // 20$
        System.out.println(account.getBalance()+" "+account.getCurrencyType());
        mergeAccount(account);

        account.setCurrencyType("UAH");
        System.out.println(account.getBalance()+" "+account.getCurrencyType());
        mergeAccount(account);

        Account accountRef = em.getReference(Account.class,account.getId());
        assertNotNull(accountRef);
        assertEquals(account.getId(),accountRef.getId());
        assertEquals(account.getBalance(),accountRef.getBalance());
    }
    @Test
    public void depositMoneyTest(){
        Client client = saveTestClient(new Client("Vasya",20));
        Account account = saveAccount(new Account("UAH", client));

        em.clear();

        System.out.println(account);

        Transaction transaction = makeATransaction(null,account,200.,"UAH");

        System.out.println(account);
        System.out.println(transaction);

    }
    @Test
    public void withdrawMoneyTest(){
        Client client = saveTestClient(new Client("Vasya",20));
        Account account = saveAccount(new Account("EUR", client));

        Transaction deposit = makeATransaction(null,account,+10.,"EUR");
        System.out.println("\n"+deposit);
        System.out.println("\n"+account);

        Transaction withdraw = makeATransaction(null,account,-5.,"EUR");
        System.out.println("\n"+withdraw);
        System.out.println("\n"+account);
        System.out.println("\nAcc transactions: "+account.getTransactions());

        System.out.println("\nAcc transactions: "+account.getTransactions());
    }
    @Test
    public void transferMoneyTest(){
        Client client1 = saveTestClient(new Client("Vasya",20));

        Account account1 = saveAccount(new Account("USD",client1));
        Transaction transaction1 = makeATransaction(null,account1,20.,"EUR"); // adding money to acc1 transaction

        Account account2 = saveAccount(new Account("UAH",client1));
        Transaction transaction = makeATransaction(account1,account2,10.,"USD"); // transferring money from acc1 to acc2 transaction

        System.out.println("Sum is: "+client1.getBalance("UAH"));

        System.out.println(account1);
        System.out.println(account2);
        System.out.println(transaction);
    }
}
