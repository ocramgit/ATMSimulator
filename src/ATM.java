import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private List<Observer> observers = new ArrayList<>();
    private long cardNumber = 0;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void insertCard(User user) {
        if (user == null) throw new InvalidCardException("Invalid card.");
        cardNumber = user.getCardNumber();
    }

    public void transfer(User sender, User receiver, int balance) throws IOException {
        if (cardNumber != sender.getCardNumber()) throw new InvalidCardException("Card not found.");

        if (sender.getBalance() < balance) throw new NotEnoughFundsException("You don't have enough funds.");

        sender.setBalance(sender.getBalance() - balance);
        receiver.setBalance(receiver.getBalance() + balance);

        addObserver(sender);
        notifyObservers("Sent "+ balance + " to "+receiver.getName());
        removeObserver(sender);

        addObserver(receiver);
        notifyObservers("You received "+balance+ " from "+ sender.getName());
        removeObserver(receiver);

        LogWriter.write(sender.getName() + " sent "+ balance + " to "+receiver.getName());
    }

    public void deposit(User user, int money) throws IOException {
        if (cardNumber != user.getCardNumber()) throw new InvalidCardException("Card not found.");

        addObserver(user);
        notifyObservers(money + " has been deposited to your account.");

        user.setBalance(user.getBalance()+money);
        LogWriter.write(user.getName() + " deposited "+money+ " to his account.");
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public static void main(String[] args) throws IOException {
        UserCreator userCreator = new UserCreator();
        userCreator.read();

        User user1 = UserCreator.getUsers().get(0);
        User user2 = UserCreator.getUsers().get(1);

        ATM atm = new ATM();
        atm.insertCard(user1);
        atm.transfer(user1, user2, 100);
        atm.deposit(user1, 200);
    }
}