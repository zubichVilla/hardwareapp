package hr.tvz.zubcic.hardwareapp.hardware;

import java.math.BigDecimal;

public class HardwareDTO {

    private final String code;
    private final String name;
    private final BigDecimal price;
    private final Hardware.Type type;
    private final Integer stock;

    public HardwareDTO(String code, String name, BigDecimal price, Hardware.Type type, Integer stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Hardware.Type getType() {
        return type;
    }

    public Integer getStockAvailable() {
        return stock;
    }

    @Override
    public String toString() {
        return "HardwareDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", stock=" + stock +
                '}';
    }
}
