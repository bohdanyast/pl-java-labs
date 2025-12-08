package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            accounts.add(new Account(random.nextInt(4500) + 15));
        }

        int initialBalance = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("К-сть грошей в банку до переказів:  " + initialBalance);

        List<Thread> threads = getThreads(random, accounts, bank);
        for (Thread thread : threads) {
            thread.join();
        }

        int finalBalance = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("К-сть грошей в банку після переказів: " + finalBalance);

        System.out.println("Чи рівна к-сть? " + (initialBalance == finalBalance));
    }

    private static List<Thread> getThreads(Random random, List<Account> accounts, Bank bank) {
        List<Thread> threads = new ArrayList<>();
        int accSize = accounts.size();

        for (int i = 0; i < 5000; i++) {
            Thread thread = new Thread(() -> {
                int fromIndex = random.nextInt(accSize);
                int toIndex = random.nextInt(accSize);

                Account from = accounts.get(fromIndex);
                Account to = accounts.get(toIndex);

                int amount = random.nextInt(from.getBalance() + 1);

                bank.transfer(from, to, amount);
            });
            threads.add(thread);
            thread.start();
        }

        return threads;
    }
}