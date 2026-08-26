package com.example.patterns.bridge.data;

import java.math.BigDecimal;

public record SalesLine(String region, int orders, BigDecimal revenue) {
}
