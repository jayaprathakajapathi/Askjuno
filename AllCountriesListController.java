package aj.cheesemountain.country;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aj.csi.CaseyException;
import aj.csi.dalsvc.DalUtil;
import aj.csi.dalsvc.bo.NewCountryMasterDataService;

import aj.csi.pim.lead.CountryMaster;
import goog.contact.GoogleContactInfo;

@RestController
@RequestMapping("/add-countries")
public class AllCountriesListController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AllCountriesListController.class);

	private final NewCountryMasterDataService newCountryMasterDataService;

	public AllCountriesListController(NewCountryMasterDataService newCountryMasterDataService) {
		this.newCountryMasterDataService = newCountryMasterDataService;

	}

	@GetMapping("/details-country")
	private void addAllCountries() throws CaseyException {

		String[] locales = Locale.getISOCountries();
		
		Collection<CountryMaster> countryLists = new ArrayList<>();
		
		for (String countryCode : locales) {
			
			CountryMaster countrymaster = new CountryMaster();

			Locale obj = new Locale("", countryCode);
			String countryCodes = obj.getCountry();
			String countryName = obj.getDisplayCountry();
            String language=this.language();
			countrymaster.setId(DalUtil.createLongId());
			
			countrymaster.setTenantId("2909");
			countrymaster.setCountryName(countryName);
			countrymaster.setCountryCode(countryCodes);
			countrymaster.setLanguage(language);
			countryLists.add(countrymaster);
			newCountryMasterDataService.add(countrymaster);
		}

	}
	private String language() {
		
		Locale[] locale = Locale.getAvailableLocales();
		String	lang=null;
		for (Locale ob : locale) {
			CountryMaster countrymaster = new CountryMaster();
		lang=		ob.getDisplayLanguage();
			 countrymaster.setLanguage(lang);
			
			 
		}
return lang;
	}

}