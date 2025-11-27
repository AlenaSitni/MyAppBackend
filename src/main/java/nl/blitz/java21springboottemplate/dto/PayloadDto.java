package nl.blitz.java21springboottemplate.dto;

import java.util.List;

public class PayloadDto {

    private String source;
    private List<ArrivalDto> arrivals;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<ArrivalDto> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<ArrivalDto> arrivals) {
        this.arrivals = arrivals;
    }
}
