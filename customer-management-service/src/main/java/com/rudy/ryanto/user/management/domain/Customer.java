package com.rudy.ryanto.user.management.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Customer")
public class Customer extends AuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CUSTOMER")
    private long idCustomer;
    @Column(name = "CUSTOMER_NAME", columnDefinition = "varchar(50)")
    @NotNull
    private String customerName;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "SEX", length = 1, columnDefinition = "varchar(1)")
    private String sex;
    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @NotNull
    @Column(name = "STATUS_ACTIVE", length = 1, columnDefinition = "boolean default true")
    private Boolean statusActive;
}
