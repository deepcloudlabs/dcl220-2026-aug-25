package com.example.patterns.bridge.data;

import com.example.patterns.bridge.common.ReportPeriod;

import java.util.List;

public interface SalesRepository {
    List<SalesLine> totalsByRegion(ReportPeriod period);
}
