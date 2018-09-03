/*!
 * Copyright 2018 Webdetails, a Hitachi Vantara company. All rights reserved.
 *
 * This software was developed by Webdetails and is provided under the terms
 * of the Mozilla Public License, Version 2.0, or any later version. You may not use
 * this file except in compliance with the license. If you need a copy of the license,
 * please go to  http://mozilla.org/MPL/2.0/. The Initial Developer is Webdetails.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. Please refer to
 * the license for the specific language governing your rights and limitations.
 */

package org.pentaho.platform.web.websocket;

import javax.websocket.Endpoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * This class should be used to configure a websocket endpoint in platform plugins.
 * Declare a bean in the plugin.spring.xml on your platform plugin.
 *
 * <pre>
 * <bean id="corsUtil" class="<local plugin CORS util class>" factory-method="getInstance" />
 * <bean id="cda.plataform.websocket.endpoint.list" class="org.pentaho.platform.web.websocket.EndpointConfig">
 *   <constructor-arg>
 *     <map>
 *       <entry key="query">
 *         <value type="java.lang.Class"><websocket endpoint implementation class></value>
 *       </entry>
 *     </map>
 *   </constructor-arg>
 *   <constructor-arg value="#{corsUtil.isCorsRequestOriginAllowedPredicate()}" />
 * </bean>
 * </pre>
 *
 * The CORS utility class usage is used as an example on how to pass a predicate as a parameter (use
 * any other mean that you found useful). The predicate can implement just the following code:
 * {@code * return domain -> this.getDomainWhitelist().contains( domain ); }
 *
 * For the websocket endpoint implementation you should go with an implementation of the javax.websocket.Endpoint
 * abstract class.
 *
 * The websocket is created in the {@link org.pentaho.platform.web.servlet.PluginDispatchServlet} initialization.
 * The endpoint will be ws://<server:port>/plugin/<plugin code>/websocket/<config map key>
 *
 */
public class EndpointConfig {
  private Map<String, Class<? extends Endpoint>> endpoints = Collections.synchronizedMap( new HashMap() );
  private Predicate<String> isOriginAllowedPredicate;

  public EndpointConfig( Map<String, Class<? extends Endpoint>> endpoints, Predicate<String> isOriginAllowedPredicate ) {
    this.endpoints = endpoints;
    this.isOriginAllowedPredicate = isOriginAllowedPredicate;
  }

  /**
   * The list of endpoints to expose as websockets. The map key is used as the final part
   * of the websocket endpoint created.
   *
   * @return the map of websocket endpoints to configure.
   */
  public Map<String, Class<? extends Endpoint>> getEndpoints() {
    return endpoints;
  }

  /**
   * Gets the predicate which evaluates if a origin is allowed for the websocket.
   *
   * @return a predicate that accepts a String and checks if the origin received as parameter is accepted.
   */
  public Predicate<String> getIsOriginAllowed() {
    return isOriginAllowedPredicate;
  }
}