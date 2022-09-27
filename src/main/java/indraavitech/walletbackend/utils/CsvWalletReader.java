package indraavitech.walletbackend.utils;

import indraavitech.walletbackend.domain.Wallet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvWalletReader
{
    private static final String SEPARATOR = ";";

    private final CsvWalletDataValidator validator;

    public CsvWalletReader()
    {
        this.validator = new CsvWalletDataValidator(SEPARATOR);
    }

    public List<String> readAllLinesFromCsv(String pathToCsv) throws IOException, NullPointerException, FileNotFoundException {
        List<String> lines = new ArrayList<>();

        BufferedReader bufferedCsvReader = null;
        try
        {
            File csvFile = new File(pathToCsv);
            FileReader csvFileReader = new FileReader(csvFile);
            bufferedCsvReader = new BufferedReader(csvFileReader);
            String line = "";
            while ((line = bufferedCsvReader.readLine()) != null)
            {
                lines.add(line.trim());
            }
        }
        catch (NullPointerException | FileNotFoundException nullPointerException)
        {
            throw  nullPointerException;
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if(bufferedCsvReader != null)
                bufferedCsvReader.close();
        }


        return lines;
    }

    private String extractName(String line)
    {
        return line.split(SEPARATOR)[0].trim();
    }


    public List<Wallet> getAllWallets(String pathToCsv) throws IOException, NullPointerException, FileNotFoundException{
        List<String> lines = readAllLinesFromCsv(pathToCsv);

        List<Wallet> wallets = new ArrayList<>();

        for(String line : lines)
        {
            boolean isCorrectData = this.validator.isCorrectData(line);

            if(isCorrectData)
            {
                String name = extractName(line);
                List<Integer> coins = new ArrayList<>();
                List<Integer> banknotes = new ArrayList<>();

                String[] splitLine = line.split(SEPARATOR);
                for(int i = 1; i < splitLine.length; i++)
                {
                    if(splitLine[i].endsWith("m"))
                    {
                        String stringCoin = splitLine[i].substring(0,splitLine[i].length() - 1);
                        coins.add(Integer.parseInt(stringCoin));
                    }
                    else
                        banknotes.add(Integer.parseInt(splitLine[i]));
                }

                wallets.add(new Wallet(name, coins, banknotes));
            }
        }

        return wallets;
    }
}
