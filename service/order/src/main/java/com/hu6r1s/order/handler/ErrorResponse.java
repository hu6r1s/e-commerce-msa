package com.hu6r1s.order.handler;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) { }
