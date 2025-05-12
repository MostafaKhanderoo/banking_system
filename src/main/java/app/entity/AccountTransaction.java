package app.entity;

import app.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account_transaction")
public class AccountTransaction extends BaseEntity<Long> {
    @ManyToOne
    private Account account;
    @ManyToOne
    private Transaction transaction;
}
