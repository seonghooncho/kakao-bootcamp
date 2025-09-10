import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bank implements Runnable{
    List<BankAccount> bankAccounts;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    public Bank(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
    public synchronized void execute(){
        for(BankAccount bankAccount : bankAccounts){
            bankAccount.calculateBalance();
            if(bankAccount instanceof SavingAccount){
                SavingAccount savingAccount = (SavingAccount)bankAccount;
                savingAccount.autoDeposit();
            }
        }
    }
    @Override
    public void run() {
        execute();
    }

    public void batch(){
        scheduler.scheduleWithFixedDelay(this,1,1, TimeUnit.MINUTES);
    }

    public void exit(){
        scheduler.shutdown();
    }

    public String showAccounts(){
        String result = "";
        for(BankAccount bankAccount : bankAccounts){
            result += bankAccount.accountName+" 정보 \n ";
            result += "거치금 : " + bankAccount.balance.toString();
            result += "이율 : "+ Double.toString(bankAccount.interestRate)+"\n\n";
        }
        return result;
    }

    public boolean depositToDepositAccount(long cash){
        for(BankAccount bankAccount : bankAccounts){
            if(bankAccount instanceof DepositAccount){
                return bankAccount.deposit(cash);
            }
        }return false;
    }

    public boolean withdrawFromDepositAccount(long cash){
        for(BankAccount bankAccount : bankAccounts){
            if(bankAccount instanceof DepositAccount){
                return bankAccount.withdraw(cash);
            }
        }return false;
    }
    public void setAutoDepositAmount(String name, long cash){
        for(BankAccount bankAccount : bankAccounts){
            if(!bankAccount.accountName.equals(name)){
                continue;
            }
            if(bankAccount instanceof SavingAccount){
                SavingAccount savingAccount = (SavingAccount)bankAccount;
                savingAccount.setAutoDepositAmount(cash);
            }
        }
    }

}
