package it.istat.is2.contingency_table.output;

import lombok.Data;

import java.util.Map;
import java.util.Objects;

@Data
public class ContingencyTableOutput {
    private Long id;
    private String sampleString;
    private Map<Long, String> data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContingencyTableOutput output = (ContingencyTableOutput) o;
        return id.equals(output.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
