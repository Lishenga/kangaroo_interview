package com.kangaroo.interview.v1.requests;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class GeneralPagedRequest implements Serializable {

    private static final long serialVersionUID = -6487671289690827077L;

    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide isDeleted")
    private Boolean isDeleted;
}
