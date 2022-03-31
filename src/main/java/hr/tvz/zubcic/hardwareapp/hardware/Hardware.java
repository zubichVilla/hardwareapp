package hr.tvz.zubcic.hardwareapp.hardware;

import java.math.BigDecimal;

public class Hardware {

    private String name;

    private String code;

    private BigDecimal price;

    private Type type;

    private Integer quantityAvailable;

    public enum Type{
        CPU, GPU, MBO, RAM, STORAGE,OTHER;
    }

    public Hardware(String name, String code, BigDecimal price, Type type, Integer quantityAvailable) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.quantityAvailable = quantityAvailable;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
