package com.rates.account.query.domain.entity;

import com.rates.account.common.dto.TableType;
import com.rates.core.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency extends BaseEntity {

    @Id
    private String id;
    private String userName;
    private String currencyCode;
    private TableType tableType;
    private Date currencyDate;
    private Date creationDate;
}
