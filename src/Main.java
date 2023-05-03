import repositories.TransactionRepository;
import storage.TransactionStorage;
import storage.TransactionStorageManager;
import utils.GUI;
import utils.LoadTransactionsException;

public class Main {

    public static void main(String[] args) {
        try{
            TransactionStorageManager.fillStorage();
        }catch (LoadTransactionsException e){
            System.out.println(e.getMessage());
        }

        GUI gui = new GUI(new TransactionRepository(TransactionStorage.getInstance().getTransactions()));
        gui.start();
    }


}
