package indraavitech.walletbackend.service;

import indraavitech.walletbackend.domain.Result;

import java.io.IOException;

public interface WalletService
{
    Result getCoinsMinMaxValue(String name) throws IOException;
    Result getBanknotesMinMaxValue(String name) throws IOException;
}
