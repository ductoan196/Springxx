package com.example.pet_shop_management.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public enum Status {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    public String name;

}
