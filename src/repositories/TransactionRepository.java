package repositories;

import specifications.ISpecification;
import model.Transaction;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TransactionRepository implements IRepository<Transaction> {

    private final List<Transaction> transactions;
    private final Random random = new Random();

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

    public Transaction getTransactionBy(ISpecification<Transaction> spec){
        return transactions.stream()
                .filter(spec::isExists)
                .findFirst()
                .orElse(null);
    }

    public List<Transaction> getAllTransactionsBy(ISpecification<Transaction> spec){
        return transactions.stream()
                .filter(spec::isExists)
                .collect(Collectors.toList());
    }

    public double getTransactionsSum(ISpecification<Transaction> spec){
        return transactions.stream()
                .filter(spec::isExists)
                .map(Transaction::getAmount)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public Transaction getRandomTransaction(){
        return transactions.get(random.nextInt(transactions.size()));
    }

}
