package com.example.smaato.restwebservicechallenge.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smaato.restwebservicechallenge.exception.ApplicationException;
import com.example.smaato.restwebservicechallenge.httpclient.RestClient;
import com.example.smaato.restwebservicechallenge.httpclient.RestMessage;
import com.example.smaato.restwebservicechallenge.service.UniqueIdService;

@RestController
@RequestMapping("/api")
public class UniqueIdController {
	Logger logger = LoggerFactory.getLogger(UniqueIdController.class);

	@Autowired
	UniqueIdService uniqueIdService;

	@Autowired
	RestClient restClient;

	@GetMapping(path = "/smaato/accept")
	public ResponseEntity<String> acceptId(@RequestParam(required = true) Integer id,
			@RequestParam(required = false) String url) {
		logger.info("Entered into api /smaato/accept with Id" + id);
		try {
			uniqueIdService.saveUniqueId(id);
			if (null!=url) {
				Date d = new Date();
				List<Integer> listdOfIds = uniqueIdService.getUniqueIdList(d);
				logger.info("listofIds "+ listdOfIds+"for date "+d);
				restClient.postcall(listdOfIds, url);
			}
		} catch (ApplicationException e) {
			logger.error("Error in saving uniqueId " + e.getMessage());
			return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<>("ok", HttpStatus.OK);

	}

	@PostMapping(path = "/smaato/id")
	public ResponseEntity<String> recieveIds(@RequestBody RestMessage message) {
		logger.info("Entered into api /smaato/id with count" + message.getCount());
			return new ResponseEntity<>("ok", HttpStatus.OK);
	}

}
