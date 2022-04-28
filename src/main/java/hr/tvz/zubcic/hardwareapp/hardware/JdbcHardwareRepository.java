package hr.tvz.zubcic.hardwareapp.hardware;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository {

    public static final String SELECT_ALL =
            "SELECT id, hardware_name, code, price, hardware_type, quantity_available FROM hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public List<Hardware> findAll() {
        return List.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public List<Hardware> findByRange(BigDecimal lowerRange, BigDecimal upperRange) {
        return List.copyOf(jdbc.query(SELECT_ALL + " WHERE PRICE >= ? AND PRICE <= ?", this::mapRowToHardware, lowerRange,upperRange));
    }


    @Override
    public Optional<Hardware> findByCode(String code) {
       try {
           return Optional.ofNullable(
                   jdbc.queryForObject(SELECT_ALL + " WHERE  CODE = ?", this::mapRowToHardware, code));

       }catch (EmptyResultDataAccessException e){
           return Optional.empty();
       }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {

        try {

            hardware.setId(saveHardwareData(hardware));
            return Optional.of(hardware);

        }catch (DuplicateKeyException e){
            return Optional.empty();
        }

    }

    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {

        int executed = jdbc.update("UPDATE hardware SET " +
                "HARDWARE_NAME = ?, " +
                "PRICE = ?, " +
                "HARDWARE_TYPE = ?, " +
                "QUANTITY_AVAILABLE = ? " +
                        "WHERE CODE = ?",
                updatedHardware.getName(),
                updatedHardware.getPrice(),
                updatedHardware.getType().toString(),
                updatedHardware.getQuantityAvailable(),
                updatedHardware.getCode()
        );

        if(executed > 0){
            return Optional.of(updatedHardware);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        jdbc.update("DELETE from hardware WHERE CODE = ?", code);
    }

    private Hardware mapRowToHardware(ResultSet resultSet, int rowNum) throws SQLException {
        return new Hardware(
                resultSet.getLong("id"),
                resultSet.getString("hardware_name"),
                resultSet.getString("code"),
                resultSet.getBigDecimal("price"),
                Hardware.Type.valueOf(resultSet.getString("hardware_type")),
                resultSet.getInt("quantity_available")
        );
    }

    private Long saveHardwareData(Hardware hardware) {

        Map<String, Object> values = new HashMap<>();

        values.put("hardware_name", hardware.getName());
        values.put("code", hardware.getCode());
        values.put("price", hardware.getPrice());
        values.put("hardware_type", hardware.getType().toString());
        values.put("quantity_available", hardware.getQuantityAvailable());

        return inserter.executeAndReturnKey(values).longValue();

    }
}
