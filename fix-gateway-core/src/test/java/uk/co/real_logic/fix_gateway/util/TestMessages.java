/*
 * Copyright 2015 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.fix_gateway.util;

import static java.nio.charset.StandardCharsets.US_ASCII;

public final class TestMessages
{
    // BUY 100 CVS MKT DAY
    public static final byte[] EG_MESSAGE = toAscii("8=FIX.4.2\0019=145\00135=D\00134=4\00149=ABC_DEFG01\001" +
            "52=20090323-15:40:29\00156=CCG\001115=XYZ\00111=NF 0542/03232009\00154=1\00138=100\00155=CVS\00140=1" +
            "\00159=0\00147=A\00160=20090323-15:40:29\00121=1\001207=N\00110=194\001");

    public static final int MSG_LEN = EG_MESSAGE.length;

    public static final byte[] INVALID_CHECKSUM_MSG = toAscii("8=FIX.4.2\0019=145\00135=D\00134=4\00149=ABC_DEFG01\001" +
            "52=20090323-15:40:29\00156=CCG\001115=XYZ\00111=NF 0542/03232009\00155=CVS\00140=1\00159=0\00147=A" +
            "60=20090323-15:40:29\00121=1\001207=N\00110=155\001");

    public static final int INVALID_CHECKSUM_LEN = INVALID_CHECKSUM_MSG.length;

    public static final byte[] INVALID_MESSAGE = toAscii("8=FIX.4.2\0019=145\00135=D\00134=4\00149=ABC_DEFG01\001" +
            "52=\\\\\20156=CCG\001115=XYZ\00111=NF 0542/03232009\001\001\001\001\001\00154=1\00138=55140=" +
            "\00159=0\00147=A\00160=20090323-15:40:29\00121=1\001207=N\00110=194\001");

    public static final int INVALID_LEN = INVALID_MESSAGE.length;

    private static byte[] toAscii(String str)
    {
        return str.getBytes(US_ASCII);
    }

    public static final byte[] EXECUTION_REPORT = toAscii("8=FIX.4.2\0019=378\00135=8\001128=XYZ\00134=5\00149=CCG" +
            "\00156=ABC_DEFG01\00152=20090323-" +
            "15:40:35\00155=CVS\00137=NF 0542/03232009\00111=NF 0542/03232009\00117=NF 0542/03232009" +
            "001001001\00120=0\00139=2\001150=2\00154=1\00138=100\00140=1\00159=0\00131=25.4800\00132=100^A" +
            "14=0\0016=0\001151=0\00160=20090323-15:40:30\00158=Fill\00130=N\00176=0034\001207=N\00147=A^A" +
            "9430=NX\0019483=000008\0019578=1\001382=1\001375=TOD\001337=0000\001437=100\001438=1243^A" +
            "9579=0000100001\0019426=2/2\0019433=0034\00129=1\00163=0\0019440=001001001\00110=080\001");

    public static final int EXECUTION_REPORT_LEN = EXECUTION_REPORT.length;

}
