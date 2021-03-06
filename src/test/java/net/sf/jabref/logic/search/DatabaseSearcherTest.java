package net.sf.jabref.logic.search;

import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibEntry;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseSearcherTest {

    public static final SearchQuery INVALID_SEARCH_QUERY = new SearchQuery("\\asd123{}asdf", true, true);

    @Test
    public void testGetDatabaseFromMatches_emptyDatabase() throws Exception {
        BibDatabase database = new BibDatabase();
        BibDatabase newDatabase = new DatabaseSearcher(new SearchQuery("whatever", true, true), database).getDatabaseFromMatches();
        assertTrue(newDatabase.getEntries().isEmpty());
    }

    @Test
    public void testGetDatabaseFromMatches_emptyDatabaseInvalidSearchExpression() throws Exception {
        BibDatabase database = new BibDatabase();
        BibDatabase newDatabase = new DatabaseSearcher(INVALID_SEARCH_QUERY, database).getDatabaseFromMatches();
        assertTrue(newDatabase.getEntries().isEmpty());
    }

    @Test
    public void testGetDatabaseFromMatches_databaseWithEmptyEntries() throws Exception {
        BibDatabase database = new BibDatabase();
        database.insertEntry(new BibEntry());
        BibDatabase newDatabase = new DatabaseSearcher(new SearchQuery("whatever", true, true), database).getDatabaseFromMatches();
        assertTrue(newDatabase.getEntries().isEmpty());
    }

    @Test
    public void testGetDatabaseFromMatches_databaseWithEntries() throws Exception {
        BibDatabase database = new BibDatabase();
        BibEntry entry = new BibEntry();
        entry.setType("article");
        entry.setField("author", "harrer");
        database.insertEntry(entry);
        BibDatabase newDatabase = new DatabaseSearcher(new SearchQuery("whatever", true, true), database).getDatabaseFromMatches();
        assertTrue(newDatabase.getEntries().isEmpty());
    }

    @Test
    public void testGetDatabaseFromMatches_databaseWithEntriesWithCorrectMatch() throws Exception {
        BibDatabase database = new BibDatabase();
        BibEntry entry = new BibEntry();
        entry.setType("article");
        entry.setField("author", "harrer");
        database.insertEntry(entry);
        BibDatabase newDatabase = new DatabaseSearcher(new SearchQuery("harrer", true, true), database).getDatabaseFromMatches();
        assertEquals(1, newDatabase.getEntryCount());
        assertEquals(entry, newDatabase.getEntries().iterator().next());
    }
}