package hr.tvz.zubcic.hardwareapp.hardware;

import hr.tvz.zubcic.hardwareapp.review.Review;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "HARDWARE")
public class Hardware implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hardware_name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "hardware_type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;

    @OneToMany(mappedBy = "hardware")
    private List<Review> reviewList;

    public Hardware() {

    }

    public enum Type{
        CPU, GPU, MBO, RAM, STORAGE,OTHER;
    }

    public Hardware(Long id, String name, String code, BigDecimal price, Type type, Integer quantityAvailable) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.quantityAvailable = quantityAvailable;
    }

    public Hardware(String name, String code, BigDecimal price, Type type, Integer quantityAvailable) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.quantityAvailable = quantityAvailable;
    }

    public void setId(Long id) {
        this.id = id;
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
