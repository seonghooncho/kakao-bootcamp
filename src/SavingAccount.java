public class SavingAccount extends BankAccount {
    long autoDepositAmount=0L;
    public SavingAccount(String accountName, Long balance, double interestRate, Member member) {
        super(accountName,balance,interestRate, member);
    }

    public void setAutoDepositAmount(long autoDepositAmount) {
        this.autoDepositAmount = autoDepositAmount;
    }

    public void autoDeposit(){
        this.deposit(autoDepositAmount);
    }
}
