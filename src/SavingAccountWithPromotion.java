public class SavingAccountWithPromotion extends SavingAccount {
    long promotionAmount;
    boolean hasCreation = false;
    public SavingAccountWithPromotion(String accountName, Long balance, double interestRate, Member member, Long promotionAmount) {
        super(accountName, balance, interestRate, member);
        this.promotionAmount = promotionAmount;
    }
    @Override
    public void setAutoDepositAmount(long autoDepositAmount) {
        long tempAutoDepositAmount = autoDepositAmount;
        if(!hasCreation){
            tempAutoDepositAmount = promotionAmount+autoDepositAmount;
        }
        this.autoDepositAmount =tempAutoDepositAmount;
    }

}
