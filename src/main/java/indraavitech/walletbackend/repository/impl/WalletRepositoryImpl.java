package indraavitech.walletbackend.repository.impl;

import indraavitech.walletbackend.domain.Wallet;
import indraavitech.walletbackend.repository.WalletRepository;
import indraavitech.walletbackend.utils.CsvWalletReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class WalletRepositoryImpl implements WalletRepository
{
    @Value("data.csv")
    private String path;

    private final CsvWalletReader csvWalletReader;


    public WalletRepositoryImpl()
    {
        this.csvWalletReader = new CsvWalletReader();
    }

    @Override
    public List<Wallet> getAllWallets() throws IOException, NullPointerException, FileNotFoundException {
        return this.csvWalletReader.getAllWallets(path);
    }

    @Override
    public Optional<Wallet> getWalletByName(String name) throws IOException, NullPointerException, FileNotFoundException {
        System.out.println(path);
        List<Wallet> allWallets = this.getAllWallets();

        for(Wallet wallet : allWallets)
        {
            if(wallet.getName().equals(name))
                return Optional.of(wallet);
        }
        return Optional.empty();
    }
}
