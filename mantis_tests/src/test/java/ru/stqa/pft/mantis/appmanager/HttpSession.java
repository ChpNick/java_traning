package ru.stqa.pft.mantis.appmanager;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

/**
 * Created by Nikolay Pechenin on 13.06.2017.
 */
public class HttpSession {
    private final CloseableHttpClient httpclient;
    private final ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }
}
