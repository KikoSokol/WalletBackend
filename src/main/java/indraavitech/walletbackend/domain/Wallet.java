package indraavitech.walletbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Wallet
{
    private String name;
    private List<Integer> coins;
    private List<Integer> banknotes;


}
