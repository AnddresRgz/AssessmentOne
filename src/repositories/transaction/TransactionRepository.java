package repositories.transaction;

import repositories.IRepository;
import specifications.ISpecification;
import model.Transaction;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TransactionRepository implements IRepository<Transaction> {

    private List<Transaction> transactions;

    public TransactionRepository(List<Transaction> transactions){
        this.transactions = transactions;
    }

    @Override
    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return transactions;
    }

    public Transaction getTransactionBy(ISpecification spec){
        return transactions.stream().filter(x -> spec.isExists(x)).findAny().orElse(null);
    }

    public List<Transaction> getAllTransactionsBy(ISpecification spec){
        return transactions.stream().filter(x -> spec.isExists(x)).collect(Collectors.toList());
    }

    public double getTransactionsSum(ISpecification spec){
        return transactions.stream().filter(x -> spec.isExists(x))
                .map(x -> x.getAmount()).collect(Collectors.summingDouble(Double::doubleValue));
    }

    public Transaction getRandomTransaction(){
        return transactions.get(new Random().nextInt(transactions.size()));
    }

    @Override
    public void update(Transaction oldObj, Transaction newObj) {

    }

    @Override
    public void delete(Transaction transaction) {

    }
}
