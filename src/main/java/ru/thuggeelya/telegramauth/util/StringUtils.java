package ru.thuggeelya.telegramauth.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.net.URLDecoder.decode;
import static java.nio.charset.StandardCharsets.UTF_8;

public final class StringUtils {

    public static String hex(final byte[] bytes) {

        return IntStream.range(0, bytes.length)
                        .mapToObj(i -> String.format("%02x", bytes[i] & 0xFF))
                        .collect(Collectors.joining());
    }

    public static byte[] hmacSha256(final byte[] source, final byte[] secret)
            throws NoSuchAlgorithmException, InvalidKeyException {

        final Mac mac = Mac.getInstance("HmacSHA256");

        mac.init(new SecretKeySpec(secret, "HmacSHA256"));

        return mac.doFinal(source);
    }

    public static Map<String, String> parseTelegramData(final String data) {

        if (data == null) return null;

        return Arrays.stream(data.split("&"))
                     .map(kv -> kv.split("=", 2))
                     .filter(kv -> kv.length == 2)
                     .collect(Collectors.toMap(
                             kv -> kv[0],
                             kv -> decode(kv[1], UTF_8)
                     ));
    }
}
