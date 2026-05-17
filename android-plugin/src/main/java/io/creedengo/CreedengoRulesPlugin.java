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
package io.creedengo;

import io.creedengo.java.JavaCreedengoProfile;
import io.creedengo.java.JavaFileCheckRegistrar;
import io.creedengo.java.JavaRulesDefinition;
import io.creedengo.xml.XmlCreedengoProfile;
import io.creedengo.xml.XmlRulesDefinition;
import io.creedengo.xml.XmlSensor;
import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;
import org.sonar.plugins.groovy.GroovySensor;
import org.sonar.plugins.groovy.codenarc.CodeNarcSensor;
import org.sonar.plugins.groovy.foundation.Groovy;

/**
 * Entry point of your plugin containing your custom rules
 */
public class CreedengoRulesPlugin implements Plugin {

  public static final String FILE_SUFFIXES_KEY = "creedengo.xml.file.suffixes";

  @Override
  public void define(Context context) {

    // ===Add Java rules extension ===
    // server extensions -> objects are instantiated during server startup
    context.addExtension(JavaRulesDefinition.class);
    // batch extensions -> objects are instantiated during code analysis
    context.addExtension(JavaFileCheckRegistrar.class);
    // === Add Java rules profile ===
    context.addExtension(JavaCreedengoProfile.class);
    // === Add XML rules extension ===
    context.addExtensions(
            PropertyDefinition.builder(FILE_SUFFIXES_KEY)
                    .name("File suffixes")
                    .description("Comma-separated list of suffixes for files to analyze.")
                    .defaultValue(".xml,.xsd,.xsl")
                    .multiValues(true)
                    .category("XML")
                    .onQualifiers(Qualifiers.PROJECT)
                    .build(),
            XmlRulesDefinition.class,
            XmlCreedengoProfile.class,
            XmlSensor.class);
    // === Add Groovy rules extension ===
    context.addExtensions(Groovy.getExtensions())
            .addExtensions(GroovySensor.getExtensions())
            .addExtensions(CodeNarcSensor.getExtensions());
  }

}
