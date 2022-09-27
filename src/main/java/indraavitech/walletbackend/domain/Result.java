package indraavitech.walletbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result
{
    private String name;
    private int count;
    private Integer min;
    private Integer max;


}
