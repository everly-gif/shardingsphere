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

grammar RQLStatement;

import BaseRule;

showStorageUnits
    : SHOW STORAGE UNITS (FROM databaseName)? (WHERE USAGE_COUNT EQ_ usageCount)?
    ;

showDefaultSingleTableStorageUnit
    : SHOW DEFAULT SINGLE TABLE STORAGE UNIT (FROM databaseName)?
    ;

showSingleTable
    : SHOW SINGLE (TABLES (LIKE likeLiteral)? | TABLE tableName) (FROM databaseName)?
    ;

showRulesUsedStorageUnit
    : SHOW RULES USED STORAGE UNIT storageUnitName (FROM databaseName)?
    ;

countSingleTableRule
    : COUNT SINGLE_TABLE RULE (FROM databaseName)?
    ;

usageCount
    : INT_
    ;

likeLiteral
    : STRING_
    ;
