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

package org.symphonyoss.vb.services;



import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.client.SymphonyClient;
import org.symphonyoss.client.SymphonyClientFactory;
import org.symphonyoss.client.model.Room;
import org.symphonyoss.client.services.ConnectionsService;
import org.symphonyoss.client.services.RoomService;
import org.symphonyoss.symphony.clients.model.SymMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;


/**
 *
 * <p>
 * <p>
 * <p>
 * REQUIRED VM Arguments or System Properties:
 * <p>
 * -Dsessionauth.url=https://pod_fqdn:port/sessionauth
 * -Dkeyauth.url=https://pod_fqdn:port/keyauth
 * -Dsymphony.agent.pod.url=https://agent_fqdn:port/pod
 * -Dsymphony.agent.agent.url=https://agent_fqdn:port/agent
 * -Dcerts.dir=/dev/certs/
 * -Dkeystore.password=(Pass)
 * -Dtruststore.file=/dev/certs/server.truststore
 * -Dtruststore.password=(Pass)
 * -Dbot.user=bot.user1
 * -Dbot.domain=@domain.com
 * -Duser.call.home=frank.tarsillo@markit.com
 * -Droom.stream=(Stream)
 * <p>
 * <p>
 * <p>
 * <p>
 * Created by Frank Tarsillo on 5/15/2016.
 */
@ApplicationScoped
@Service
public class VoteBotService {


    private final Logger logger = LoggerFactory.getLogger(VoteBotService.class);
    private SymphonyClient symClient;
    private RoomService roomService;
    private ConnectionsService connectionsService;


    private static VoteBotService self = new VoteBotService();

    private HashMap<String, VotingSession> calls = new HashMap<>();




    public VoteBotService() {


        init();




    }

    public static VoteBotService get() {
        return self;
    }

    public static void main(String[] args) {


        System.out.println("ChatExample starting...");
        new VoteBotService();

    }

    public void init() {


        try {

            //Create a basic client instance.
            symClient = SymphonyClientFactory.getClient(SymphonyClientFactory.TYPE.BASIC);

            logger.debug("{} {}", System.getProperty("sessionauth.url"),
                    System.getProperty("keyauth.url"));


            new AuthRefreshTask(symClient).runTask();

            TimerTask authRefreshTask = new AuthRefreshTask(symClient);
            // running timer task as daemon thread
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(authRefreshTask, 80000, 7200 * 1000);

            //With a valid SymAuth we can now init our client.
            symClient.init(
                    symClient.getSymAuth(),
                    System.getProperty("bot.user") + "@" + System.getProperty("bot.domain"),
                    System.getProperty("symphony.agent.agent.url"),
                    System.getProperty("symphony.agent.pod.url")
            );


            //Init connection service.
            connectionsService = new ConnectionsService(symClient);

            //Optional to auto accept connections.
            connectionsService.setAutoAccept(true);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void onRoomMessage(SymMessage roomMessage) {

        Room room = roomService.getRoom(roomMessage.getId());

        if (room != null && roomMessage.getMessage() != null)
            logger.debug("New room message detected from room: {} on stream: {} from: {} message: {}",
                    room.getRoomDetail().getRoomAttributes().getName(),
                    roomMessage.getStreamId(),
                    roomMessage.getFromUserId(),
                    roomMessage.getMessage()

            );





    }




    public SymphonyClient getSymClient() {
        return symClient;
    }

    public void removeCallSession(String ticketId){

        logger.info("Removing ticketId: [{}]", ticketId);

        if(calls.get(ticketId)!=null)
            logger.info("Found callsession for ticketId: [{}]", ticketId);


        calls.remove(ticketId);

    }


}




