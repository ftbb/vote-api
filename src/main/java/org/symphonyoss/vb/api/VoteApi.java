
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

package org.symphonyoss.vb.api;

/**
 * TTID LLC.
 * Created by Frank on 8/2/2015.
 */



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.vb.impl.VoteApiService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@RequestScoped
@Api(value = "/mtm", description = "Symphony Notification Service Service")
@Path("/mtm")
public class VoteApi {

    private Logger logger = LoggerFactory.getLogger(VoteApi.class);

    @Inject
    private VoteApiService delegate;





//    @POST
//    @RolesAllowed("ADMIN")
//    @Path("chat")
//    @Consumes({"application/json"})
//    @Produces({"application/json"})
//    @ApiOperation(value = "Create a Symphony Chat between two or more parties related to an MTM ticket ID", response = String.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully created Symphony Chat", response = String.class),
//            @ApiResponse(code = 401, message = "Access denied for this call."),
//            @ApiResponse(code = 403, message = "Account not verified"),
//            @ApiResponse(code = 412, message = "Precondition failed. User might exist or domain not in whitelist."),
//            @ApiResponse(code = 500, message = "Internal server error")}
//    )
//    public Response chat(MtmTradeBreak mtmTradeBreak) {
//
//        return delegate.chat(mtmTradeBreak);
//
//    }



    @GET
    @RolesAllowed("ADMIN")
    @Path("echo")
    @Produces({"application/json"})
    @ApiOperation(value = "Simple status echo", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return status echo", response = String.class),
            @ApiResponse(code = 401, message = "Access denied for this call."),
            @ApiResponse(code = 403, message = "Account not verified"),
            @ApiResponse(code = 412, message = "Precondition failed. User might exist or domain not in whitelist."),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public Response echo() {

        return delegate.echo();

    }

}

