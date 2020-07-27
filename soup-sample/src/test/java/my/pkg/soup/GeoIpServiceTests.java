package my.pkg.soup;

import org.testng.Assert;
import org.testng.annotations.Test;
import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;


import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("109.252.87.78");
        assertEquals(geoIP.getCountryCode(), "RUS");


    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("217.69.139.xxx");
        assertEquals(geoIP.getCountryCode(), "RUS");

    }
}

