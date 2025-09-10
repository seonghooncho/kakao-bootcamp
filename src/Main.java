import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Member member;
    public static void main(String[] args) {
        member = new Member("조성훈", 100000l);
        List<BankAccount> bankAccounts = new ArrayList<>();

        bankAccounts.add( new DepositAccount("예금", 0l, 1.0, member));
        bankAccounts.add(new SavingAccount("적금", 0l, 2.0, member));
        bankAccounts.add(new SavingAccountWithPromotion("적금(신규가입이벤트)", 0l, 2.0, member,10000l));

        Bank bank = new Bank(bankAccounts);
        boolean running = true;
        bank.batch();
        System.out.println("계좌 프로젝트입니다");
        Scanner sc = new Scanner(System.in);
        while(running){
            printWithMenu("아래 메뉴 중에서 선택해주세요");
            String input = sc.nextLine();
            switch (input){
                case "1"->{
                    printWithMenu(bank.showAccounts());
                }case "2"->{
                    System.out.println("송금할 금액을 입력해주세요");
                    String cashStr = sc.nextLine();
                    if (input.matches("\\d+")) {
                        long cash = Long.parseLong(cashStr);
                        if(!bank.depositToDepositAccount(cash)){
                            System.out.println("입금한도 초과");
                        }
                    } else {
                        System.out.println("숫자만 입력하세요");
                    }
                }case "3"->{
                    System.out.println("출금할 금액을 입력해주세요");
                    String cashStr = sc.nextLine();
                    if (cashStr.matches("\\d+")) {
                        long cash = Long.parseLong(cashStr);
                        if(!bank.withdrawFromDepositAccount(cash)){
                            System.out.println("출금한도 초과");
                        }
                    } else {
                        System.out.println("숫자만 입력하세요");
                    }
                }case "4"->{
                    System.out.println("적금 이름을 입력하세요");
                    String accountName = sc.nextLine();
                    System.out.println("자동이체 금액을 입력하세요");
                    String autoDepositStr = sc.nextLine();
                    if (autoDepositStr.matches("\\d+")) {
                        long autoDeposit = Long.parseLong(autoDepositStr);
                        bank.setAutoDepositAmount(accountName, autoDeposit);
                    } else {
                        System.out.println("숫자만 입력하세요");
                    }

                }
            }
        }
        bank.exit();

    }
    static void printWithMenu(String text){
        System.out.println("가지고있는 현금 :"+Long.toString(member.getCash()));
        System.out.println("===================================");
        System.out.println(text);
        System.out.println("===================================");
        System.out.println("\n[cli key menu]\n내 계좌보기:1, 예금에 송금하기:2, 예금에서 출금하기:3, 적금 송금액 변경하기:4");
        System.out.print("입력: ");
    }
}