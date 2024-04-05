package com.youable.bestudy.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private Date createDate;        // 등록일
    private Date lastModifiedDate;  // 수정일
}
