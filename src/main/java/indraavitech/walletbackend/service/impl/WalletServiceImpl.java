package indraavitech.walletbackend.service.impl;

import indraavitech.walletbackend.domain.Result;
import indraavitech.walletbackend.domain.Wallet;
import indraavitech.walletbackend.repository.WalletRepository;
import indraavitech.walletbackend.repository.impl.WalletRepositoryImpl;
import indraavitech.walletbackend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService
{
    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepositoryImpl walletRepository) {
        this.walletRepository = walletRepository;
    }


    @Override
    public Result getCoinsMinMaxValue(String name) throws IOException {
        Optional<Wallet> optionalWallet = this.walletRepository.getWalletByName(name);

        if(optionalWallet.isEmpty())
            return null;

        Wallet wallet = optionalWallet.get();

        wallet.getCoins().sort(Integer::compareTo);

        if(wallet.getCoins().size() == 1)
            return new Result(wallet.getName(), wallet.getCoins().size(), wallet.getCoins().get(0), null);
        else if(wallet.getCoins().size() == 0)
            return new Result(wallet.getName(), wallet.getCoins().size(), null, null);

        return new Result(wallet.getName(), wallet.getCoins().size(), wallet.getCoins().get(0), wallet.getCoins()
                .get(wallet.getCoins().size()-1));

    }

    @Override
    public Result getBanknotesMinMaxValue(String name) throws IOException {
        Optional<Wallet> optionalWallet = this.walletRepository.getWalletByName(name);

        if(optionalWallet.isEmpty())
            return null;

        Wallet wallet = optionalWallet.get();

        wallet.getBanknotes().sort(Integer::compareTo);

        if(wallet.getBanknotes().size() == 1)
            return new Result(wallet.getName(), wallet.getBanknotes().size(), wallet.getBanknotes().get(0), null);
        else if(wallet.getCoins().size() == 0)
            return new Result(wallet.getName(), wallet.getBanknotes().size(),null, null);

        return new Result(wallet.getName(),wallet.getBanknotes().size(), wallet.getBanknotes().get(0),
                wallet.getBanknotes().get(wallet.getBanknotes().size()-1));
    }
}
