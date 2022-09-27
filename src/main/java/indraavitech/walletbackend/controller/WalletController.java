package indraavitech.walletbackend.controller;

import indraavitech.walletbackend.ErrorMessage;
import indraavitech.walletbackend.domain.Result;
import indraavitech.walletbackend.service.WalletService;
import indraavitech.walletbackend.service.impl.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("wallet")
public class WalletController
{
    private final WalletService walletService;


    @Autowired
    public WalletController(WalletServiceImpl walletService) {
        this.walletService = walletService;
    }


    @GetMapping("/{name}/min-max-banknotes-values")
    @CrossOrigin(origins = "http://localhost:3000/")
    public ResponseEntity getMinMaxBanknotesValue(@PathVariable String name)
    {
        try
        {
            Result result = this.walletService.getBanknotesMinMaxValue(name);

            if(result == null)
                return new ResponseEntity<>(new ErrorMessage("No owner found with that name"), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (IOException | NullPointerException ioException)
        {
            return new ResponseEntity<>(new ErrorMessage("Error loading data."), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(IllegalArgumentException illegalArgumentException)
        {
            return new ResponseEntity<>(new ErrorMessage(illegalArgumentException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}/min-max-coins-values")
    @CrossOrigin(origins = "http://localhost:3000/")
    public ResponseEntity getMinMaxCoinsValue(@PathVariable String name)
    {
        try
        {
            Result result = this.walletService.getCoinsMinMaxValue(name);

            if(result == null)
                return new ResponseEntity<>(new ErrorMessage("No owner found with that name"), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (IOException | NullPointerException ioException)
        {
            return new ResponseEntity<>(new ErrorMessage("Error loading data."), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(IllegalArgumentException illegalArgumentException)
        {
            return new ResponseEntity<>(new ErrorMessage(illegalArgumentException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
