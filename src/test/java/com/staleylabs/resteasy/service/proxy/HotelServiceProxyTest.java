package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.beans.Room;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * StaleyLabs
 *
 * @author Sean M. Staley
 * @version 1.0 (9/1/13)
 */

public class HotelServiceProxyTest {

    private static final String USER_ID = "2ab3c";

    private static final String HOTEL_ID = "333323";

    private static final String USERNAME = "joe";

    private static final String PASSWORD = "doe";

    private static final String ORGANIZATION_ID = "12345";

    @InjectMocks
    private HotelServiceProxy hotelServiceProxy;

    @Mock
    private HotelServiceImpl hotelServiceImpl;

    @Before
    public void setUp() throws Exception {
        hotelServiceProxy = new HotelServiceProxy();

        initMocks(this);
    }

    @Test
    public void testAddRoomToHotel_works() throws InsufficientInformationException {
        Room room = new Room();
        room.setRoomCapacity(1);
        room.setRoomCost(23.33);
        room.setRoomName("Magnolia Room");

        hotelServiceProxy.addRoomToHotel(room, HOTEL_ID);

        verify(hotelServiceImpl, times(1)).addRoomToHotel(room, HOTEL_ID);
    }

    @Test(expected = InsufficientInformationException.class)
    public void testAddRoomToHotel_throws_exception() throws InsufficientInformationException {
        Room room = new Room();
        room.setRoomCost(23.33);
        room.setRoomName("Magnolia Room");

        hotelServiceProxy.addRoomToHotel(room, HOTEL_ID);
    }

    private Object buildSecureUser(boolean isAdmin) {
        SecureRestEasyUser user;

        if (isAdmin) {
            final List<GrantedAuthority> authorities = new ArrayList<>(2);
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            user = new SecureRestEasyUser(USER_ID, USERNAME, PASSWORD, true, true, true, true, authorities, ORGANIZATION_ID);
        } else {
            final List<GrantedAuthority> authorities = new ArrayList<>(1);
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            user = new SecureRestEasyUser(USER_ID, USERNAME, PASSWORD, true, true, true, true, authorities, ORGANIZATION_ID);
        }

        return user;
    }
}
