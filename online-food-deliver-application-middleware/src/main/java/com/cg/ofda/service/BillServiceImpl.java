package com.cg.ofda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.BillEntity;
import com.cg.ofda.entity.CustomerEntity;
import com.cg.ofda.entity.FoodCartEntity;
import com.cg.ofda.entity.OrderDetailsEntity;
import com.cg.ofda.exception.BillException;
import com.cg.ofda.exception.CustomerException;
import com.cg.ofda.model.BillModel;
import com.cg.ofda.repository.IBillRepository;
import com.cg.ofda.repository.ICustomerRepository;
import com.cg.ofda.util.EMParserBill;

@Service
public class BillServiceImpl implements IBillService {
	
	public static final String NOT_FOUND = "no bill with id #";
	public static final String PRESENT = " present";
	
   
	/*
	 * Bill Repository is Autowired 
     */
	
	@Autowired
	private IBillRepository billRepo;
	
	
	
	@Autowired
	private ICustomerRepository custRepo;
	
	

	/*
	 * EMParserBill is Autowired 
     */
	
	
	@Autowired
	private EMParserBill parser;
	
	/*
	 * Default constructor
     */

	public BillServiceImpl() {
		this.parser = new EMParserBill();
	}

	/*
	 * Parameterized for assigning
	 */

	public BillServiceImpl(IBillRepository billRepo) {
		super();
		this.billRepo = billRepo;
		this.parser = new EMParserBill();
	}

	/*
	 * Implementation of addBill method to add bill
	 */

	@Transactional
	@Override
	public BillModel addBill(BillModel bill) throws BillException {
		if (bill != null) {
			if (billRepo.existsById(bill.getBillId())) {
				throw new BillException("Bill with this id already exists");
			}
			bill = parser.parse(billRepo.save(parser.parse(bill)));
		}
		return bill;
	}

	/*
	 * Implementation of updateBill method to update existing bill
	 */

	@Transactional
	@Override
	public BillModel updateBill(BillModel bill) throws BillException {

		BillEntity bill1 = billRepo.findById(bill.getBillId()).orElse(null);
		if (bill1 == null) {
			throw new BillException(NOT_FOUND + bill.getBillId() + PRESENT);
		}
		bill = parser.parse(billRepo.save(parser.parse(bill)));
		return bill;
	}

	/*
	 * Implementation of removeBill method to remove existing bill
	 */

	@Transactional
	@Override
	public boolean removeBill(Long billId) throws BillException {
		boolean isDeleted = false;
		BillEntity oldbill = billRepo.findById(billId).orElse(null);
		if (oldbill == null) {
			throw new BillException(NOT_FOUND + billId + PRESENT);
		} else {
			billRepo.deleteById(billId);
			isDeleted = true;
		}
		return isDeleted;

	}

	/*
	 * Implementation of viewBill method to view a bill
	 */

	@Override
	public BillModel viewBill(Long billId) throws BillException {
		BillEntity oldbill = billRepo.findById(billId).orElse(null);
		if (oldbill == null) {
			throw new BillException(NOT_FOUND + billId + PRESENT);
		}
		return parser.parse(billRepo.findById(billId).orElse(null));
	}

	/*
	 * Implementation of viewAllBills method to view all bills
	 */

	@Override
	public List<BillModel> viewAllBills() throws BillException {

		return billRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}
	
	
	/*
	 *  Implementation of view bill method to view a bill by getting customer id as a input
	 *  
	 */

	@Override
	public List<BillModel> viewAllBillsByCustomer(Long customerId) throws BillException, CustomerException {
		
		CustomerEntity customer = custRepo.findById(customerId).orElse(null);
		
		if (customer == null) {
			throw new CustomerException("No custome with #" + customerId + " present");
		}
		
		FoodCartEntity cart= customer.getFoodCart();
		
		List<OrderDetailsEntity> orders= cart.getOrders();
		
		List<BillEntity> bill= new ArrayList<>();
		
		for(OrderDetailsEntity order : orders) {
			bill.add(order.getBill());
		}
		
		return bill.stream().map(parser::parse).collect(Collectors.toList());
		}

}