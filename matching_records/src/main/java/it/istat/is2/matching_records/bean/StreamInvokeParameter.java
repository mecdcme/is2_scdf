package it.istat.is2.matching_records.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StreamInvokeParameter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String authorizationToken;

	public StreamInvokeParameter(String rawParameter) {
		String[] params = rawParameter.split("\\|");
		this.id = Long.parseLong(params[0]);
		this.authorizationToken = params[1];

	}
}
