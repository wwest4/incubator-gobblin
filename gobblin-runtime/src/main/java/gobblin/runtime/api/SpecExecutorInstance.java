/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gobblin.runtime.api;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.typesafe.config.Config;

import gobblin.annotation.Alpha;


/**
 * Defines a SpecExecutorInstance (typically a standalone instance, cluster or Azkaban deployment)
 * that can execute a {@link Spec}.
 */
@Alpha
public interface SpecExecutorInstance<V> {
  /** An URI identifying the SpecExecutorInstance. */
  URI getUri();

  /** Human-readable description of the SpecExecutorInstance .*/
  Future<String> getDescription();

  /** SpecExecutorInstance config as a typesafe config object. */
  Future<Config> getConfig();

  /** Health of SpecExecutorInstance. */
  Future<String> getHealth();

  /** Source : Destination processing capabilities of SpecExecutorInstance. */
  Future<? extends Map<String, String>> getCapabilities();

  /** Add a {@link Spec} for execution on {@link SpecExecutorInstance}. */
  Future<?> addSpec(V addedSpec);

  /** Update a {@link Spec} being executed on {@link SpecExecutorInstance}. */
  Future<?> updateSpec(V updatedSpec);

  /** Delete a {@link Spec} being executed on {@link SpecExecutorInstance}. */
  Future<?> deleteSpec(URI deletedSpecURI);

  /** List all {@link Spec} being executed on {@link SpecExecutorInstance}. */
  Future<? extends List<V>> listSpecs();
}
