package specifications;

import specifications.ISpecification;

import java.util.UUID;

public class FindTransactionByUserId implements ISpecification<Transaction> {

    private UUID transactionId;
    private int userId;

    public FindTransactionByUserId(UUID transactionId, int userId){
        this.transactionId = transactionId;
        this.userId = userId;
    }

    @Override
    public boolean isExists(Transaction transaction) {
        return transaction.getUserId() == userId &&
                transaction.getId().equals(transactionId);
    }
}
