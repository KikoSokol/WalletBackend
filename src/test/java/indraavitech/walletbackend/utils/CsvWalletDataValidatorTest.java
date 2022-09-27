package indraavitech.walletbackend.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvWalletDataValidatorTest
{

    CsvWalletDataValidator validator = new CsvWalletDataValidator(",");

    @Test
    public void isCoin()
    {
        String money = "10m";

        Assert.assertEquals(TypeOfMoney.COIN, validator.kindOfMoney(money));
    }

    @Test
    public void isBanknote()
    {
        String money = "10";

        Assert.assertEquals(TypeOfMoney.BANKNOTE, validator.kindOfMoney(money));
    }

    @Test
    public void isNoMoney1()
    {
        String money = "10mn";

        Assert.assertEquals(TypeOfMoney.NO_MONEY, validator.kindOfMoney(money));
    }

    @Test
    public void isNoMoney2()
    {
        String money = "mn";

        Assert.assertEquals(TypeOfMoney.NO_MONEY, validator.kindOfMoney(money));
    }

    @Test
    public void isCorrectLine()
    {
        String line = "Dusan;20;5m;10;2m;1m;50;70;25;12;4mm";

        assert validator.isCorrectData(line);
    }

    @Test
    public void noCorrectLine()
    {
        String line = "Dusan;20;5m;10;2m;1m;50;70;25;12;mm";

        try{
            validator.isCorrectData(line);
            System.out.println("aaa");
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            Assert.assertNotEquals("Bad data in CSV. In CSV data is invalid kind of money", illegalArgumentException.getMessage());
        }

    }

}
