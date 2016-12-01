package net.ottleys;


import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxEvent;
import com.box.sdk.EventListener;
import com.box.sdk.EventStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * Created by jottley on 11/21/16.
 */

@Component
public class BoxApplicationListener
{
    private static final Log logger = LogFactory.getLog(BoxApplicationListener.class);

    @Value("${box.developerId}")
    private String developerId;

    private static EventStream stream;


    @PostConstruct
    public void listenForEntries()
    {

        BoxAPIConnection api = new BoxAPIConnection(developerId);
        stream = new EventStream(api);
        stream.addListener(new EventListener()
        {
            public void onEvent(BoxEvent event)
            {
                logger.info("==> Event Start <==");
                logger.info("==> Event Type: " + event.getType());
                logger.info("==> Event Created By: " + event.getCreatedBy().getName());
                logger.info("==> Event Created At: " + event.getCreatedAt());
                logger.info("==> Event Source: " + event.getSourceJSON());
                logger.info("==> Event End <==");
            }


            @Override public void onNextPosition(long l)
            {

            }


            @Override public boolean onException(Throwable throwable)
            {
                return false;
            }
        });
        stream.start();
    }

    @PreDestroy
    public void destroy()
    {
        if (stream != null && stream.isStarted())
        {
            stream.stop();
        }
    }

}
