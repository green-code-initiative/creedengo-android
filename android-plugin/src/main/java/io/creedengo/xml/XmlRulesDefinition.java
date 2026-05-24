/*
 * Creedengo Android Java plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@green-code-initiative.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.creedengo.xml;

import io.creedengo.common.RuleSpecificationLoader;
import org.apache.commons.lang.StringUtils;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;
import org.sonar.api.utils.AnnotationUtils;

public final class XmlRulesDefinition implements RulesDefinition {

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(Xml.REPOSITORY_KEY, Xml.KEY).setName(Xml.REPOSITORY_NAME);

        for (Class<?> check : XmlCheckList.getXmlChecks()) {
            new RulesDefinitionAnnotationLoader().load(repository, check);
            enrichRule(check, repository);
        }
        repository.done();
    }

    private void enrichRule(Class<?> ruleClass, NewRepository repository) {
        org.sonar.check.Rule ruleAnnotation = AnnotationUtils.getAnnotation(ruleClass, org.sonar.check.Rule.class);
        if (ruleAnnotation == null) {
            throw new IllegalArgumentException("No Rule annotation was found on " + ruleClass);
        }
        String ruleKey = ruleAnnotation.key();
        if (StringUtils.isEmpty(ruleKey)) {
            throw new IllegalArgumentException("No key is defined in Rule annotation of " + ruleClass);
        }
        NewRule rule = repository.rule(ruleKey);
        if (rule == null) {
            throw new IllegalStateException("No rule was created for " + ruleClass + " in " + repository.key());
        }
        RuleSpecificationLoader.load(rule, ruleKey, Xml.RULES_SPECIFICATIONS_XML_PATH);
    }
}
