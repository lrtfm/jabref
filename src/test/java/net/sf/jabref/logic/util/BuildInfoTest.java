package net.sf.jabref.logic.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuildInfoTest {

    @Test
    public void testDefaults() {
        BuildInfo buildInfo = new BuildInfo("asdf");
        assertEquals("*unknown*", buildInfo.getVersion());
    }

    @Test
    public void testFileImport() {
        BuildInfo buildInfo = new BuildInfo("/net/sf/jabref/util/build.properties");
        assertEquals("42", buildInfo.getVersion());
    }

}