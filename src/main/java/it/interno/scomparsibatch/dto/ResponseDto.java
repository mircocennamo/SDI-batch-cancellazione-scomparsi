package it.interno.scomparsibatch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class ResponseDto<T> {
    private int code;
    private T body;
    private String error;
}
