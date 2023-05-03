package specifications;


import model.Transaction;

import java.util.UUID;

public class FindTransactionByUserId implements ISpecification<Transaction> {

    private final UUID transactionId;
    private final int userId;

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
