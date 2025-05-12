package app.repository;

import app.base.repository.BaseRepository;
import app.entity.CreditCard;
import org.hibernate.Session;

import java.util.List;

public interface CreditCardRepository extends BaseRepository<Long, CreditCard> {
        CreditCard byBankNameAndCustomer(Session session,String bankName,String nationalCode);
        List<CreditCard> findCreditCardByNationalCode(Session session, String nationalCode);
        Long creditCardExist(Session session,String nationalCode);
        Long checkCustomerHasCardInBank(Session session, String nationalCode ,String bankName);

        CreditCard findCardByCardNumber(Session session, String cardNumber);

}
