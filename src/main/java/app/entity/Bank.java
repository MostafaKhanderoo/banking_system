package app.entity;

import app.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bank extends BaseEntity<Long> {
    @Column(nullable = false)
    private String name;
@Column(nullable = false ,unique = true)
    private String branch;

@OneToOne
private Boss boss;
}
