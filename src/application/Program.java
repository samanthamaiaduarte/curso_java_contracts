package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entre com os dados do contrato");
		System.out.print("Número: ");
		int contractNumber = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate contractDate = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.print("Valor do contrato: ");
		double contractValue = sc.nextDouble();
		System.out.print("Entre com o númeor de parcelas: ");
		int numberOfMonths = sc.nextInt();
		
		Contract contract = new Contract(contractNumber, contractDate, contractValue);
		
		ContractService contractService = new ContractService(new PaypalService());
		contractService.processContract(contract, numberOfMonths);
		
		System.out.println("PARCELAS:");
		for(Installment installment : contract.getInstallment()) {
			System.out.println(installment.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + " - " + String.format("%.2f", installment.getAmount()));
		}
		
		sc.close();

	}

}
