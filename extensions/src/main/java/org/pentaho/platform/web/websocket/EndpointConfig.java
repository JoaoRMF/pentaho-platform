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

public class EndpointConfig {
  private Map<String, Class<? extends Endpoint>> endpoints = Collections.synchronizedMap( new HashMap() );
  private Predicate<String> isOriginAllowed;

  public EndpointConfig( Map<String, Class<? extends Endpoint>> endpoints, Predicate<String> isOriginAllowed ) {
    this.endpoints = endpoints;
    this.isOriginAllowed = isOriginAllowed;
  }

  public Map<String, Class<? extends Endpoint>> getEndpoints() {
    return endpoints;
  }

  public Predicate<String> getIsOriginAllowed() {
    return isOriginAllowed;
  }
}