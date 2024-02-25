package geocoding;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {
    // create a mock for ISimpleHttpClient (since it is slow to respond and may have $$$ associated)

    @Mock
    ISimpleHttpClient simpleHttpClient; // mock the http client

    @InjectMocks
    AddressResolverService resolver; // depends on the mock

    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException {

        String response = "{" +
                "  \"info\": {" +
                "    \"statuscode\": 0," +
                "    \"copyright\": {" +
                "      \"text\": \"© 2024 MapQuest, Inc.\"," +
                "      \"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\"," +
                "      \"imageAltText\": \"© 2024 MapQuest, Inc.\"" +
                "    }," +
                "    \"messages\": []" +
                "  }," +
                "  \"options\": {" +
                "    \"maxResults\": 1," +
                "    \"ignoreLatLngInput\": false" +
                "  }," +
                "  \"results\": [" +
                "    {" +
                "      \"providedLocation\": {" +
                "        \"latLng\": {" +
                "          \"lat\": 40.63436," +
                "          \"lng\": -8.65616" +
                "        }" +
                "      }," +
                "      \"locations\": [" +
                "        {" +
                "          \"street\": \"Avenida da Universidade\"," +
                "          \"adminArea6\": \"Aveiro\"," +
                "          \"adminArea6Type\": \"Neighborhood\"," +
                "          \"adminArea5\": \"Aveiro\"," +
                "          \"adminArea5Type\": \"City\"," +
                "          \"adminArea4\": \"Aveiro\"," +
                "          \"adminArea4Type\": \"County\"," +
                "          \"adminArea3\": \"\"," +
                "          \"adminArea3Type\": \"State\"," +
                "          \"adminArea1\": \"PT\"," +
                "          \"adminArea1Type\": \"Country\"," +
                "          \"postalCode\": \"3810-489\"," +
                "          \"geocodeQualityCode\": \"B1AAA\"," +
                "          \"geocodeQuality\": \"STREET\"," +
                "          \"dragPoint\": false," +
                "          \"sideOfStreet\": \"L\"," +
                "          \"linkId\": \"0\"," +
                "          \"unknownInput\": \"\"," +
                "          \"type\": \"s\"," +
                "          \"latLng\": {" +
                "            \"lat\": 40.63437," +
                "            \"lng\": -8.65625" +
                "          }," +
                "          \"displayLatLng\": {" +
                "            \"lat\": 40.63437," +
                "            \"lng\": -8.65625" +
                "          }," +
                "          \"mapUrl\": \"\"" +
                "        }" +
                "      ]" +
                "    }" +
                "  ]" +
                "}";

        lenient().when(simpleHttpClient.doHttpGet(contains("location=40.63436%2C-8.65616"))).thenReturn(response);

        // will crash for now...need to set the resolver before using it --> should not crash now
        Optional<Address> result = resolver.findAddressForLocation(40.63436, -8.65616);

        //return
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        String response = "{" +
                "  \"info\": {" +
                "    \"statuscode\": 400," +
                "    \"copyright\": {" +
                "      \"text\": \"© 2024 MapQuest, Inc.\"," +
                "      \"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\"," +
                "      \"imageAltText\": \"© 2024 MapQuest, Inc.\"" +
                "    }," +
                "    \"messages\": [" +
                "      \"Illegal argument from request: Invalid LatLng specified.\"" +
                "    ]" +
                "  }," +
                "  \"options\": {" +
                "    \"maxResults\": 1," +
                "    \"ignoreLatLngInput\": false" +
                "  }," +
                "  \"results\": [" +
                "    {" +
                "      \"providedLocation\": {}," +
                "      \"locations\": []" +
                "    }" +
                "  ]" +
                "}";

        when(simpleHttpClient.doHttpGet(contains("location=-361.00000%2C-361.00000")))
                .thenReturn(response);

        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        // verify no valid result
        assertFalse( result.isPresent());

    }

    @Test
    public void whenBadURI_thenThrowNull() throws IOException, URISyntaxException, ParseException {
        when(simpleHttpClient.doHttpGet(isNull())).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> simpleHttpClient.doHttpGet(null));
    }
}