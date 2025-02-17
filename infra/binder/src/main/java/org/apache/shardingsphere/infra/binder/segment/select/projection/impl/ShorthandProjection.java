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

package org.apache.shardingsphere.infra.binder.segment.select.projection.impl;

import com.google.common.base.Strings;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.shardingsphere.infra.binder.segment.select.projection.Projection;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Shorthand projection.
 */
@Getter
@EqualsAndHashCode
@ToString
public final class ShorthandProjection implements Projection {
    
    private final String owner;
    
    private final Map<String, Projection> actualColumns = new LinkedHashMap<>();
    
    public ShorthandProjection(final String owner, final Collection<Projection> projections) {
        this.owner = owner;
        projections.forEach(each -> actualColumns.put(each.getExpression().toLowerCase(), each));
    }
    
    @Override
    public String getExpression() {
        return Strings.isNullOrEmpty(owner) ? "*" : owner + ".*";
    }
    
    @Override
    public Optional<String> getAlias() {
        return Optional.empty();
    }
    
    @Override
    public String getColumnLabel() {
        return "*";
    }
    
    /**
     * Get owner.
     * 
     * @return owner
     */
    public Optional<String> getOwner() {
        return Optional.ofNullable(owner);
    }
    
    /**
     * Get column projections.
     *
     * @return column projections
     */
    public Collection<ColumnProjection> getColumnProjections() {
        Collection<ColumnProjection> result = new LinkedList<>();
        for (Entry<String, Projection> entry : actualColumns.entrySet()) {
            if (entry.getValue() instanceof ColumnProjection) {
                result.add((ColumnProjection) entry.getValue());
            }
        }
        return result;
    }
}
