package indraavitech.walletbackend.repository;

import indraavitech.walletbackend.domain.Wallet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface WalletRepository
{
    List<Wallet> getAllWallets() throws IOException, NullPointerException, FileNotFoundException;
    Optional<Wallet> getWalletByName(String name) throws IOException, NullPointerException, FileNotFoundException;
}
