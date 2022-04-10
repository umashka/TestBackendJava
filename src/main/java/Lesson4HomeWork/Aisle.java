package Lesson4HomeWork;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisle",
        "items"
})

@Data
public class Aisle {
    @JsonProperty("aisle")
    public String aisle;
    @JsonProperty("items")
    public List<Item> items = new ArrayList<Item>();
}
