package hr.tvz.zubcic.hardwareapp.hardware;

import java.math.BigDecimal;

public class HardwareDTO {

    private final String code;
    private final String name;
    private final BigDecimal price;

    public HardwareDTO(String code, String name, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return "HardwareDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
