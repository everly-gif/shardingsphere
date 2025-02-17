/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.test.e2e.framework.param.array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.infra.database.type.DatabaseType;
import org.apache.shardingsphere.infra.database.type.DatabaseTypeFactory;
import org.apache.shardingsphere.test.e2e.cases.SQLCommandType;
import org.apache.shardingsphere.test.e2e.env.container.atomic.constants.AdapterContainerConstants;
import org.apache.shardingsphere.test.e2e.env.container.atomic.constants.EnvironmentConstants;
import org.apache.shardingsphere.test.e2e.env.runtime.IntegrationTestEnvironment;
import org.apache.shardingsphere.test.e2e.framework.param.model.AssertionTestParameter;
import org.apache.shardingsphere.test.e2e.framework.param.model.E2ETestParameter;

import java.util.Collection;
import java.util.Collections;

/**
 * JDBC test parameter generator for standalone mode.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JdbcStandaloneTestParameterGenerator {
    
    private static final Collection<String> ADAPTERS = Collections.singleton(AdapterContainerConstants.JDBC);
    
    private static final Collection<DatabaseType> DATABASE_TYPES = Collections.singleton(DatabaseTypeFactory.getInstance("H2"));
    
    private static final IntegrationTestEnvironment ENV = IntegrationTestEnvironment.getInstance();
    
    /**
     * Get assertion test parameter.
     *
     * @param sqlCommandType SQL command type
     * @return assertion test parameter
     */
    public static Collection<AssertionTestParameter> getAssertionTestParameter(final SQLCommandType sqlCommandType) {
        return new E2ETestParameterGenerator(ADAPTERS, ENV.getScenarios(), EnvironmentConstants.STANDALONE_MODE, DATABASE_TYPES).getAssertionTestParameter(sqlCommandType);
    }
    
    /**
     * Get case test parameter.
     *
     * @param sqlCommandType SQL command type
     * @return case test parameter
     */
    public static Collection<E2ETestParameter> getCaseTestParameter(final SQLCommandType sqlCommandType) {
        return new E2ETestParameterGenerator(ADAPTERS, ENV.getScenarios(), EnvironmentConstants.STANDALONE_MODE, DATABASE_TYPES).getCaseTestParameter(sqlCommandType);
    }
}
