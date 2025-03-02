package app.entity;

import app.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction extends BaseEntity<Long> {
    @Column(nullable = false)
    private String withdrawAccount;
    @Column(nullable = false)
    private String depositorAccount;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private LocalDateTime timeTransaction;



}
