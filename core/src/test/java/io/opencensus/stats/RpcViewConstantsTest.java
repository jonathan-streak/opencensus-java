/*
 * Copyright 2017, OpenCensus Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.opencensus.stats;

import static com.google.common.truth.Truth.assertThat;

import io.opencensus.common.Duration;
import io.opencensus.stats.View.AggregationWindow.Cumulative;
import io.opencensus.stats.View.AggregationWindow.Interval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Test for {@link RpcViewConstants}. */
@RunWith(JUnit4.class)
public final class RpcViewConstantsTest {

  @Test
  public void testConstants() {

    // Test bucket boundaries.
    assertThat(RpcViewConstants.RPC_BYTES_BUCKET_BOUNDARIES).containsExactly(
        0.0, 1024.0, 2048.0, 4096.0, 16384.0, 65536.0, 262144.0, 1048576.0, 4194304.0,
        16777216.0, 67108864.0, 268435456.0, 1073741824.0, 4294967296.0).inOrder();
    assertThat(RpcViewConstants.RPC_MILLIS_BUCKET_BOUNDARIES).containsExactly(
        0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 8.0, 10.0, 13.0, 16.0, 20.0, 25.0, 30.0,
        40.0, 50.0, 65.0, 80.0, 100.0, 130.0, 160.0, 200.0, 250.0, 300.0, 400.0, 500.0, 650.0,
        800.0, 1000.0, 2000.0, 5000.0, 10000.0, 20000.0, 50000.0, 100000.0).inOrder();
    assertThat(RpcViewConstants.RPC_COUNT_BUCKET_BOUNDARIES).containsExactly(
        0.0, 1.0, 2.0, 4.0, 8.0, 16.0, 32.0, 64.0, 128.0, 256.0, 512.0, 1024.0, 2048.0,
        4096.0, 8192.0, 16384.0, 32768.0, 65536.0).inOrder();

    // Test Duration and Window
    assertThat(RpcViewConstants.MINUTE).isEqualTo(Duration.create(60, 0));
    assertThat(RpcViewConstants.HOUR).isEqualTo(Duration.create(60 * 60, 0));
    assertThat(RpcViewConstants.CUMULATIVE).isEqualTo(Cumulative.create());
    assertThat(RpcViewConstants.INTERVAL_MINUTE).isEqualTo(
        Interval.create(RpcViewConstants.MINUTE));
    assertThat(RpcViewConstants.INTERVAL_HOUR).isEqualTo(
        Interval.create(RpcViewConstants.HOUR));


    // Test client distribution view descriptors.
    assertThat(RpcViewConstants.RPC_CLIENT_ERROR_COUNT_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_REQUEST_BYTES_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_RESPONSE_BYTES_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_REQUEST_COUNT_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_RESPONSE_COUNT_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_SERVER_ELAPSED_TIME_VIEW).isNotNull();

    // Test server distribution view descriptors.
    assertThat(RpcViewConstants.RPC_SERVER_ERROR_COUNT_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_SERVER_LATENCY_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_REQUEST_BYTES_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_RESPONSE_BYTES_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_REQUEST_COUNT_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_RESPONSE_COUNT_VIEW).isNotNull();

    // Test client interval view descriptors.
    assertThat(RpcViewConstants.RPC_CLIENT_ERROR_COUNT_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_REQUEST_BYTES_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_RESPONSE_BYTES_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_STARTED_COUNT_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_FINISHED_COUNT_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_SERVER_ELAPSED_TIME_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_REQUEST_COUNT_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_RESPONSE_COUNT_MINUTE_VIEW).isNotNull();

    assertThat(RpcViewConstants.RPC_CLIENT_ERROR_COUNT_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_REQUEST_BYTES_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_RESPONSE_BYTES_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_STARTED_COUNT_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_FINISHED_COUNT_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_SERVER_ELAPSED_TIME_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_REQUEST_COUNT_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_CLIENT_RESPONSE_COUNT_HOUR_VIEW).isNotNull();

    // Test server interval view descriptors.
    assertThat(RpcViewConstants.RPC_SERVER_ERROR_COUNT_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_SERVER_LATENCY_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_REQUEST_BYTES_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_RESPONSE_BYTES_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_STARTED_COUNT_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_FINISHED_COUNT_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_REQUEST_COUNT_MINUTE_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_RESPONSE_COUNT_MINUTE_VIEW).isNotNull();

    assertThat(RpcViewConstants.RPC_SERVER_ERROR_COUNT_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_SERVER_LATENCY_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_REQUEST_BYTES_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_RESPONSE_BYTES_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_STARTED_COUNT_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_FINISHED_COUNT_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_REQUEST_COUNT_HOUR_VIEW).isNotNull();
    assertThat(RpcViewConstants.RPC_SERVER_RESPONSE_COUNT_HOUR_VIEW).isNotNull();
  }

  @Test(expected = AssertionError.class)
  public void testConstructor() {
    new RpcViewConstants();
  }
}