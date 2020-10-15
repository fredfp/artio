/*
 * Copyright 2015-2019 Adaptive Financial Consulting Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.artio.engine.logger;

import iLinkBinary.*;
import org.junit.Before;
import uk.co.real_logic.artio.ilink.ILink3Proxy;
import uk.co.real_logic.artio.ilink.ILinkMessageConsumer;
import uk.co.real_logic.artio.protocol.GatewayPublication;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class ILink3MessageLoggerTest extends AbstractFixMessageLoggerTest
{
    {
        compactionSize = 2000;
    }

    private final ILinkMessageConsumer iLinkMessageConsumer = (iLinkMessage, buffer, offset, header) ->
        timestamps.add(iLinkMessage.enqueueTime());

    @Before
    public void setup()
    {
        setup(iLinkMessageConsumer);
    }

    void onMessage(final GatewayPublication publication, final int timestamp)
    {
        final ILink3Proxy proxy = new ILink3Proxy(1, publication.dataPublication(), null, null);
        final ExecutionReportStatus532Encoder executionReportStatus = new ExecutionReportStatus532Encoder();
        final long position = proxy.claimILinkMessage(
            ExecutionReportStatus532Encoder.BLOCK_LENGTH,
            executionReportStatus,
            timestamp);
        assertThat(position, greaterThan(0L));

        executionReportStatus
            .seqNum(1)
            .uUID(1)
            .text("")
            .execID("123")
            .senderID("ABC")
            .clOrdID("1")
            .partyDetailsListReqID(1)
            .orderID(1)
            .transactTime(timestamp)
            .sendingTimeEpoch(timestamp)
            .orderRequestID(1)
            .location("LONDO")
            .securityID(1)
            .orderQty(1)
            .cumQty(1)
            .leavesQty(1)
            .expireDate(1)
            .ordStatus(OrderStatus.Filled)
            .side(SideReq.Buy)
            .timeInForce(TimeInForce.Day)
            .possRetransFlag(BooleanFlag.False)
            .shortSaleType(ShortSaleType.LongSell);

        executionReportStatus.price().mantissa(1);
        executionReportStatus.stopPx().mantissa(2);

        proxy.commit();
    }
}
