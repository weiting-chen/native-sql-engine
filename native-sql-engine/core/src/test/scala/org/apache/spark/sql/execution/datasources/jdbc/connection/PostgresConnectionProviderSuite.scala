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

package org.apache.spark.sql.execution.datasources.jdbc.connection

class PostgresConnectionProviderSuite extends ConnectionProviderSuiteBase {
  ignore("setAuthenticationConfigIfNeeded must set authentication if not set") {
    val provider = new PostgresConnectionProvider()
    val defaultOptions = options("jdbc:postgresql://localhost/postgres")
    val customOptions =
      options(s"jdbc:postgresql://localhost/postgres?jaasApplicationName=custompgjdbc")
    val driver = registerDriver(provider.driverClass)

    assert(provider.appEntry(driver, defaultOptions) !== provider.appEntry(driver, customOptions))
    testSecureConnectionProvider(provider, driver, defaultOptions)
    testSecureConnectionProvider(provider, driver, customOptions)
  }
}
