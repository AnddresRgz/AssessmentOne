package specifications.transaction;

import specifications.ISpecification;

import java.time.LocalDate;

public class FindTransactionsByDate implements ISpecification<Transaction> {

    private LocalDate initDate;
    private LocalDate endDate;

    public FindTransactionsByDate(LocalDate initDate, LocalDate endDate){
        this.initDate = initDate;
        this.endDate = endDate;
    }
    @Override
    public boolean isExists(Transaction transaction) {
        return  !(transaction.getCreateAt().isBefore(initDate) || transaction.getCreateAt().isAfter(endDate)) ;
    }
}
