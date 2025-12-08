package task1;

import java.util.concurrent.locks.Lock;

public class Bank {
    public void transfer(Account from, Account to, int amount) {
        Lock lock1 = from.getLock();
        Lock lock2 = to.getLock();

        Lock firstLock = lock1.hashCode() < lock2.hashCode() ? lock1 : lock2;
        Lock secondLock = (firstLock == lock1) ? lock2 : lock1;

        firstLock.lock();
        try {
            secondLock.lock();
            try {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            } finally {
                secondLock.unlock();
            }
        } finally {
            firstLock.unlock();
        }
    }
}