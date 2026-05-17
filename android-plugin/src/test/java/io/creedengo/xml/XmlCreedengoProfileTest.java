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

import org.junit.Test;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.utils.ValidationMessages;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlCreedengoProfileTest {

    @Test
    public void should_create_sonar_way_profile() {
        ValidationMessages validation = ValidationMessages.create();

        BuiltInQualityProfilesDefinition.Context context = new BuiltInQualityProfilesDefinition.Context();

        XmlCreedengoProfile definition = new XmlCreedengoProfile();
        definition.define(context);

        BuiltInQualityProfilesDefinition.BuiltInQualityProfile profile = context.profile(Xml.KEY, Xml.PROFILE_NAME);

        assertThat(profile.language()).isEqualTo(Xml.KEY);
        assertThat(profile.name()).isEqualTo(Xml.PROFILE_NAME);
        // "-1" because we do not want the rule "DarkUIBrightColors" in the profile
        assertThat(profile.rules()).hasSize(XmlCheckList.getXmlChecks().size() - 1);
        assertThat(validation.hasErrors()).isFalse();
    }
}
