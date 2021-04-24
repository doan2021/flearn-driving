package com.ktgroup.application.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktgroup.application.common.Constant;
import com.ktgroup.application.entities.Customer;
import com.ktgroup.application.entities.CustomerHistory;
import com.ktgroup.application.respone.ResponeData;
import com.ktgroup.application.responsitories.CustomerHistoryRespository;
import com.ktgroup.application.responsitories.CustomerRespository;

@Service
public class CustomerServices {

	@Autowired
	private CustomerRespository customerRespository;
	
	@Autowired
	private CustomerHistoryRespository customerHistoryRespository;
	
	public ResponeData findAllCustomer(int pageCurrent) {
    	ResponeData responeData = new ResponeData();
    	List<Customer> listCustomer = customerRespository.findAll();
    	if (Objects.isNull(listCustomer)) {
    		responeData.setStatus(Constant.STATUS_ERROR);
    		responeData.setMessage("System error!");
    		return responeData;
    	}
    	
    	if (listCustomer.size() == 0) {
    		responeData.setStatus(Constant.STATUS_NOT_FOUND);
    		responeData.setMessage("No record!");
    		return responeData;
    	}
		responeData.setStatus(Constant.STATUS_SUCCESS);
    	responeData.setResult(customerRespository.findAll(PageRequest.of(pageCurrent, 20)));
		return responeData;
	}
	
	@Transactional
	public ResponeData importDataCustomer(MultipartFile fileCsv, String userName) {
    	ResponeData responeData = new ResponeData();
        if (fileCsv.isEmpty()) {
            return responeData;
        }
        BufferedReader br;
        List<Customer> listCustomer = new ArrayList<>();
        try {
             String line;
             InputStream is = fileCsv.getInputStream();
             br = new BufferedReader(new InputStreamReader(is));
             String [] result;
             boolean isFirst = true;
             while ((line = br.readLine()) != null) {
            	 if(!isFirst) {
            		 System.out.println(line);
            		 result = line.split(",");
            		 Customer customer = new Customer();
            		 try {
            			 customer.setId(Long.parseLong(result[0]));
            			 customer.setName(result[1]);
            			 customer.setNumberPhone(result[2]);
            			 customer.setDescription(result[3]);
            			 customer.setCreateAt(userName);
            			 customer.setCreateDate(new Date());
            			 customer.setUpdateAt(userName);
            			 customer.setUpdateDate(new Date());
            		 } catch (Exception e) {
            			System.err.println(line);
            			continue;
            		 }
            		 // insert history
         			List<CustomerHistory> listHistory = new ArrayList<>();
         				CustomerHistory history = new CustomerHistory();
         				history.setCustomer(customer);
            			history.setContent("Create data customer");
            			history.setCreateAt(userName);
            			history.setCreateDate(new Date());
            			history.setUpdateAt(userName);
            			history.setUpdateDate(new Date());
            			listHistory.add(history);
            		customer.setListHistory(listHistory);
            		listCustomer.add(customer);
            	 }
            	 isFirst = false;
             }
             customerRespository.saveAll(listCustomer);
          } catch (IOException e) {
        	  System.err.println(e.getMessage());

          }
        return responeData;
	}

	public ResponeData getHistoryCustomer(Long customerId) {
		ResponeData responeData = new ResponeData();
		Customer customer = customerRespository.getOne(customerId);
		responeData.setResult(customerHistoryRespository.findByCustomer(customer));
		return responeData;
	}
}
