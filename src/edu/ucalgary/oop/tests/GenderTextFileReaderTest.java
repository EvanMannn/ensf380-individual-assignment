package edu.ucalgary.oop.tests;

import org.junit.Before;
import org.junit.Test;

import edu.ucalgary.oop.GenderTextFileReader;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class GenderTextFileReaderTest {
    private GenderTextFileReader gtrWindows;
    private GenderTextFileReader gtrUnix;

    @Before
    public void setUp() {
        gtrWindows = new GenderTextFileReader("..\\data\\GenderOptions.txt");
        gtrUnix = new GenderTextFileReader("../data/GenderOptions.txt");
    }

    @Test
    public void testConstructorsWithWindowsAndUnixFilepaths() {
        assertNotNull("The file path variable should have been properly initalized", gtrWindows.getGenders());
        assertNotNull("The file path variable should have been properly initalized", gtrUnix.getGenders());
    }

    @Test
    public void testConstructorWithInvalidFilePath() {
        GenderTextFileReader invalidGtr = new GenderTextFileReader("BadFilePath.txt");
        assertEquals("The length of the list of valid genders should be zero", 0, invalidGtr.getGenders().size());
    }

}
