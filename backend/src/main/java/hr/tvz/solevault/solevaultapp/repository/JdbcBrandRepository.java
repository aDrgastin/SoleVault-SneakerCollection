package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Brand;
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
public class JdbcBrandRepository implements BrandRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertBrand;
    private static final RowMapper<Brand> BRAND_ROW_MAPPER = ((rs, rowNum) -> new Brand(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("country"),
            rs.getDate("founded").toLocalDate(),
            rs.getString("logo_url")
    ));

    public JdbcBrandRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertBrand = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("brand")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Brand> findAll() {
        String sql = """
                SELECT id, name, country, founded, logo_url
                FROM brand
                """;
        return jdbcTemplate.query(sql, BRAND_ROW_MAPPER);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        String sql = """
                SELECT id, name, country, founded, logo_url
                FROM brand
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, BRAND_ROW_MAPPER, id).stream().findFirst();
    }

    @Override
    public Optional<Brand> addBrand(Brand brand) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", brand.getName());
        params.put("country", brand.getCountry());
        params.put("founded", brand.getFounded());
        params.put("logo_url", brand.getLogoUrl());

        Number id = insertBrand.executeAndReturnKey(params);
        return Optional.of(new Brand(id.longValue(), brand.getName(), brand.getCountry(), brand.getFounded(), brand.getLogoUrl()));
    }

    @Override
    public Optional<Brand> updateBrand(Brand brand) {
        String sql = """
                UPDATE brand
                SET name = ?, country = ?, founded = ?, logo_url = ?
                WHERE id = ?
                """;
        int rows = jdbcTemplate.update(sql, brand.getName(), brand.getCountry(), brand.getFounded(), brand.getLogoUrl(), brand.getId());
        return rows > 0 ? Optional.of(brand) : Optional.empty();
    }

    @Override
    public int deleteBrand(Long id) {
        return jdbcTemplate.update("DELETE FROM brand WHERE id = ?", id);
    }
}
