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
package io.creedengo.common;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.apache.commons.lang.StringUtils;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.debt.DebtRemediationFunction;
import org.sonar.api.server.rule.RulesDefinition;

/**
 * Loads rule metadata from classpath resources (creedengo-rules-specifications JAR).
 */
public final class RuleSpecificationLoader {

  private static final Gson GSON = new Gson();

  private RuleSpecificationLoader() {
  }

  public static void load(RulesDefinition.NewRule rule, String ruleKey, String resourceBasePath) {
    String normalizedKey = normalizeKey(ruleKey);
    String basePath = normalizeBasePath(resourceBasePath);

    RuleMetadata metadata = readJson(basePath + "/" + normalizedKey + ".json");
    if (metadata != null) {
      applyMetadata(rule, metadata);
    }

    String html = readText(basePath + "/" + normalizedKey + ".html");
    if (html != null) {
      rule.setHtmlDescription(html);
    } else {
      rule.setMarkdownDescription("Specification missing for rule: " + normalizedKey);
    }
  }

  private static void applyMetadata(RulesDefinition.NewRule rule, RuleMetadata metadata) {
    if (StringUtils.isNotEmpty(metadata.title)) {
      rule.setName(metadata.title);
    }
    if (StringUtils.isNotEmpty(metadata.defaultSeverity)) {
      rule.setSeverity(metadata.defaultSeverity.toUpperCase(Locale.US));
    }
    if (StringUtils.isNotEmpty(metadata.type)) {
      rule.setType(RuleType.valueOf(metadata.type));
    }
    if (StringUtils.isNotEmpty(metadata.status)) {
      rule.setStatus(RuleStatus.valueOf(metadata.status.toUpperCase(Locale.US)));
    }
    if (metadata.tags != null && metadata.tags.length > 0) {
      rule.addTags(metadata.tags);
    }
    if (metadata.remediation != null) {
      rule.setDebtRemediationFunction(metadata.remediation.toSonar(rule.debtRemediationFunctions()));
      rule.setGapDescription(metadata.remediation.linearDesc);
    }
  }

  private static RuleMetadata readJson(String resourcePath) {
    String json = readText(resourcePath);
    return json == null ? null : GSON.fromJson(json, RuleMetadata.class);
  }

  private static String readText(String resourcePath) {
    try (InputStream inputStream = RuleSpecificationLoader.class.getResourceAsStream(resourcePath)) {
      if (inputStream == null) {
        return null;
      }
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        return reader.lines().collect(Collectors.joining("\n"));
      }
    } catch (Exception e) {
      throw new IllegalStateException("Failed to read resource: " + resourcePath, e);
    }
  }

  private static String normalizeKey(String ruleKey) {
    return ruleKey == null ? "" : ruleKey.trim();
  }

  private static String normalizeBasePath(String resourceBasePath) {
    if (resourceBasePath.startsWith("/")) {
      return resourceBasePath;
    }
    return "/" + resourceBasePath;
  }

  public static class RuleMetadata {
    public String title;
    public String status;
    public String type;
    public String[] tags;
    public String defaultSeverity;
    @Nullable
    public Remediation remediation;
  }

  public static class Remediation {
    public String func;
    public String constantCost;
    public String linearDesc;
    public String linearOffset;
    public String linearFactor;

    public DebtRemediationFunction toSonar(RulesDefinition.DebtRemediationFunctions drf) {
      if (func == null) {
        return drf.constantPerIssue("10min");
      }
      if (func.startsWith("Constant")) {
        return drf.constantPerIssue(constantCost.replace("mn", "min"));
      }
      if ("Linear".equals(func)) {
        return drf.linear(linearFactor.replace("mn", "min"));
      }
      return drf.linearWithOffset(linearFactor.replace("mn", "min"), linearOffset.replace("mn", "min"));
    }
  }
}
