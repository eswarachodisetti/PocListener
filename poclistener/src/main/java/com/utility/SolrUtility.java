package com.utility;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

import com.accounts.AddAccountRequest;

public class SolrUtility {

	public void insertDocument(String xml) {
		try {
			AddAccountRequest request = null;
			PropertyUtility prop = new PropertyUtility();
			request = JMSUtility.convertXMLToObject(request, xml);
			System.out.println("Inside SolrUtility()");
			String url = prop.readProperty("SOLR_URL");

			HttpSolrClient solr = new HttpSolrClient.Builder(url).build();
			solr.setParser(new XMLResponseParser());
			SolrInputDocument document = new SolrInputDocument();

			if (request != null) {

				document.addField("id", request.getObjId());
				document.addField("ExternalId", request.getExternalId());
				document.addField("TypeCode", request.getTypeCode());
				document.addField("ExpirationTimeStamp", request.getExpirationTimestamp());
				document.addField("CustomerView", request.getCustomerView());
				document.addField("CustomerVIewModel", request.getCustomerViewModelValue());
				document.addField("CompanyTypeCode", request.getCompanyTypeCode());
				document.addField("CompanyDescription", request.getCompanyDescription());
				document.addField("PhoneTypeCode", request.getPhoneTypeCode());
				document.addField("PhoneCountryCode", request.getPhcountryCode());
				document.addField("AreaCode", request.getAreaCode());
				document.addField("PhoneNumber", request.getPhoneNumber());
				document.addField("Extension", request.getExtension());
				document.addField("AddressTypeCode", request.getAddressTypeCode());
				document.addField("StreetLine1", request.getStreetLine1());
				document.addField("StreetLine2", request.getStreetLine2());
				document.addField("StreetLine3", request.getStreetLine3());
				document.addField("City", request.getCity());
				document.addField("State", request.getState());
				document.addField("PostalCode", request.getPostalCode());
				document.addField("AddCountryCode", request.getAddcountryCode());

			}
			solr.add(document);

			solr.commit();

		} catch (SolrServerException e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
