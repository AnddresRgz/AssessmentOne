package storage;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionStorage {

    private List<Transaction> transactions;

    private static TransactionStorage instance;

    private TransactionStorage(){
        transactions = new ArrayList<>();
    }

    public static TransactionStorage getInstance(){
        if( instance == null) {
            instance = new TransactionStorage();
        }
        return instance;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
