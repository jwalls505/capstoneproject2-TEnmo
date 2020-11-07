package com.techelevator.tenmo.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.TransferDTO;

public class RestTransferService {
	public static String AUTH_TOKEN = "";
	private final String BASE_URL;
	private final RestTemplate restTemplate = new RestTemplate();

	public RestTransferService(String url) {
		this.BASE_URL = url;
	}

	public Transfer[] getTransfersforUser() {
		Transfer[] transfers;
		transfers = restTemplate.exchange(BASE_URL + "transfers", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
		return transfers;
	}

	public Transfer getTransferById() {
		Transfer transfer = null;
		transfer = restTemplate.exchange(BASE_URL + "transfers/{id}", HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
		return transfer;
	}

	public Transfer sendTransfer(TransferDTO transferDto) throws TransferServiceException {
		Transfer confirmation = null;
		try {
			confirmation = restTemplate.exchange(BASE_URL + "transfers", HttpMethod.POST, makeAuthTransferDTO(transferDto), Transfer.class).getBody();
			System.out.println("Approved");
			return confirmation;
		} catch (RestClientResponseException ex) {
			throw new TransferServiceException(
					ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString() + "Insufficient funds");
		} catch (ResourceAccessException ex) {
			throw new TransferServiceException(ex.getMessage());
		}
	}

	public Transfer[] viewPendingTransfers() throws TransferServiceException {
		Transfer[] pending = null;
		
		pending = restTemplate.exchange(BASE_URL + "transfers/pending", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
	
		return pending;

	}

	public Account[] viewAvailableAccounts() throws AccountServiceException {
		Account[] account = null;
		try {
			account = restTemplate.exchange(BASE_URL + "accounts", HttpMethod.GET, makeAuthEntity(), Account[].class).getBody();
			return account;

		} catch (RestClientResponseException ex) {
			throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
		} catch (ResourceAccessException ex) {
			throw new AccountServiceException(ex.getMessage());
		}
	}

	private HttpEntity makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}

	private HttpEntity makeAuthTransferDTO(TransferDTO transferDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(transferDto, headers);
		return entity;
	}

}
