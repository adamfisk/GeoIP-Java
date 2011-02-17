package com.maxmind.geoip;

/* CountryLookupTest.java */

/* Only works with GeoIP Country Edition */
/* For Geoip City Edition, use CityLookupTest.java */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class CountryLookupTest {

    @Test
    public void testCountryLookups() throws Exception {
        final File dir = new File("/usr/local/share/GeoIP");

        final String fileName = "GeoIP.dat";
        final File file;
        if (dir.isDirectory()) {
            file = new File(dir, fileName);
        }
        else {
            file = new File(fileName);
        }
        
        assertTrue("No data file found!! Download GeoIP.dat", file.isFile());
        
        // You should only call LookupService once, especially if you use
        // GEOIP_MEMORY_CACHE mode, since the LookupService constructor
        // takes up
        // resources to load the GeoIP.dat file into memory
        // LookupService cl = new
        // LookupService(dbfile,LookupService.GEOIP_STANDARD);
        final LookupService cl = new LookupService(file,
            LookupService.GEOIP_MEMORY_CACHE);

        assertEquals("IT", cl.getCountry("151.38.39.114").getCode());
        assertEquals("Italy", cl.getCountry("151.38.39.114").getName());
        assertEquals("United States", cl.getCountry("12.25.205.51").getName());
        assertEquals("United States", cl.getCountry("64.81.104.131").getName());
        assertEquals("Colombia", cl.getCountry("200.21.225.82").getName());

        cl.close();
    }
}
