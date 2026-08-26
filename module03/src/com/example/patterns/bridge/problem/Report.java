package com.example.patterns.bridge.problem;

import com.example.patterns.bridge.common.ReportPeriod;

/** PROBLEM: the root of a hierarchy that multiplies report types by output formats. */
public abstract class Report {
    public abstract byte[] generate(ReportPeriod period);
}
