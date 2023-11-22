/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.auth.config;

import com.alibaba.nacos.sys.module.ModuleState;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;

import static com.alibaba.nacos.auth.config.AuthModuleStateBuilder.AUTH_ENABLED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthModuleStateBuilderTest {
    
    @Mock
    private ConfigurableApplicationContext context;
    
    @Mock
    private AuthConfigs authConfigs;
    
    @Before
    public void setUp() throws Exception {
        when(context.getBean(AuthConfigs.class)).thenReturn(authConfigs);
        ApplicationUtils.injectContext(context);
        when(authConfigs.getNacosAuthSystemType()).thenReturn("nacos");
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testBuild() {
        ModuleState actual = new AuthModuleStateBuilder().build();
        assertFalse((Boolean) actual.getStates().get(AUTH_ENABLED));
        assertFalse((Boolean) actual.getStates().get("login_page_enabled"));
        assertEquals("nacos", actual.getStates().get("auth_system_type"));
    }
}