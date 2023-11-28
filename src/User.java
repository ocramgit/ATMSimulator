import java.io.Serializable;

public class User implements Serializable, Observer {
    private String name;
    private long cardNumber;
    private long balance;
    private boolean isCardBlocked;

    public User(String myUser) {
        String[] userDetails = myUser.split("-");
        this.name = userDetails[0].trim();
        this.cardNumber = Integer.parseInt(userDetails[1].trim());
        this.balance = Integer.parseInt(userDetails[2].trim());
    }

    public User(String name, long cardNumber, long balance) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public long insertCard() {
        return this.cardNumber;
    }

    @Override
    public String toString() {
        return name+"-"+cardNumber+"-"+balance;
    }

    public String getName() {
        return name;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " recebeu a seguinte noticicação: " + message);
    }
}
