package specifications;

import model.Transaction;

import java.time.LocalDate;

public class FindTransactionsByDate implements ISpecification<Transaction> {

    private final LocalDate initDate;
    private final LocalDate endDate;

    public FindTransactionsByDate(LocalDate initDate, LocalDate endDate){
        this.initDate = initDate;
        this.endDate = endDate;
    }
    @Override
    public boolean isExists(Transaction transaction) {
        return  !(transaction.getCreateAt().isBefore(initDate) || transaction.getCreateAt().isAfter(endDate)) ;
    }
}
