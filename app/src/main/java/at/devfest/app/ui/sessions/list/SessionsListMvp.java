package at.devfest.app.ui.sessions.list;

import at.devfest.app.data.app.model.Session;

import java.util.List;

public interface SessionsListMvp {

    interface SessionDetailsHandler {
        void startSessionDetails(Session session);
    }
    interface View extends SessionDetailsHandler {
        void initToobar(String title);

        void initSessionsList(List<Session> sessions);

    }
}
