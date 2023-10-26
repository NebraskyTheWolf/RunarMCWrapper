package com.runarmc.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class ErrorModel {
    Integer code;
    String error;
    String message;
}
