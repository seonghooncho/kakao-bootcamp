public class Member {
    String name;
    long cash=100000L;

    public Member(String name,Long cash){
        this.name=name;
        this.cash=cash;
    }
    public long getCash(){
        return cash;
    }
    public void setCash(Long cash){
        this.cash=cash;
    }
}
