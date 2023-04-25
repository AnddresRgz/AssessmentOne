public class FindTransactionsByUserId implements ISpecification<Transaction> {

    private int userId;

    public FindTransactionsByUserId(int userId){
        this.userId = userId;
    }

    @Override
    public boolean isExists(Transaction transaction) {
        return transaction.getUserId() == userId;
    }
}
