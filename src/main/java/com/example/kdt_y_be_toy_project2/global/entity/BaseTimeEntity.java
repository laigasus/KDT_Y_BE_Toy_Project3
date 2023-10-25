package com.example.kdt_y_be_toy_project2.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 각자 같은 Class를 상속받아도 Column Name을 다르게 설정할 수 있는 기술을 찾아보세요
 * <p>
 * 대안 1: 각각 필드를 재정의 해 @Column(name= " ") 사용
 * 대안 2: @AttributeOverride - 해당 엔티티에서는 다른 컬럼명을 사용하고 싶을때 쓰는 어노테이션
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Comment("수정일")
    private LocalDateTime updatedAt;

}
