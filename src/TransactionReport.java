import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionReport {
    private LocalDate initDate;
    private LocalDate endDate;
    private int quantity;

    public TransactionReport(LocalDate initDate, LocalDate endDate){
        this.initDate = initDate;
        this.endDate = endDate;
        this.quantity = 0;
    }
    public LocalDate getInitDate() {
        return initDate;
    }

    public void setInitDate(LocalDate initDate) {
        this.initDate = initDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static List<TransactionReport> calculateWeeks(LocalDate date){
        LocalDate initDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
        LocalDate endDate = LocalDate.of(date.getYear(), date.getMonth().plus(1), 1 );
        List<LocalDate> dates =  initDate.datesUntil(endDate).
                filter(x -> x.getDayOfMonth() == 1 || x.getDayOfMonth() == date.lengthOfMonth() ||
                        x.getDayOfWeek().getValue() == Calendar.MONDAY || x.getDayOfWeek().getValue() == Calendar.SUNDAY)
                .collect(Collectors.toList() );
        List<TransactionReport> reports = new ArrayList<>();
        for(int i = 0; i < dates.size(); i= i+2){
            reports.add(new TransactionReport(dates.get(i),dates.get(i+1)));
        }
        return reports;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Init Date: {0} - End Date: {1} - Quantity: {2}", getInitDate(), getEndDate(), getQuantity());
    }
}
