package com.algasensors.temperature.processing.common;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

import java.util.UUID;

public class IdGenerator {

    private static final TimeBasedEpochRandomGenerator generator = Generators.timeBasedEpochRandomGenerator();

    private IdGenerator(){}

    public static UUID generateTimeBasedUUID() {
        return generator.generate();
    }
}
