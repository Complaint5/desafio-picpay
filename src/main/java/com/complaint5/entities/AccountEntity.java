package com.complaint5.entities;

import java.math.BigDecimal;
import java.util.UUID;

import com.complaint5.dtos.AccountDto;
import com.complaint5.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@EqualsAndHashCode(of = "id")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private boolean active;

    public AccountEntity(AccountDto contaDto){
        this.name = contaDto.name();
        this.document = contaDto.document();
        this.email = contaDto.email();
        this.password = contaDto.password();
        this.amount = BigDecimal.valueOf(0);
        this.accountType = contaDto.accountType();
        this.active = true;
    }
}
