package indraavitech.walletbackend.utils;

public class CsvWalletDataValidator
{
    private final String separator;

    public CsvWalletDataValidator(String separator) {
        this.separator = separator;
    }


    public TypeOfMoney kindOfMoney(String item)
    {
        if(isNumber(item))
            return TypeOfMoney.BANKNOTE;

        if(item.endsWith("m"))
        {
            if(isNumber(item.substring(0,item.length()-1)))
                return TypeOfMoney.COIN;
            else
                return TypeOfMoney.NO_MONEY;
        }

        return TypeOfMoney.NO_MONEY;
    }

    private boolean isNumber(String item)
    {
        try
        {
            int a = Integer.parseInt(item);
        }
        catch(NumberFormatException numberFormatException)
        {
            return false;
        }
        return true;
    }

    public boolean isCorrectData(String line)
    {
        String[] splitString = line.split(separator);

        if(splitString[0].trim().equals(""))
            throw new IllegalArgumentException("Bad data in CSV. In CSV data is wallet with empty name.");

        for(int i = 1; i < splitString.length; i++)
        {
            if(kindOfMoney(splitString[i]) == TypeOfMoney.NO_MONEY)
            {
                throw new IllegalArgumentException("Bad data in CSV. In CSV data is invalid kind of money");
            }
        }

        return true;
    }
}
