package fr.univlyon1.mifprojetgp7.utilstest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.utils.ParseURI;

public class ParseURITest {


    @Test
    public void parseUri() {
	List<String> uri = ParseURI.parseUri("/blabla/events/salut/coucou", "events");
	assertEquals("salut", uri.get(0));
	assertEquals("coucou", uri.get(1));
    }
    
    @Test
    public void sourceUri() {
	String uri = ParseURI.sourceURI("/blabla/events/salut/coucou");
	assertEquals("blabla", uri);
    }
}
