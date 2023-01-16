package main;
@FunctionalInterface
public interface BankTransactionFilter {
    /**
     * @param bankTransaction
     * @return
     */
    boolean test(BankTransaction bankTransaction);
}
