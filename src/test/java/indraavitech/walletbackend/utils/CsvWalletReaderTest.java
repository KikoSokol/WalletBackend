package indraavitech.walletbackend.utils;

import indraavitech.walletbackend.domain.Wallet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvWalletReaderTest
{
    CsvWalletReader reader = new CsvWalletReader();

    @Test
    public void isCorrectFile()
    {

        try {
            List<Wallet> wallets = reader.getAllWallets("data_for_test\\test_data_1.csv");
            wallets.forEach(wallet -> {
                System.out.println(wallet.getName());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void notFoundFile()
    {

        try {
            List<Wallet> wallets = reader.getAllWallets("data_for_test\\test.csv");
            wallets.forEach(wallet -> {
                System.out.println(wallet.getName());
            });
        } catch (IOException e) {
            assert false;
        }
    }

    @Test
    public void withoutName()
    {
        try {
            List<Wallet> wallets = reader.getAllWallets("data_for_test\\test_data_2.csv");
        } catch (IOException e) {
            assert false;
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            Assert.assertEquals("Bad data in CSV. In CSV data is wallet with empty name.",
                    illegalArgumentException.getMessage());
        }
    }

    @Test
    public void moneyIsLetterOnly()
    {
        try {
            List<Wallet> wallets = reader.getAllWallets("data_for_test\\test_data_3.csv");
            wallets.forEach(wallet -> {
                System.out.println(wallet.getName());
            });
        } catch (IOException e) {
            assert false;
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            Assert.assertEquals("Bad data in CSV. In CSV data is invalid kind of money",
                    illegalArgumentException.getMessage());
        }
    }

    @Test
    public void badMoneyFormat()
    {
        try {
            List<Wallet> wallets = reader.getAllWallets("data_for_test\\test_data_4.csv");
            wallets.forEach(wallet -> {
                System.out.println(wallet.getName());
            });
        } catch (IOException e) {
            assert false;
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            Assert.assertEquals("Bad data in CSV. In CSV data is invalid kind of money",
                    illegalArgumentException.getMessage());
        }
    }
}
