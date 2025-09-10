public abstract class BankAccount {
    Member member;
    String accountName;
    Long balance;
    double interestRate;

    public BankAccount(String accountName, Long balance, double interestRate, Member member) {
        this.accountName = accountName;
        this.balance = balance;
        this.interestRate = interestRate;
        this.member = member;
    }

    public boolean deposit(Long cash) {
        long remainCash =member.getCash() - cash;
        if(remainCash>=0){
            member.setCash(remainCash);
            balance += cash;
            return true;
        }
        return false;
    }
    public boolean withdraw(Long cash) {
        long remainBalance = balance - cash;
        if(remainBalance>=0){
            balance = remainBalance;
            member.setCash(member.cash+ cash);
            return true;
        }
        return false;
    }
    public void calculateBalance() {
        balance = (long)(balance*(1+ interestRate/100));
    }
}
