package app.service;

import app.base.service.BaseService;
import app.entity.CreditCard;

public interface CreditCardService extends BaseService<Long, CreditCard> {

    CreditCard requestCard ( CreditCard creditCard , String bankName);

    void creditCardExist(String nationalCode);

    void checkCustomerHasCardInBank(String nationalCode,String bankName);

    CreditCard byBankNameAndCustomer (String bankName,String nationalCode);

    CreditCard findCardByCardNumber(String cardNumber);

}
