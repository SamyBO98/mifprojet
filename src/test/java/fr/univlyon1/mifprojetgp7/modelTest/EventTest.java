package fr.univlyon1.mifprojetgp7.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EventTest {

    Event eventT = new Event();

    @Test
    public void TestEventID() {
        System.out.println(">> TEST: Event ID <<");

        eventT.setEventID(5);
        assertEquals(5, eventT.getId());
        eventT.setEventID(-1);
    }

    @Test
    public void TestEventTitle() {
        System.out.println(">> TEST: Event Title <<");

        eventT.setTitle("test");
        assertEquals("test", eventT.getTitle());
        eventT.setTitle(null);
    }

    @Test
    public void TestEventContent() {
        System.out.println(">> TEST: Event Content <<");

        eventT.setContent("test");
        assertEquals("test", eventT.getContent());
        eventT.setContent(null);
    }


}
