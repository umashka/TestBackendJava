package Lesson4HomeWork;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "measures",
        "usages",
        "usageRecipeIds",
        "pantryItem",
        "aisle",
        "cost",
        "ingredientId"
})

@Data
public class AddToShoppingListResponse {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("measures")
    public Measures measures;
    @JsonProperty("usages")
    public List<Object> usages = new ArrayList<Object>();
    @JsonProperty("usageRecipeIds")
    public List<Object> usageRecipeIds = new ArrayList<Object>();
    @JsonProperty("pantryItem")
    public Boolean pantryItem;
    @JsonProperty("aisle")
    public String aisle;
    @JsonProperty("cost")
    public Double cost;
    @JsonProperty("ingredientId")
    public Integer ingredientId;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "original",
            "metric",
            "us"
    })
    @Data
    public static class Measures {

        @JsonProperty("original")
        public Original original;
        @JsonProperty("metric")
        public Metric metric;
        @JsonProperty("us")
        public Us us;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({
                "amount",
                "unit"
        })
        @Data
        public static class Original {

            @JsonProperty("amount")
            public Double amount;
            @JsonProperty("unit")
            public String unit;

        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({
                "amount",
                "unit"
        })
        @Data
        public static class Metric {

            @JsonProperty("amount")
            public Double amount;
            @JsonProperty("unit")
            public String unit;

        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({
                "amount",
                "unit"
        })
        @Data
        public static class Us {

            @JsonProperty("amount")
            public Double amount;
            @JsonProperty("unit")
            public String unit;

        }
    }
}
