package com.br.volleyhub.api.dto.time;

import java.util.List;

public record TimeRequest(
                String name,
                String category,
                String imageUrl,
                List<Long> playerIds) {
}
