package aj.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import aj.csi.CaseyException;
import aj.csi.ConfigurationService;
import aj.csi.assets.UploadServiceImpl;
import aj.csi.dalsvc.DalUtil;
import aj.csi.dalsvc.bo.MetadataService;
import aj.csi.dalsvc.bo.NewCountryMasterDataService;
import aj.csi.dalsvc.bo.NewTwostCampaignDataService;
import aj.csi.dalsvc.insights.PimKpiDataService;
import aj.csi.pim.kpi.FlightMetricsPimKpi;
import aj.csi.pim.lead.CountryMaster;

public class CountryMasterTest {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CountryMasterTest.class);
	private NewCountryMasterDataService countryCSV ;
	
	@Before
	public void setUp() throws Exception {
		ConfigurationService configService = new ConfigurationService();
		MetadataService ms = new MetadataService(configService);
		
		countryCSV= new  NewCountryMasterDataService(configService, ms);
		
	}

	@Test
	public void testJbRollUpTest() throws FileNotFoundException, IOException, CaseyException, CsvException{
		
	    String fileName = "src/test/resources/CountryMasterDetails.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
		}
	
	}
	@Test
	public void testInsertFields() throws FileNotFoundException, IOException, CaseyException{
		//	String kpiDataCsv = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("data.csv"));
		CsvMapper mapper = new CsvMapper();
		 mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"));
	    CsvSchema schema = mapper.schemaFor(CountryMaster.class).withHeader().withoutColumns();
	   
	   // schema.column(name)
	 
	    ObjectReader oReader = mapper.reader(CountryMaster.class).with(schema);
	    
	   try (Reader reader = new FileReader("src/test/resources/CountryMasterDetails.csv")) {

	           MappingIterator<CountryMaster> it = oReader.readValues(reader);
	           
	           while (it.hasNext()) {
	        	  
	        	   CountryMaster current = it.next();
	        	   current.setTenantId("2909");
	        	   countryCSV.add(current);
	        	   System.out.println(  ""+current.getId());
	           }
	        	   
		}
	}
	}
