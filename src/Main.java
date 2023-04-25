import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        fillStorage();
        GUI gui = new GUI(new TransactionRepository(TransactionStorage.getInstance().getTransactions()));
        gui.start();
    }

    private static  void fillStorage(){
        List<Transaction> transactions = new ArrayList<>();
            try {
                String description;
                double amount;
                int id;
                LocalDate date;
                Scanner sc = new Scanner(new File(Main.class.getResource("MOCK_DATA.csv").getPath()));
                sc.useDelimiter(",|\n");
                while(sc.hasNext()){
                    description = sc.next();
                    amount = sc.nextDouble();
                    id = sc.nextInt();
                    date = LocalDate.parse(sc.next());
                    transactions.add(new Transaction(description, amount, id, date));
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        TransactionStorage.getInstance().setTransactions(transactions);
    }
}
