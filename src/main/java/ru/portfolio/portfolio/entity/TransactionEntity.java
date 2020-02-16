package ru.portfolio.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity {
    @Id
    @GenericGenerator(name = "UseExistingOrGenerateIdGenerator", strategy = "ru.portfolio.portfolio.entity.UseExistingOrGenerateIdGenerator")
    @GeneratedValue(generator = "UseExistingOrGenerateIdGenerator")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isin", referencedColumnName = "isin")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private SecurityEntity security;

    @Basic
    @Column(name = "timestamp")
    private Instant timestamp;

    @Basic
    @Column(name = "count")
    private int count;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Set<TransactionCashFlowEntity> transactionCashFlows = Collections.newSetFromMap(new ConcurrentHashMap<>());
}
