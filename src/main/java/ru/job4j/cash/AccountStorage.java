package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.put(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.put(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        Optional<Account> result = Optional.empty();
        if (accounts.containsKey(id)) {
            result = Optional.of(accounts.get(id));
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> fromAccount = getById(fromId);
        Optional<Account> toAccount = getById(toId);
        if (fromAccount.isEmpty()) {
            throw new IllegalStateException("Not found account by id = 1");
        }
        if (toAccount.isEmpty()) {
            throw new IllegalStateException("Not found account by id = 2");
        }
        if (fromAccount.get().amount() < amount) {
            throw new IllegalStateException("The amount on the account is less than requested");
        }
        return update(new Account(fromId, fromAccount.get().amount() - amount))
                && update(new Account(toId, toAccount.get().amount() + amount));
    }
}
