package co.com.crediya.users.api.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        List<String> messages,
        String code,
        String detail,
        LocalDateTime timestamp
) {
}
