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

package io.opencensus.implcore.internal;

import com.google.common.base.Throwables;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/** A {@link ThreadFactory} implementation that starts all {@link Thread} as daemons. */
public final class DaemonThreadFactory implements ThreadFactory {
  public static final boolean IS_APPENGINE =
      System.getProperty("com.google.appengine.runtime.environment") != null;
  // AppEngine runtimes have constraints on threading and socket handling
  // that need to be accommodated.
  public static final boolean IS_RESTRICTED_APPENGINE =
      IS_APPENGINE && "1.7".equals(System.getProperty("java.specification.version"));
  private static final String DELIMITER = "-";
  private static final ThreadFactory threadFactory = getThreadFactory();
  private final AtomicInteger threadIdGen = new AtomicInteger();
  private final String threadPrefix;

  private static ThreadFactory getThreadFactory() {
    if (!IS_APPENGINE) {
      return Executors.defaultThreadFactory();
    }
    try {
      String threadMethod =
          "1.7".equals(System.getProperty("java.specification.version"))
              ? "currentRequestThreadFactory"
              : "backgroundThreadFactory";
      return (ThreadFactory)
          Class.forName("com.google.appengine.api.ThreadManager")
              .getMethod(threadMethod)
              .invoke(null);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Couldn't invoke ThreadManager.backgroundThreadFactory", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Couldn't invoke ThreadManager.backgroundThreadFactory", e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Couldn't invoke ThreadManager.backgroundThreadFactory", e);
    } catch (InvocationTargetException e) {
      throw Throwables.propagate(e.getCause());
    }
  }

  /**
   * Constructs a new {@code DaemonThreadFactory}.
   *
   * @param threadPrefix used to prefix all thread names. (E.g. "CensusDisruptor").
   */
  public DaemonThreadFactory(String threadPrefix) {
    this.threadPrefix = threadPrefix + DELIMITER;
  }

  @Override
  public Thread newThread(Runnable r) {
    Thread thread = threadFactory.newThread(r);
    if (!IS_RESTRICTED_APPENGINE) {
      thread.setName(threadPrefix + threadIdGen.getAndIncrement());
      thread.setDaemon(true);
    }
    return thread;
  }
}
