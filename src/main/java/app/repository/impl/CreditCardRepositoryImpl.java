package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.Account;
import app.entity.CreditCard;
import app.repository.CreditCardRepository;
import org.hibernate.Session;

import java.util.List;

public class CreditCardRepositoryImpl extends BaseRepositoryImpl<Long, CreditCard>
        implements CreditCardRepository {

    public CreditCardRepositoryImpl(Class<CreditCard> entity) {
        super(entity);
    }


    @Override
    public CreditCard byBankNameAndCustomer(Session session, String bankName, String nationalCode) {
     return    session.createQuery("from CreditCard c where c.account.bank.name = :bankName" +
                " and c.customer.nationalCode = :nationalCode",CreditCard.class)
                .setParameter("bankName",bankName).setParameter("nationalCode",nationalCode)
                .uniqueResult();
    }

    @Override
    public List<CreditCard> findCreditCardByNationalCode(Session session, String nationalCode) {
        return session.createQuery
                        ("from CreditCard c where c.customer.nationalCode =:nationalCode", CreditCard.class)
                .setParameter("nationalCode", nationalCode).getResultList();
    }

    @Override
    public Long creditCardExist(Session session, String nationalCode) {
      return  session.createQuery
                ("select count(id) from CreditCard c where c.customer.nationalCode = :nationalCode", Long.class)
                .setParameter("nationalCode",nationalCode).uniqueResult();
    }

    @Override
    public Long checkCustomerHasCardInBank(Session session, String nationalCode,String bankName) {
       return session.createQuery
                ("select count (id) from CreditCard b where b.account.bank.name =:bankName" +
                " and b.customer.nationalCode=:nationalCode"
                        ,Long.class).setParameter("bankName" ,bankName)
                            .setParameter("nationalCode" ,nationalCode)
                             .getSingleResult();

    }

    @Override
    public CreditCard findCardByCardNumber(Session session, String cardNumber) {
        return   session.createQuery("from CreditCard c where c.cardNumber =:cardNumber"
                , CreditCard.class).setParameter("cardNumber",cardNumber).uniqueResult();

    }


}
