package com.seaboxdata.portal;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.Charset;

/**
 * Created by xiaoy on 3/21/2017.
 */
public class PortalPasswordEncoder implements PasswordEncoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalPasswordEncoder.class);

    private String encodingAlgorithm;
    private String characterEncoding;

    public static void main(String[] args) {
        PortalPasswordEncoder encoder = new PortalPasswordEncoder("MD5", null);

        LOGGER.info("Encoded password for " + args[0] + " is: " + encoder.encode(args[0]));
    }

    public PortalPasswordEncoder(final String encodingAlgorithm, final String characterEncoding){
        this.encodingAlgorithm = encodingAlgorithm;
        this.characterEncoding = characterEncoding;
    }

    public String encode(CharSequence password) {
        if (password == null) {
            return null;
        }

        if (StringUtils.isBlank(this.encodingAlgorithm)) {
            LOGGER.warn("No encoding algorithm is defined. Password cannot be encoded; Returning null");
            return null;
        }

        final String encodingCharToUse = StringUtils.isNotBlank(this.characterEncoding)
                ? this.characterEncoding : Charset.defaultCharset().name();

        LOGGER.debug("Using [{}] as the character encoding algorithm to update the digest", encodingCharToUse);

        try {
            final byte[] pswBytes = password.toString().getBytes(encodingCharToUse);
            final String encoded = Hex.encodeHexString(DigestUtils.getDigest(this.encodingAlgorithm).digest(pswBytes));
            LOGGER.debug("Encoded password via algorithm [{}] and character-encoding [{}] is [{}]", this.encodingAlgorithm,
                    encodingCharToUse, encoded);
            return encoded;
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        final String encodedRawPassword = StringUtils.isNotBlank(rawPassword) ? encode(rawPassword.toString()) : null;
        final boolean matched = StringUtils.equals(encodedRawPassword, encodedPassword);
        LOGGER.debug("Provided password does{}match the encoded password", BooleanUtils.toString(matched, StringUtils.EMPTY, " not "));
        return matched;
    }
}
