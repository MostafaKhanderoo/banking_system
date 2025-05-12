package app.entity;

import app.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account extends BaseEntity<Long> {
    @Column(nullable = false, unique = true)
    private String address;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private boolean accountLock;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Bank bank;


}
