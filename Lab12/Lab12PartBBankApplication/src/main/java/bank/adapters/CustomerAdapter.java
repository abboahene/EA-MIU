package bank.adapters;

import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountEntryDTO;
import bank.dto.CustomerDTO;

public class CustomerAdapter {
    public static CustomerDTO customerToCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.getName());
    }

    public static Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getName());
    }

}
