class BankAccount {
    private double balance;

    public double getBalance() {
        return this.balance;
    }

    public double setBalance(double balance) {
        return this.balance = balance;
    }
}

class Encapsulation {

    public static void main(String[] args) {
        BankAccount user1 = new BankAccount();

        user1.setBalance(12);
        System.out.println(user1.getBalance());
    }
}