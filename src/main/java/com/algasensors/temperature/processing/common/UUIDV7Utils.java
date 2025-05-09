package com.algasensors.temperature.processing.common;

import org.springframework.util.Assert;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class UUIDV7Utils {

    private UUIDV7Utils() {
    }

    public static OffsetDateTime extractOffsetDateTime(UUID uuid) {
        Assert.notNull(uuid, "uuid must not be null");

        long time = uuid.getMostSignificantBits() >>> 16;
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }
}
