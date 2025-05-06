package com.hu6r1s.customer.handler;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) { }
