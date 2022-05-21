package hr.tvz.zubcic.hardwareapp.hardware;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class HardwareCommand {

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotBlank(message = "Hardware code cannot be empty")
    private String code;

    @NotNull(message = "Product price must be entered")
    @Min(message = "Product price must be higher than 10", value = 10)
    private BigDecimal price;

    @NotNull(message = "Product type must be selected")
    private Hardware.Type type;

    @NotNull(message = "Quantity of the products available must be entered")
    @PositiveOrZero(message = "Quantity of the products available must be 0 or more")
    @Max(message = "Quantity of the products available cannot be higher than 200", value = 200)
    private Integer stock;

    public enum Type{
        CPU, GPU, MBO, RAM, STORAGE,OTHER;
    }

    public HardwareCommand(String name, String code, BigDecimal price, Hardware.Type type, Integer stock) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Hardware.Type getType() {
        return type;
    }

    public void setType(Hardware.Type type) {
        this.type = type;
    }

    public Integer getStockAvailable() {
        return stock;
    }

    public void setStockAvailable(Integer quantityAvailable) {
        this.stock = quantityAvailable;
    }
}
