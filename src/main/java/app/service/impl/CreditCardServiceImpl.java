package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.cfg.ApplicationContext;
import app.cfg.SessionFactoryInstance;
import app.entity.CreditCard;
import app.exception.CreditCardAlreadyExistsException;
import app.exception.NotFoundException;
import app.repository.CreditCardRepository;
import app.repository.impl.CreditCardRepositoryImpl;
import app.service.CreditCardService;
import app.service.authentication.AuthenticationCustomer;
import org.hibernate.Session;

import java.time.LocalDateTime;

public class CreditCardServiceImpl extends BaseServiceImpl<Long, CreditCard, CreditCardRepository>
        implements CreditCardService {
    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }

    CreditCardRepositoryImpl creditCardRepository = new CreditCardRepositoryImpl
            (CreditCard.class);

    @Override
    public CreditCard requestCard(CreditCard creditCard ,String bankName) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                var loggedInCustomer = AuthenticationCustomer.getLoggedInCustomer();

                var customerAccount = ApplicationContext.getAccountService()
                        .findAccountByBankNameAndCustomerLogged(bankName, loggedInCustomer.getNationalCode());

                checkCustomerHasCardInBank
                        (loggedInCustomer.getNationalCode(), customerAccount.getBank().getName());
                    if (customerAccount.getBank().getName().equals("saman")) {
                        creditCard.setCardNumber("621986" + RandomStringGenerator.cardNumberRandomString());
                    } else if (customerAccount.getBank().getName().equals("sina")) {
                        creditCard.setCardNumber("639346" + RandomStringGenerator.cardNumberRandomString());
                    } else if (customerAccount.getBank().getName().equals("melat")) {
                        creditCard.setCardNumber("610433" + RandomStringGenerator.cardNumberRandomString());
                    } else if (customerAccount.getBank().getName().equals("pasargard")) {

                        creditCard.setCardNumber("502229" + RandomStringGenerator.cardNumberRandomString());
                    } else {

                    }
                creditCard.setAccount(customerAccount);
                creditCard.setCVV2(RandomStringGenerator.cvvRandomString());
                creditCard.setDataCard(LocalDateTime.now());
                creditCard.setCustomer(loggedInCustomer);
                //          if (loggedInCustomer.() !=null) {
                //              throw new CreditCardAlreadyExistsException();
                //          }

                var saveCard = save(creditCard);

                session.getTransaction().commit();
                return saveCard;
            } catch (CreditCardAlreadyExistsException e) {
                session.getTransaction().rollback();
                throw new CreditCardAlreadyExistsException();
            }
        }
    }

    @Override
    public void creditCardExist(String nationalCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                creditCardRepository.creditCardExist(session, nationalCode);
            } catch (NotFoundException e) {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public void checkCustomerHasCardInBank(String nationalCode, String bankName) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            Long cardCount = creditCardRepository.checkCustomerHasCardInBank
                    (session, nationalCode, bankName);
            if (cardCount != null && cardCount > 0)
                throw new CreditCardAlreadyExistsException();
        }
    }

    @Override
    public CreditCard byBankNameAndCustomer(String bankName, String nationalCode) {
        try(Session session = SessionFactoryInstance.sessionFactory.openSession()){
            try {
              return  creditCardRepository.byBankNameAndCustomer(session,bankName,nationalCode);
            }catch (NotFoundException e){
                throw new NotFoundException("Credit Not Found for this customer");
            }
        }
    }

    @Override
    public CreditCard findCardByCardNumber(String cardNumber) {
        try(Session session = SessionFactoryInstance.sessionFactory.openSession()){
            try {
                var card= creditCardRepository.findCardByCardNumber(session,cardNumber);
                if (card ==null)
                    throw new NotFoundException();

                return card;
            }catch (NotFoundException e){
                throw new NotFoundException("card with number "+cardNumber+" notFound");
            }
        }
    }
}
