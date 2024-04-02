package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePayment;
	
	public ContractService(OnlinePaymentService onlinePayment) {
		this.onlinePayment = onlinePayment;
	}
	
	public void processContract(Contract contract, Integer months) {
		
		double value = contract.getTotalValue() / months;
		
		for(int i = 1; i <= months; i++) {
			double amount = value + onlinePayment.interest(value, i);
			amount += onlinePayment.paymentFee(amount);
			
			LocalDate dueDate = contract.getDate().plusMonths(i);
			
			contract.getInstallment().add(new Installment(dueDate, amount));
		}
		
	}

}
