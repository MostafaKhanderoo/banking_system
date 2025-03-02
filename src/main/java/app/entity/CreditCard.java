package app.entity;

import app.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditCard extends BaseEntity<Long> {
    @Column(nullable = false,unique = true)
    private String cardNumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String CVV2;
    @Column(nullable = false)
    private  LocalDateTime dataCard;

}
