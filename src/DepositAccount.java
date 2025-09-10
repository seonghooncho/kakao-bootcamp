public class DepositAccount extends BankAccount{
    public DepositAccount(String accountName, Long balance, double interestRate, Member member){
        super(accountName,balance,interestRate, member);
    }
}
