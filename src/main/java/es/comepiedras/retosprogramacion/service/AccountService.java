package es.comepiedras.retosprogramacion.service;

import es.comepiedras.retosprogramacion.model.Account;
import es.comepiedras.retosprogramacion.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Optional<Account> fromAccountOpt = accountRepository.findById(fromAccountId);
        Optional<Account> toAccountOpt = accountRepository.findById(toAccountId);

        if(fromAccountOpt.isEmpty() || toAccountOpt.isEmpty()) {
            throw new RuntimeException("Una de las cuentas no existe");
        }

        Account fromAccount = fromAccountOpt.get();
        Account toAccount = toAccountOpt.get();

        if(fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));

        if(amount.compareTo(new BigDecimal("1000")) > 0) {
            throw new RuntimeException("Error simulado para rollback");
        }

        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
