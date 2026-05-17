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

import org.sonar.api.SonarEdition;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.SonarRuntime;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.Version;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

public final class XmlRulesDefinition implements RulesDefinition {

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(Xml.REPOSITORY_KEY, Xml.KEY).setName(Xml.REPOSITORY_NAME);
        SonarRuntime sonarRuntime = SonarRuntimeImpl.forSonarQube(Version.create(9, 8), SonarQubeSide.SCANNER, SonarEdition.DEVELOPER);
        RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(Xml.RULES_SPECIFICATIONS_XML_PATH, Xml.PROFILE_PATH,sonarRuntime);

        // add the new checks
        ruleMetadataLoader.addRulesByAnnotatedClass(repository, XmlCheckList.getXmlChecks());
        repository.done();
    }
}
