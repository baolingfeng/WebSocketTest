package websocket.server;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value = "/test")
public class TestServerEndpoint {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	 
	@OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }
 
    @OnMessage
    public String onMessage(String message, Session session) {
    	logger.info("from client: " +message);
    	switch (message) {
        case "quit":
            try {
                session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Game ended"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        }
        return "From server: "+message;
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}
