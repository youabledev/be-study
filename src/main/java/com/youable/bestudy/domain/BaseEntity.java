package com.youable.bestudy.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    private Date createDate;        // 등록일

    @LastModifiedDate
    private Date lastModifiedDate;  // 수정일
}
