package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Sneaker;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
public class JdbcSneakerRepository implements SneakerRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert sneakerInsert;
    private static final RowMapper<Sneaker> SNEAKER_ROW_MAPPER = ((rs, rowNum) -> new Sneaker(
            rs.getLong("id"),
            rs.getString("model"),
            rs.getLong("brand_id"),
            rs.getBigDecimal("size"),
            rs.getString("colorway"),
            rs.getBigDecimal("buy_price"),
            rs.getBigDecimal("current_value"),
            rs.getString("condition"),
            rs.getDate("purchased_at").toLocalDate()
    ));

    public JdbcSneakerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.sneakerInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("sneaker")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Sneaker> findAll() {
        String sql = """
                SELECT id, model, brand_id, size, colorway, buy_price, current_value, condition, purchased_at
                FROM sneaker
                """;
        return jdbcTemplate.query(sql, SNEAKER_ROW_MAPPER);
    }

    @Override
    public Optional<Sneaker> findById(Long id) {
        String sql = """
                SELECT id, model, brand_id, size, colorway, buy_price, current_value, condition, purchased_at
                FROM sneaker
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, SNEAKER_ROW_MAPPER, id).stream().findFirst();
    }

    @Override
    public Optional<Sneaker> findByModel(String val) {
        String sql = """
                SELECT id, model, brand_id, size, colorway, buy_price, current_value, condition, purchased_at
                FROM sneaker
                WHERE model = ?
                """;
        return jdbcTemplate.query(sql, SNEAKER_ROW_MAPPER, val).stream().findFirst();
    }

    @Override
    public List<Sneaker> findByColorway(String colorway) {
        String sql = """
                SELECT id, model, brand_id, size, colorway, buy_price, current_value, condition, purchased_at
                FROM sneaker
                WHERE colorway = ?
                """;
        return jdbcTemplate.query(sql, SNEAKER_ROW_MAPPER, colorway);
    }

    @Override
    public Optional<Sneaker> addSneaker(Sneaker sneaker) {
        Map<String, Object> params = new HashMap<>();
        params.put("model", sneaker.getModel());
        params.put("brand_id", sneaker.getBrand_id());
        params.put("size", sneaker.getSize());
        params.put("colorway", sneaker.getColorway());
        params.put("buy_price", sneaker.getBuyPrice());
        params.put("current_value", sneaker.getCurrentValue());
        params.put("condition", sneaker.getCondition());
        params.put("purchased_at", sneaker.getPurchasedAt());

        Number genId = sneakerInsert.executeAndReturnKey(params);
        return Optional.of(new Sneaker(genId.longValue(), sneaker.getModel(), sneaker.getBrand_id(), sneaker.getSize(), sneaker.getColorway(), sneaker.getBuyPrice(), sneaker.getCurrentValue(), sneaker.getCondition(), sneaker.getPurchasedAt()));
    }

    @Override
    public Optional<Sneaker> updateSneaker(Sneaker sneaker) {
        String sql = """
                UPDATE sneaker
                SET model = ?,
                    brand_id = ?,
                    size = ?,
                    colorway = ?,
                    buy_price = ?,
                    current_value = ?,
                    condition = ?,
                    purchased_at = ?
                WHERE id = ?
                """;
        int rows = jdbcTemplate.update(sql, sneaker.getModel(), sneaker.getBrand_id(), sneaker.getSize(), sneaker.getColorway(), sneaker.getBuyPrice(), sneaker.getCurrentValue(), sneaker.getCondition(), sneaker.getPurchasedAt(), sneaker.getId());
        return rows > 0 ? Optional.of(sneaker) : Optional.empty();
    }

    @Override
    public boolean deleteSneaker(String model) {
        return jdbcTemplate.update("DELETE FROM sneaker WHERE model = ?", model) > 0;
    }

    @Override
    public boolean deleteSneaker(Long id) {
        return jdbcTemplate.update("DELETE FROM sneaker WHERE id = ?", id) > 0;
    }
}
