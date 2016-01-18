package com.saintdan.util.rocketmq.tools;

import java.util.zip.CRC32;

/**
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/12/15
 * @since JDK1.8
 */
public class HashUtil {

    public static long crc32Code(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return crc32.getValue();
    }

}
