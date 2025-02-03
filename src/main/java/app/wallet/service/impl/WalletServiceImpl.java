package app.wallet.service.impl;

import app.user.model.User;
import app.wallet.Wallet;
import app.wallet.WalletStatus;
import app.wallet.dao.WalletRepository;
import app.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet createNewWallet() {
        Wallet wallet = Wallet.builder()
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .balance(new BigDecimal("20.00"))
                .status(WalletStatus.ACTIVE)
                .currency(Currency.getInstance("EUR"))
                .build();

        walletRepository.save(wallet);

        return wallet;
    }

}
