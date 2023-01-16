package main;
import java.util.ArrayList;
import java.util.List;
import java.time.Month;

public class BankStatmentProcessor {
    private final List<BankTransaction> bankTransactions;
    
    public BankStatmentProcessor(final List<BankTransaction> bankTransactions){
        this.bankTransactions=bankTransactions;

    }
    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer){
        double result=0;
        for(final BankTransaction bankTransaction:bankTransactions){
            result=bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalAmount(){
        double total=0;
        for(final BankTransaction bankTransaction:bankTransactions){
                total +=bankTransaction.getAmount();
        }
        return total;
    }
    public double calculateTotalInMonth(final Month month){
        return summarizeTransactions((acc,bankTransaction)->
                bankTransaction.getDate().getMonth()==month ? acc + bankTransaction.getAmount():acc)    ;
        }
     
        
    public double calculateTotalForCategory(final String category) {
        double total=0;
        for(final BankTransaction bankTransaction:bankTransactions){
            if(bankTransaction.getDescription().equals(category))
                total+=bankTransaction.getAmount();
        }
        return total;
        
    }
   

    /**
     * @param IntialAmmount
     * @param FinalAmount
     * @return
     */
    public List<BankTransaction>findTransactionsBetween(final int IntialAmmount,final int FinalAmount){
        int Initial,Final;
        Initial=IntialAmmount;
        Final=FinalAmount;
        if(Initial>Final){
            int temp=Final;
            Final=Initial;
            Initial=temp;
        }
        final List<BankTransaction>result=new ArrayList<>();
        for(final BankTransaction bankTransaction:bankTransactions){
            if(bankTransaction.getAmount()<=Final && bankTransaction.getAmount()>=Initial){
                    result.add(bankTransaction);
            }
        }
        return result;
    }
   
    /**
     * @param bankTransactionFilter
     * @return
     */
    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter){
        final List<BankTransaction> result=new ArrayList<>();
        for(final BankTransaction bankTransaction :bankTransactions){
            if(bankTransactionFilter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }
        return result;
    }
    /**
     * @param amount
     * @return
     */
    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount){
        return findTransactions(bankTransaction->bankTransaction.getAmount()>=amount);
    }
}
