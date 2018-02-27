package org.linkedgeodesy.rdf4jext.rdf4j;

import java.util.HashSet;
import java.util.List;
import org.eclipse.rdf4j.query.BindingSet;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class RDF4J_20Test {

    public RDF4J_20Test() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testQueryORDEREDLIST() throws Exception {
        System.out.println("test query triplestore ORDEREDLIST");
        String query = "SELECT * WHERE { ?s ?p ?o }";
        List<BindingSet> result = RDF4J_20.SPARQLquery("rdf4j-ext", "http://ls-dev.i3mainz.hs-mainz.de/rdf4j-server", query);
        List<String> s = RDF4J_20.getValuesFromBindingSet_ORDEREDLIST(result, "s");
        List<String> p = RDF4J_20.getValuesFromBindingSet_ORDEREDLIST(result, "p");
        List<String> o = RDF4J_20.getValuesFromBindingSet_ORDEREDLIST(result, "o");
        assertNotSame(0, result.size());
        assertEquals(result.size(), s.size());
        assertEquals(result.size(), p.size());
        assertEquals(result.size(), o.size());
        for (int i = 0; i < result.size(); i++) {
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#worksAs")) {
                assertEquals(o.get(i), "http://example.org#SoftwareEngineer");
            }
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#worksAt")) {
                assertEquals(o.get(i), "http://example.org#RGZM");
            }
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#hasAge")) {
                assertEquals(o.get(i), "31");
            }
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#hasSize")) {
                assertEquals(o.get(i), "1.61");
            }
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#hasName")) {
                assertEquals(o.get(i), "Florian Thiery");
            }
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#speaks")) {
                assertEquals(o.get(i), "\"deutsch\"@de");
            }
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#hasMail")) {
                assertEquals(o.get(i), "mailto:thiery@rgzm.de");
            }
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#hasWebsite")) {
                assertEquals(o.get(i), "https://linkedgeodesy.org");
            }
            if (s.get(i).equals("http://example.org#Florian") && p.get(i).equals("http://example.org#hasHomepage")) {
                assertEquals(o.get(i), "http://florian-thiery.de");
            }
        }
    }

    @Test
    public void testQueryUNIQUESET() throws Exception {
        System.out.println("test query triplestore UNIQUESET");
        String query = "SELECT * WHERE { ?s ?p ?o }";
        List<BindingSet> result = RDF4J_20.SPARQLquery("rdf4j-ext", "http://ls-dev.i3mainz.hs-mainz.de/rdf4j-server", query);
        HashSet<String> s = RDF4J_20.getValuesFromBindingSet_UNIQUESET(result, "s");
        HashSet<String> p = RDF4J_20.getValuesFromBindingSet_UNIQUESET(result, "p");
        HashSet<String> o = RDF4J_20.getValuesFromBindingSet_UNIQUESET(result, "o");
        assertNotSame(0, result.size());
        assertEquals(result.size(), p.size());
        assertEquals(result.size(), o.size());
        HashSet<String> expectedRows = new HashSet<>();
        expectedRows.add("http://example.org#SoftwareEngineer");
        expectedRows.add("http://example.org#RGZM");
        expectedRows.add("31");
        expectedRows.add("1.61");
        expectedRows.add("Florian Thiery");
        expectedRows.add("\"deutsch\"@de");
        expectedRows.add("mailto:thiery@rgzm.de");
        expectedRows.add("https://linkedgeodesy.org");
        expectedRows.add("http://florian-thiery.de");
        assertTrue(expectedRows.equals(o));
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("test update triplestore");
        int before = RDF4J_20.getNumberOfStatements("rdf4j-ext", "http://ls-dev.i3mainz.hs-mainz.de/rdf4j-server");
        String update = "INSERT DATA { <http://example.org#example:Allard> <http://example.org#worksAt> <http://example.org#RGZM>. }";
        RDF4J_20.SPARQLupdate("rdf4j-ext", "http://ls-dev.i3mainz.hs-mainz.de/rdf4j-server", update);
        int after1 = RDF4J_20.getNumberOfStatements("rdf4j-ext", "http://ls-dev.i3mainz.hs-mainz.de/rdf4j-server");
        assertNotSame(after1, before);
        System.out.println("test update triplestore input " + (after1 - before) + " line");
        update = "DELETE DATA { <http://example.org#example:Allard> <http://example.org#worksAt> <http://example.org#RGZM>. }";
        RDF4J_20.SPARQLupdate("rdf4j-ext", "http://ls-dev.i3mainz.hs-mainz.de/rdf4j-server", update);
        int after2 = RDF4J_20.getNumberOfStatements("rdf4j-ext", "http://ls-dev.i3mainz.hs-mainz.de/rdf4j-server");
        assertEquals(after2, before);
        System.out.println("test update triplestore deleted " + (after1 - after2) + " line");
    }

}
