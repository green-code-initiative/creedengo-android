package io.creedengo;

import org.junit.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarEdition;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.SonarRuntime;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.assertj.core.api.Assertions.assertThat;

public class CreedengoRulesPluginTest {

    public static final Version VERSION_10_3 = Version.create(10, 3);

    @Test
    public void testExtensions() {
        CreedengoRulesPlugin plugin = new CreedengoRulesPlugin();
        SonarRuntime runtime =
                SonarRuntimeImpl.forSonarQube(VERSION_10_3, SonarQubeSide.SCANNER, SonarEdition.COMMUNITY);
        Plugin.Context context = new Plugin.Context(runtime);
        plugin.define(context);
        assertThat(context.getExtensions()).hasSize(14);
    }
}
