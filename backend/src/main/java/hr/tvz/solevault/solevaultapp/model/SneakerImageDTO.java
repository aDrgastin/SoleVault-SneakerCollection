package hr.tvz.solevault.solevaultapp.model;

public record SneakerImageDTO(
        Long id,
        String url,
        String tag,
        String description,
        SneakerDTO sneaker
) { }
