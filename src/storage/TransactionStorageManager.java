package storage;

import com.sun.tools.javac.Main;
import model.Transaction;
import utils.Constants;
import utils.LoadTransactionsException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionStorageManager {
    public static void fillStorage() throws LoadTransactionsException {
        Scanner sc = null;
        List<Transaction> transactions = new ArrayList<>();
        Path filePath = Paths.get(Main.class.getResource(Constants.MOCK_DATA_PATH).getPath());
            try {
                sc = new Scanner(filePath);
                sc.useDelimiter(Constants.CSV_DELIMITER);
                fillList(sc, transactions);
            } catch (IOException e) {
                throw new LoadTransactionsException(
                        MessageFormat.format("Couldn't load data from {0}", Constants.MOCK_DATA_PATH), e);
            } finally {
                if(sc != null){
                    sc.close();
                }
            }
            TransactionStorage.getInstance().setTransactions(transactions);
    }

    private static void fillList(Scanner sc, List<Transaction> transactions){
        String description;
        double amount;
        int id;
        LocalDate date;
        while(sc.hasNext()){
            description = sc.next();
            amount = sc.nextDouble();
            id = sc.nextInt();
            date = LocalDate.parse(sc.next());
            transactions.add(new Transaction(description, amount, id, date));
        }
    }
}
