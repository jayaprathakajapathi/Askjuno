package googleapi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.google.api.services.people.v1.model.EmailAddress;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;
import com.google.api.services.people.v1.model.PhoneNumber;
import com.google.api.services.people.v1.model.Url;

import core.HabServiceException;
import goog.contact.ContactCreateResponse;
import goog.contact.CreateContactAction;
import goog.contact.GoogleContactInfo;

public class CreateContactActionTest {

	@Test
	public void test() throws HabServiceException {

		GoogleContactInfo info = new GoogleContactInfo();

		String accessToken = "ya29.A0ARrdaM-aOa-7hZP49jNkqj6e1fdsGF9QyQWCuq--JwMZ_eOoCq-z2rfIiDx7FHeEarXSncODqcsOB7lxJvWppJA3S0YHmdvpQgOXP-uYhejOWB7rIdUPSHoFWh6S1vFasCm0BfQnB-2k9HDonQnbaSASTtMq";
		String junoBusinessName = "Juno";

		info.setFullName("kavi");
		info.setEmaiId("kavi@askjuno.com");
		info.setLocation("chennai");
		info.setPhoneNumber("977653927");
		info.setJunoUrl("");
		// ArrayList<PhoneNumber> phoneList = createPhoneNumber();

		Collection<GoogleContactInfo> collection = new ArrayList<>();
		collection.add(info);
		CreateContactAction action = new CreateContactAction(accessToken, junoBusinessName);
		action.execute(collection);

	}

}
