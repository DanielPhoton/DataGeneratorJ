
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.github.javafaker.Address; // Excellent Fake data generator for java. 
import com.github.javafaker.Demographic;
import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import com.github.javafaker.Number;
import com.github.javafaker.Options;

import au.com.anthonybruno.Gen; //Helpful for generating data as needed
import au.com.anthonybruno.settings.CsvSettings;

public class DataGenerator 
// Created for generating data for Machine Learning Models -  Daniel Fischer
{

    public static void createMockData() {
        Faker faker = Faker.instance();
        Number number = faker.number();
        Lorem lorem = faker.lorem(); //<String> = words()
        Address address = faker.address();
        Options options = faker.options();
        Demographic demographic = faker.demographic();
        CsvSettings settings = new CsvSettings.Builder().setDelimiter('|').build();



        Gen.start()

//Individual
                .addField("indiv_id", () -> number.numberBetween(11111111111111111L, 99999999999999999L)) 	// Individual ID 17 Digits
                .addField("frst_nm", () -> faker.name().firstName()) 	// First Name
                .addField("mid_initial",  ()-> lorem.fixedString(1)) 	// Middle Initial
                .addField("lst_nm", () -> faker.name().lastName()) 		// Last Name
                .addField("age", () -> number.numberBetween(18, 98)) 	// Age
                .addField("cntry_cd",() -> "US") 	//  US OR address.countryCode()
                .addField("gndr_cd", () -> demographic.sex().charAt(0))	 // Gender M or F
                .addField("acct_nbr", () -> number.numberBetween(1111111111111111L, 9999999999999999L)) 	// Account Number 16 Digits
                .addField("acct_id", () -> number.numberBetween(111111111111L, 999999999999L))  // Account ID 12 Digits
                .addField("acct_type_ind", () -> options.option(Provider.class)) 	//  Class W,H,S,L,D,U
		        .addField("addr_id", () -> number.numberBetween(100000000000000000L, 999999999999999999L )) // addressID 18 Digits Long
		        .addField("str_num", () -> address.streetAddressNumber()) //number.numberBetween(10, 9999)) Address number
		        .addField("str_drct1", ()-> options.option(CardDirection.class)) //lorem.fixedString(1)) //lorem.fixedString(1) //
		        .addField("str_nm", () -> address.streetName()) // Street Name
		        .addField("addr_ln_2", () -> address.secondaryAddress()) // Suite number, Flat number
		        .addField("city", () -> address.cityName()) // City Name
		        .addField("cntry_cd", () -> "US" )// address.countryCode().replaceAll(address.countryCode().substring(0, 2),"US"))
		        .addField("st_cd", () -> address.stateAbbr()) // State Abbreviations
		        .addField("zipcd", () -> address.zipCode().substring(0, 5)) // First five zip code numbers
		        .addField("zip4", () -> number.numberBetween(1111, 9999)) // Last four variable codesdfasdf

                .generate(10000) 		// 10000 number of rows: Iterates to whatever number desired. Input rows MUST match.
                .asCsv(settings) 		// Changes to Pipe delimited | | | | | | above 
                .toFile("///File Location here////MockData.txt");  // MockDataCreation location; place in different directory than data generated folder


        //Copy from file
    }

    enum Provider{ W, H, S, L, D, U } // Options for applications

    enum CardDirection{ N,S,E,W } // Cardinal Directions

    public static void main(String[] args) throws IOException {

        createMockData() ;

        try(FileReader originalDataFileReader = new FileReader("/Users/ PLACE DATA FILE HERE/originalDataFilewithPipes.txt")) // Data file LOCATION
        {
            try(BufferedReader originalDataBufferedReader = new BufferedReader(originalDataFileReader))
            {
                // originalDataBufferedReader.readLine(); //Skips header
                try(FileReader mockDataFileReader = new FileReader("/Users/////MockDataCreation01.txt")) // Reading Mocked Data created 
                {
                    try(BufferedReader mockDataBufferedReader = new BufferedReader(mockDataFileReader))
                    {
                        // mockDataBufferedReader.readLine(); // Skips header
                        try (FileWriter  mockIndivWriter = new FileWriter("////IndiviMockData01.txt")) // Change Path for each iteration, otherwise data is rewritten.
                        {
                            try (FileWriter mockCustomerWriter = new FileWriter ("/////CustomerMockData01.txt")) // Change PATH for run 2
                            {
                                try (FileWriter originalHouseholdWriter = new FileWriter("////originalDataPiped.txt")) { //Just a change from csv to pipe-delimited |||
                                    while(true)
                                    {
                                        String originalDataLine = originalDataBufferedReader.readLine() ;
                                        if (originalDataLine == null || originalDataLine.trim().length() == 0 )
                                        {
                                            break;
                                        }

                                        String mockDataLine = mockDataBufferedReader.readLine();
                                        if (mockDataLine == null)
                                        {
                                            throw new RuntimeException("Ran out of mock data lines!");
                                        }

                                        String [] originalDataRow = originalDataLine.split("\\|");
                                        if (originalDataRow.length == 0)
                                        {
                                            break; //if blank line, break.
                                        }
//													String [] originalDataRowCSV = originalDataLine.split("|"); //if CSV and NOT Pipes
//													if (originalDataRow.length == 0)
//													{
//													break; //if blank line, break.
//													}

                                        String [] mockDataRow = mockDataLine.split("\\|"); // Split upon Pipes | | 

                                        List <String> mockIndivRow = new LinkedList<>();
                                        List <String> mockCustomerRow = new LinkedList<>();
                                        List <String> originalHouseHoldRow = new LinkedList<>();
                                        //ToDo (UHOH) Build mockcustomerRow and Build mockNIndivRow from elements in originalDataRow and MockDataRow

                                        originalHouseHoldRow.add(originalDataRow[0]); // Original data information inputs; can be modified to only generate data
                                        originalHouseHoldRow.add(originalDataRow[1]);
                                        originalHouseHoldRow.add(originalDataRow[2]);
                                        originalHouseHoldRow.add(originalDataRow[3]);
                                        originalHouseHoldRow.add(originalDataRow[4]);
                                        originalHouseHoldRow.add(originalDataRow[5]);
                                        originalHouseHoldRow.add(originalDataRow[6]);
                                        originalHouseHoldRow.add(originalDataRow[7]);
                                        originalHouseHoldRow.add(originalDataRow[8]);
                                        originalHouseHoldRow.add(originalDataRow[9]);
                                        originalHouseHoldRow.add(originalDataRow[10]);
                                        originalHouseHoldRow.add(originalDataRow[11]);

//													originalHouseHoldRow.add(originalDataRowCSV[0]);
//													originalHouseHoldRow.add(originalDataRowCSV[1]);
//													originalHouseHoldRow.add(originalDataRowCSV[2]);
//													originalHouseHoldRow.add(originalDataRowCSV[3]);
//													originalHouseHoldRow.add(originalDataRowCSV[4]);
//													originalHouseHoldRow.add(originalDataRowCSV[5]);
//													originalHouseHoldRow.add(originalDataRowCSV[6]);
//													originalHouseHoldRow.add(originalDataRowCSV[7]);
//													originalHouseHoldRow.add(originalDataRowCSV[8]);
//													originalHouseHoldRow.add(originalDataRowCSV[9]);
//													originalHouseHoldRow.add(originalDataRowCSV[10]);
//													originalHouseHoldRow.add(originalDataRowCSV[11]);

                                        mockIndivRow.add(originalDataRow[0]); // addr_id
                                        mockIndivRow.add(mockDataRow[0]); // indiv_id
                                        mockIndivRow.add(mockDataRow[1]); // frst_nm
                                        mockIndivRow.add(mockDataRow[2]); // mid_initial
                                        mockIndivRow.add(mockDataRow[3]); // lst_nm
                                        mockIndivRow.add(mockDataRow[4]); // age
                                        mockIndivRow.add(mockDataRow[6]); //gndr_cd

                                        mockCustomerRow.add(originalDataRow[0]); // addr_id
                                        mockCustomerRow.add(mockDataRow[0]); // indiv_id
                                        mockCustomerRow.add(mockDataRow[7]); // acct_nbr
                                        mockCustomerRow.add(mockDataRow[9]); // acct_type_ind

                                        mockIndivWriter.write(String.join("|",mockIndivRow));
                                        mockIndivWriter.write('\n');

                                        mockCustomerWriter.write(String.join("|", mockCustomerRow));
                                        mockCustomerWriter.write('\n');

                                        originalHouseholdWriter.write(String.join("|", originalHouseHoldRow));
                                        originalHouseholdWriter.write('\n');

                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
