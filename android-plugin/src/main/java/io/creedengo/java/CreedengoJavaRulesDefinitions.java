package io.creedengo.java;

import org.apache.tomcat.util.bcel.Const;
import org.sonar.api.SonarRuntime;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;
import org.reflections.Reflections;


import java.util.ArrayList;

/*
 * Creedengo iOS plugin - Help the earth, adopt this green plugin for your applications
 * Copyright © 2024 green-code-initiative (https://green-code-initiative.org/)
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


public class CreedengoJavaRulesDefinitions implements RulesDefinition {

/**
 * Loads local rules definition.
 * This should be removed once all rules are defined in the common rules repository (<a href="https://github.com/green-code-initiative/creedengo/tree/main/creedengo-rules-specifications">...</a>)
 */
    private static final String RESOURCE_BASE_PATH = "org/green-code-initiative/rules/java";

    private static final String NAME = Java.REPOSITORY_NAME;
    private static final String LANGUAGE = Java.KEY;
    private static final String JAVA_REPOSITORY_KEY = "creedengo-android-java";

    private final SonarRuntime sonarRuntime;

    public CreedengoJavaRulesDefinitions(SonarRuntime sonarRuntime) {
        this.sonarRuntime = sonarRuntime;
    }

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(JAVA_REPOSITORY_KEY, LANGUAGE).setName(NAME);
        RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(RESOURCE_BASE_PATH, sonarRuntime);
        Reflections reflections = new Reflections("org.greencodeinitiative.creedengo.ios.java.checks");
        Set<Class<? extends JavaRuleCheck>> checkClasses = reflections.getSubTypesOf(JavaRuleCheck.class);
        ruleMetadataLoader.addRulesByAnnotatedClass(repository, new ArrayList<>(checkClasses));
        repository.done();
    }

    public String repositoryKey() {
        return Const.JAVA_REPOSITORY_KEY;
    }
}

