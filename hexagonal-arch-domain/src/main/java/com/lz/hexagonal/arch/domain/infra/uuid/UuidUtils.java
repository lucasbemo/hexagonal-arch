package com.lz.hexagonal.arch.domain.infra.uuid;

import com.fasterxml.uuid.Generators;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

public class UuidUtils {
    private static final SecureRandom random = new SecureRandom();

    private UuidUtils() {
    }

    public static UUID timeBasedV7() {
        return Generators.timeBasedEpochGenerator().generate();
    }

    public static UUID randomV7() {
        byte[] value = randomBytes();
        ByteBuffer buf = ByteBuffer.wrap(value);
        long high = buf.getLong();
        long low = buf.getLong();
        return new UUID(high, low);
    }

    public static byte[] randomBytes() {
        byte[] value = new byte[16];
        random.nextBytes(value);

        ByteBuffer timestamp = ByteBuffer.allocate(Long.BYTES);
        timestamp.putLong(System.currentTimeMillis());

        System.arraycopy(timestamp.array(), 2, value, 0, 6);

        value[6] = (byte) ((value[6] & 0x0F) | 0x70);
        value[8] = (byte) ((value[8] & 0x3F) | 0x80);

        return value;
    }
}
