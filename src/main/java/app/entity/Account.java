package app.entity;

import app.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account extends BaseEntity<Long> {
    @Column(nullable = false,unique = true)
    private String Address;
    @Column(nullable = false)
    private  Double balance;

@OneToOne
private Customer customer;
@OneToOne
private Bank bank;
@OneToMany
private List<Transaction> transaction;

}
