/*
 *
 *
 * Copyright 2016 The Symphony Software Foundation
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.symphonyoss.vb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;


@SessionScoped
public class Authenticator implements ClientRequestFilter, Serializable {
    private Logger logger = LoggerFactory.getLogger(Authenticator.class);

    //private final String user;
    //private final String password;
    
    private final MultivaluedMap<String,String> authHeaders;

    public Authenticator(){
        //user = null;
        //password = null;
    	authHeaders = new MultivaluedHashMap<String,String>();

    }
    public Authenticator(MultivaluedMap<String,String> authHeaders){
        //user = null;
        //password = null;
    	this.authHeaders = authHeaders;

    }

    public Authenticator(String user, String password) {
    	authHeaders = new MultivaluedHashMap<String,String>();
        final String basicAuthentication = getBasicAuthentication(user,password);
    	authHeaders.add("Authorization", basicAuthentication);
    }

    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();

        headers.add("Authorization", authHeaders.get("Authorization"));

    }

    private static String getBasicAuthentication(String user, String password) {
        String token = user + ":" + password;
        try {
            return "Basic " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException("Cannot encode with UTF-8", ex);
        }
    }


    public void dump() {
        for (Iterator<String> it = authHeaders.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            List<String> values = authHeaders.get(key);
            for (int j=0; j<values.size(); j++) {
                logger.info("--- Authorization Header -> {} : {}", key, values.get(j));
            }
        }

    }

}
