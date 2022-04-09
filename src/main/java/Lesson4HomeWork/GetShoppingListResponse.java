package Lesson4HomeWork;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisles",
        "cost",
        "startDate",
        "endDate"
})

@Data
public class GetShoppingListResponse {
    @JsonProperty("aisles")
    public List<Aisle> aisles = new ArrayList<Aisle>();
    @JsonProperty("cost")
    public Double cost;
    @JsonProperty("startDate")
    public Integer startDate;
    @JsonProperty("endDate")
    public Integer endDate;
}
