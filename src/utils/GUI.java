package utils;

import specifications.FindTransactionByUserId;
import specifications.FindTransactionsByDate;
import specifications.FindTransactionsByUserId;
import model.Transaction;
import model.TransactionReport;
import repositories.TransactionRepository;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class GUI {
    private final TransactionRepository repository;
    private final Scanner sc;
    public GUI(TransactionRepository repository){
        this.repository = repository;
        sc = new Scanner(System.in).useDelimiter(Constants.GUI_DELIMITER);
    }

    public void start(){
        int opt = 0;
        UUID transactionId;
        int userId;
        LocalDate createDate;
        double amount;
        String desc;
        do{
            showOptions();
            opt = sc.nextInt();
            switch(opt){
                case 1:
                    System.out.println("Enter description:");
                    desc = sc.next();
                    System.out.println("Enter amount:");
                    amount = sc.nextDouble();
                    System.out.println("Enter user ID:");
                    userId = sc.nextInt();
                    System.out.println("Enter create date (yyyy-mm-dd):");
                    createDate = LocalDate.parse(sc.next());
                    add(desc, amount, userId, createDate);
                    break;
                case 2:
                    System.out.println("Enter transaction ID:");
                    transactionId = UUID.fromString(sc.next());
                    System.out.println("Enter user ID:");
                    userId = sc.nextInt();
                    verifyTransaction(transactionId, userId);
                    break;
                case 3:
                    System.out.println("Enter user ID:");
                    userId = sc.nextInt();
                    showAllTransactionsByUser(userId);
                    break;
                case 4:
                    System.out.println("Enter user ID:");
                    userId = sc.nextInt();
                    showSum(userId);
                    break;
                case 5:
                    System.out.println(repository.getRandomTransaction().toString());
                    break;
                case 6:
                    System.out.println("Monthly Report. Type date with next format (yyyy-mm-dd):");
                    createDate = LocalDate.parse(sc.next());
                    showReport( createDate );
                    break;
                case 7:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Chosen option doesn't exist. Choose a valid one.");
            }

        }while (opt != 7);
        sc.close();
    }

    private void add(String desc, double amount, int userId, LocalDate createDate){
        repository.add(new Transaction(desc, amount, userId, createDate));
    }

    private void verifyTransaction(UUID transactionId, int userId){
        Transaction transaction = repository.getTransactionBy(new FindTransactionByUserId(transactionId, userId));
        if( transaction == null ){
            System.out.println("Transaction not found");
        }else{
            System.out.println(transaction.toString());
        }
    }

    private void showAllTransactionsByUser(int userId){
        List<Transaction> transactions = repository.getAllTransactionsBy(new FindTransactionsByUserId(userId));
        if(transactions.isEmpty()){
            System.out.println("User doesn't exist");
        }else{
            transactions.stream().forEach(x -> System.out.println(x.toString()));
        }
    }

    private void showSum(int userId){
        double amount = repository.getTransactionsSum(new FindTransactionsByUserId(userId));
        System.out.println(MessageFormat.format("User: {0}, Sum: {1}", userId, amount) );
    }

    private void showReport(LocalDate date){
        List<TransactionReport> reports = TransactionReport.calculateWeeks(date);
        for (TransactionReport report: reports) {
            report.setQuantity( repository.getAllTransactionsBy(new FindTransactionsByDate(report.getInitDate(), report.getEndDate())).size() );
        }
        reports.forEach(x -> System.out.println(x.toString()));
    }

    private void showOptions(){
        System.out.println("1- Create model.Transaction.\n2.- Verify user transaction.\n3.- Show all transactions from user." +
                "\n4.- Show user total sales amount.\n5.- Get random transaction.\n6.- Show monthly report.\n7.- Exit ");
    }

}
