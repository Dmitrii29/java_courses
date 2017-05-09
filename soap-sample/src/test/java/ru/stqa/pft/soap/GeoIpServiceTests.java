package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Mitrich on 09.05.2017.
 */
public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("62.84.51.24");
    assertEquals(geoIP.getCountryCode(), "KAZ");
  }

  @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("62.84.51.xxx");
    assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
  }
}
