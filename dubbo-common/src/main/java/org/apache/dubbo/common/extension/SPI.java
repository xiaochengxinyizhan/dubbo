/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.common.extension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker for extension interface
 * 扩展接口标识器
 * <p/>
 * Changes on extension configuration file <br/>
 * 在扩展配置文件中的更改
 * Use <code>Protocol</code> as an example, its configuration file 'META-INF/dubbo/com.xxx.Protocol' is changed from: <br/>
 * 使用<code>Protocol</code>作为一个例子，他的配置文件为'META-INF/dubbo/com.xxx.Protocol'改变从com.foo.XxxProtocol变为KV键值对xxx=com.foo.XxxProtocol。
 * <pre>
 *     com.foo.XxxProtocol
 *     com.foo.YyyProtocol
 * </pre>
 * <p>
 * to key-value pair <br/>
 * <pre>
 *     xxx=com.foo.XxxProtocol
 *     yyy=com.foo.YyyProtocol
 * </pre>
 * <br/>
 * The reason for this change is:
 * 这个改变的原因是
 * <p>
 * If there's third party library referenced by static field or by method in extension implementation, its class will
 * fail to initialize if the third party library doesn't exist. In this case, dubbo cannot figure out extension's id
 * therefore cannot be able to map the exception information with the extension, if the previous format is used.
 * 如果这里有第三方库引用通过静态属性或者扩展实现的方法，他的类将不可以被初始化，如果第三方库不存在。
 * 为了防止dubbo不能指出扩展的ID这个问题。如果使用之前的格式，则不能够映射这个扩展的异常信息。
 * <p/>
 * For example:
 * 举个例子：
 * <p>
 * Fails to load Extension("mina"). When user configure to use mina, dubbo will complain the extension cannot be loaded,
 * instead of reporting which extract extension implementation fails and the extract reason.
 * 失败加载了扩展("mina")。当使用者配置使用mina，dubbo将因为不能加载这个扩展，而不是报告提取哪个扩展实现失败了以及提取的失败原因。
 * </p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * default extension name
     * 默认的扩展名字
     */
    String value() default "";

}