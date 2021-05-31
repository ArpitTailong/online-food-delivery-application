package com.cg.ofda.service;

import java.util.List;

import com.cg.ofda.exception.BillException;
import com.cg.ofda.exception.CustomerException;
import com.cg.ofda.model.BillModel;

public interface IBillService {

	/* Definition of addBill method for adding the Bill */
	public BillModel addBill(BillModel bill) throws BillException;

	/* Definition of updatingBill method for updating the Bill */
	public BillModel updateBill(BillModel bill) throws BillException;

	/* Definition of removeBill method for removing the Bill */
	public boolean removeBill(Long billId) throws BillException;

	/* Definition of viewBill method for viewing the required Bill */
	public BillModel viewBill(Long billId) throws BillException;

	/*
	 * Definition of viewBills method for viewing all the bills
	 */
	public List<BillModel> viewAllBills() throws BillException;
	
	
	/*
	 * Defination of view Bill method that takes customer id and return the bill of the customer
	 * */
	public List<BillModel> viewAllBillsByCustomer(Long customerId) throws BillException, CustomerException;

}
