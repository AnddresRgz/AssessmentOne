import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private String description;
    private double amount;
    private LocalDate createAt;
    private int userId;

    public Transaction(String description, double amount, int userId, LocalDate createAt) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.amount = amount;
        this.createAt = createAt;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("UUID: %s, Description: %s, Amount: %s, Create At: %s, User Id: %s", getId(),
                getDescription(), getAmount(), getCreateAt(), getUserId());
    }
}
