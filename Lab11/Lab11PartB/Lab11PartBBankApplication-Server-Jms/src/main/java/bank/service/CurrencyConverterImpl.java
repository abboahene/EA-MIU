package bank.service;

import bank.integration.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterImpl implements CurrencyConverter{

    @Autowired
    private Logger logger;
    public double euroToDollars (double amount){
		logger.log("CurrencyConverter: converting "+amount+" dollars to euros");
        return 1.57 * amount;
    }
    
    public double dollarsToEuros (double amount){
        return 0.637 * amount;
    }

}
