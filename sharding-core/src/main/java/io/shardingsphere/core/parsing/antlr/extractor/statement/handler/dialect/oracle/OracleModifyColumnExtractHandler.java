/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.extractor.statement.handler.dialect.oracle;

import java.util.Collection;

import org.antlr.v4.runtime.ParserRuleContext;

import com.google.common.base.Optional;

import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.ASTExtractHandler;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.RuleName;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.result.ColumnDefinitionExtractResult;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.result.ExtractResult;
import io.shardingsphere.core.parsing.antlr.extractor.statement.phrase.ColumnDefinitionPhraseExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.util.ASTUtils;
import io.shardingsphere.core.parsing.antlr.sql.ddl.ColumnDefinition;

/**
 * Modify column extract handler for Oracle.
 *
 * @author duhongjun
 */
public final class OracleModifyColumnExtractHandler implements ASTExtractHandler {
    
    private final ColumnDefinitionPhraseExtractor columnDefinitionPhraseExtractor = new ColumnDefinitionPhraseExtractor();
    
    @Override
    public Optional<ExtractResult> extract(final ParserRuleContext ancestorNode) {
        Collection<ParserRuleContext> modifyColumnNodes = ASTUtils.getAllDescendantNodes(ancestorNode, RuleName.MODIFY_COLUMN);
        if (modifyColumnNodes.isEmpty()) {
            return Optional.absent();
        }
        ColumnDefinitionExtractResult result = new ColumnDefinitionExtractResult();
        for (ParserRuleContext modifyColumnNode : modifyColumnNodes) {
            for (ParserRuleContext each : ASTUtils.getAllDescendantNodes(modifyColumnNode, RuleName.MODIFY_COL_PROPERTIES)) {
                // it`s not column definition, but can call this method
                Optional<ColumnDefinition> columnDefinition = columnDefinitionPhraseExtractor.extract(each);
                if (columnDefinition.isPresent()) {
                    result.getColumnDefintions().add(columnDefinition.get());
                }
            }
        }
        return Optional.<ExtractResult>of(result);
    }
}
