/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.buildsetup.plugins.internal;

import org.gradle.api.internal.project.ProjectInternal;
import org.gradle.buildsetup.plugins.BuildSetupPlugin;
import org.gradle.configuration.ProjectConfigureAction;

public class BuildSetupAutoApplyAction implements ProjectConfigureAction {
    public void execute(ProjectInternal projectInternal) {
        if (buildSetupShouldBeAutoApplied(projectInternal)) {
            projectInternal.getPlugins().apply("build-setup");
        }
    }

    private boolean buildSetupShouldBeAutoApplied(ProjectInternal projectInternal) {
        if (!projectInternal.getGradle().getStartParameter().getTaskNames().contains(BuildSetupPlugin.SETUP_BUILD_TASK_NAME)) {
            return false;
        }
        if (projectInternal.file("build.gradle").exists()) {
            return false;
        }
        return true;
    }
}
