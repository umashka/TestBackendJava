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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "aisle",
            "items"
    })
    @Data
    public static class Aisle {

        @JsonProperty("aisle")
        public String aisle;
        @JsonProperty("items")
        public List<Item> items = new ArrayList<Item>();

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
        public static class Item {

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

            public static class Measures {

                @JsonProperty("original")
                public AddToShoppingListResponse.Measures.Original original;
                @JsonProperty("metric")
                public AddToShoppingListResponse.Measures.Metric metric;
                @JsonProperty("us")
                public AddToShoppingListResponse.Measures.Us us;

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
    }
}
