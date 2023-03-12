package com.changelle.nisum.api.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPhones")
    @SequenceGenerator(name = "seqPhones", sequenceName = "seqPhones", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String cityCode;

    @Column(nullable = false)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}