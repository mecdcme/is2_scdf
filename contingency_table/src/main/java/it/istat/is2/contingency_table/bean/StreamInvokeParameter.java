package it.istat.is2.contingency_table.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class StreamInvokeParameter implements Serializable {
    public static final Long serialVersionUid = 1L;

    private Long id;
    private String authorizationToken;

}
