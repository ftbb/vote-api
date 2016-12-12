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

package org.symphonyoss.vb.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.vb.services.VoteBotService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.io.Serializable;


/**
 * Created by Frank on 8/2/2015.
 */

@RequestScoped
@Service
public class VoteApiService implements Serializable {
    private Logger logger = LoggerFactory.getLogger(VoteApiService.class);


    @Inject
    VoteBotService voteBotService;


//
//    public Response transcript(String id) throws NotFoundException {
//        JsonObject jo = new JsonObject();
//        Response.Status status = Response.Status.OK;
//        Gson gson = new GsonBuilder().create();
//
//        try {
//
//            Transcript transcript = transcriptService.getTranscript(id);
//
//            if (transcript != null) {
//
//
//                for (BasicMessage symMessage : transcript.getMessages()) {
//                    logger.info("Message: {}", symMessage.getMessage());
//                }
//
//                jo.add("transcript", gson.toJsonTree(transcript));
//                jo.addProperty("message", "OK");
//
//            } else {
//                jo.addProperty("message", "The transcript was not found.");
//                status = Response.Status.NOT_FOUND;
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//
//        return Response
//                .status(status)
//                .entity(jo.toString())
//                .build();
//
//
//    }


//    public Response chat(MtmTradeBreak mtmTradeBreak) throws NotFoundException {
//        JsonObject jo = new JsonObject();
//        Response.Status status = Response.Status.OK;
//
//
//        try {
//            mtmBotService.constructChat(mtmTradeBreak);
//            jo.addProperty("message", "OK");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            status = Response.Status.PRECONDITION_FAILED;
//            jo.addProperty("message", e.getLocalizedMessage());
//        }
//
//
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//
//
//        return Response
//                .status(status)
//                .entity(jo.toString())
//                .build();
//
//
//    }

    public Response echo() throws NotFoundException {
        JsonObject jo = new JsonObject();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        jo.addProperty("message", "ECHO");

        return Response
                .status(Response.Status.OK)
                .entity(jo.toString())
                .build();


    }


}
