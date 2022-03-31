package hr.tvz.zubcic.hardwareapp.hardware;

import java.math.BigDecimal;

public class HardwareDTO {

    private String name;
    private BigDecimal price;

    public HardwareDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "HardwareDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
