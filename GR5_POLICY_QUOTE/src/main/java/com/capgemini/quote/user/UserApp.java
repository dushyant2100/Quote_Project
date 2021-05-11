package com.capgemini.quote.user;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.capgemini.quote.bean.Accounts;
import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyDetails;
import com.capgemini.quote.bean.PolicyQuestion;
import com.capgemini.quote.bean.UserRole;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.service.AccountsService;
import com.capgemini.quote.service.AccountsServiceImpl;
import com.capgemini.quote.service.PolicyService;
import com.capgemini.quote.service.PolicyServiceImpl;
import com.capgemini.quote.service.UserService;
import com.capgemini.quote.service.UserServiceImpl;
import com.capgemini.quote.service.ViewService;
import com.capgemini.quote.service.ViewServiceImpl;

public class UserApp {
	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties"); // Logger File Properties
		// Service Layer Objects
		UserService uServ = new UserServiceImpl();
		AccountsService aServ = new AccountsServiceImpl();
		PolicyService pServ = new PolicyServiceImpl();
		ViewService vServ = new ViewServiceImpl();

		Scanner sc = new Scanner(System.in);
		int ans;
		Accounts accountC;

		String ch = "yes";

		do {
			try {
				System.out.println("\t----------\t Online Insurance Quote Generation System\t----------");
				System.out.println("\nLogin using your Credentials=>");
				System.out.println("\tUsername:");
				String username = sc.next();
				System.out.println("\tPassword:");
				String password = sc.next();
				UserRole userRole = uServ.loginCheck(username, password); // User validation without regex
				System.out.println("\n---------------------------------------------------\n");

				ch = "no";
				switch (userRole.getRoleCode()) {
				case "insurer":
					do {
						try {
							ch = "no";
							System.out.println("Hello " + userRole.getUserName() + ", you are logged in as an Insurer");
							System.out.println("Insurer Functionalities>>");
							System.out.println("\t1. Create Account");
							System.out.println("\t2. View Account Details");
							System.out.println("\t3. View Policy Details");

							System.out.println("Enter Choice");
							ans = sc.nextInt();
							switch (ans) {
							case 1: // Account Creation

								Accounts accDetail = aServ.getAccountsByUsername(userRole.getUserName(),
										userRole.getRoleCode());

								if (userRole.getRoleCode().equals("insurer") && accDetail == null) {
									System.out.println("Input the following feilds for account generation>>");
									System.out.println(
											"Enter data according to the guidline below: \n( Name starts with Capital letter and no spaces)\n");
									System.out.print("First Name: ");
									String name = sc.next();
									System.out.println(
											"\n( No spaces,No special characters");
									System.out.print("\nAddress: ");
									String address = sc.next();
									System.out.println(
											"\n( No spaces, No special characters");
									System.out.print("\nCity: ");
									String city = sc.next();
									System.out.println(
											"\n( No spaces, No special character");
									System.out.print("\nState: ");
									String state = sc.next();
									System.out.println("\n(Enter 5 digit zip code)");
									System.out.print("\nZip Code: ");
									long zipCode = sc.nextLong();
									System.out.println("Print list");
									System.out.print("\nType of Business Segment: Choose from below Options only\n");
									System.out.println("'B' for Business Auto");
									System.out.println("'R' for Restaurant");
									System.out.println("'A' for Apartment");
									System.out.println("'G' for General Merchant");
									String busSegType = sc.next();

									Accounts account = new Accounts(name, address, city, state, zipCode, busSegType,
											userRole.getUserName(), userRole.getUserName());
									accountC = aServ.accountCreation(account);

									if (accountC == null) {
										System.err.println("Enter Data according to the guidelines specified.\n");
									} else {
										System.out.println("Account Created: " + accountC);
									}

								} else {
									System.err.println(
											"Account already exist, Account can be created only one time by the insurer.");
									accountC = null;
								}

								break;

							case 2: // View Account Details
								try {
									Accounts acc = aServ.getAccountsByUsername(userRole.getUserName(),
											userRole.getRoleCode());
									System.out.println(acc);
								} catch (Exception e) {
									System.err.println("No Account Found");
								}
								break;

							case 3: // View Policy Details
								try {
                                    Accounts account = aServ.getAccountsByUsername(userRole.getUserName());
                                    List<Policy> myPolicy = vServ.getPolicybyAccNo(account.getAccountNumber());
                                    if(!myPolicy.isEmpty()){
                                    for (Policy mp : myPolicy) {
                                        System.out.println(mp);
                                    }
                                    }
                                    else {
                                        System.err.println("No Policy been attached to Account.");
                                    }
                                } catch (Exception e) {
                                    System.err.println("No Policy or Account Found");
                                }
                                break;
							default:
								System.err.println("Wrong Input");
								break;
							}

						} catch (CustomException e) {
							System.err.println(e.getMessage());
						} catch (Exception e) {
							System.err.println("Invalid Input.");
						}
						System.out.println("Do u wish to continue?");
						ch = sc.next();
						System.out.println("\n---------------------------------------------------\n");
					} while (ch.equals("yes"));
					break;

				case "admin":
					do {
						try {
							ch = "no";
							System.out.println("Hello " + userRole.getUserName() + ", you are logged in as an Admin");
							System.out.println("Admin Functionalities>>");
							System.out.println("\t1. New Profile Creation");
							System.out.println("\t2. Account Creation");
							System.out.println("\t3. Policy Creation");
							System.out.println("\t4. View Account Details of customer");
							System.out.println("\t5. View Policy Details");
							System.out.println("\t6. Generate Report");
							System.out.println("Enter Choice");
							ans = sc.nextInt();
							switch (ans) {
							case 1: // New Profile Creation

								System.out.println("Enter the following Profile Creation Details>>");
								System.out.println("Username: ");
								System.out.println(
										"Follow these rules while entering username:\n( Username must be 5-20 characters long, No _ at the beginning, alpha numeric)");

								String userName = sc.next();
								System.out.println("Password: ");
								System.out.println(
										"Follow these rules while entering password:\n( Min 1 uppercase, min 1 lowercase, min 1 special character, min 1 number, min 8 characters.)");

								String passWord = sc.next();
								System.out.println("\nUser Role: ");
								System.out.println(
										"Choose User Role, roles Available are- \n\tadmin\t\\\tagent\t\\\tinsurer \n(input must be in lowercase)");

								String userR = sc.next();
								UserRole userRole1 = new UserRole(userName, passWord, userR);
								String profile = uServ.createProfile(userRole1);
								if (profile == null) {
									System.err.println("Enter Username and password as instructed");
								} else {
									System.out.println("Profile Created: " + profile);
								}

								break;

							case 2: // Account Creation

								System.out.println("Input the following feilds for account generation>>");
								System.out.println(
										"Enter data according to the guidline below: \n( Name starts with Capital letter and no spaces)\n");
								System.out.print("First Name: ");
								String name = sc.next();
								System.out.println(
										"\n( No spaces,No special characters");
								System.out.print("\nAddress: ");
								String address = sc.next();
								System.out.println(
										"\n( No spaces, No special characters");
								System.out.print("\nCity: ");
								String city = sc.next();
								System.out.println(
										"\n( No spaces, No special character");
								System.out.print("\nState: ");
								String state = sc.next();
								System.out.println("\n(Enter 5 digit zip code)");
								System.out.print("\nZip Code: ");
								long zipCode = sc.nextLong();
								System.out.println("Print list");
								System.out.print("\nType of Business Segment: Choose from below Options only\n");
								System.out.println("'B' for Business Auto");
								System.out.println("'R' for Restaurant");
								System.out.println("'A' for Apartment");
								System.out.println("'G' for General Merchant");
								String busSegType = sc.next();
								System.out.print("\nUserName from profile: ");
								String uName = sc.next();

								Accounts a = new Accounts(name, address, city, state, zipCode, busSegType, uName,
										userRole.getUserName());
								accountC = aServ.accountCreation(a);
								if (accountC == null) {
									System.err.println("Enter Data according to the guidelines specified.\n");
								} else {
									System.out.println("Account Created: " + accountC);
								}

								break;

							case 3: // Policy Creation

								// Policy Structure created
								Policy policy = new Policy(); // create new policy
								System.out.println("Please enter Insurer's username:");
								String inputUser = sc.next();
								Accounts account = aServ.getAccountsByUsername(inputUser);
								if (account == null) {
									System.out.println("Entered Invalid username");
								} else {
									policy.setAccountNumber(account.getAccountNumber());
									Policy policyC = pServ.createPolicy(policy);

									// Policy Premium evaluated according to question asked
									System.out.println("Populating questions");
									List<PolicyQuestion> pPolQuesList = pServ
											.getPolicyQuestionBySegId(account.getBusinessSegment());

									for (PolicyQuestion p : pPolQuesList) {
										String ansq = "";

										System.out.println("Question: " + p.getPolQDesc() + "?");
										System.out.println(
												"Choose from:\n\tOption A: " + p.getPolQAns1() + "\n\tOption B: "
														+ p.getPolQAns2() + "\n\tOption C: " + p.getPolQAns3());
										System.out.println("Please Enter your Option as A or B or C\n");
										ansq = sc.next();
										int val = 0;
										if ((ansq.equals("A")) || (ansq.equals("B")) || (ansq.equals("C"))) {
											if (ansq.equals("A")) {
												val = 200;
											} else if (ansq.equals("B")) {
												val = 400;
											} else {
												val = 600;
											}
										}
										PolicyDetails policyDetails = new PolicyDetails();
										policyDetails.setAnswer(val);
										policyDetails.setPolicyNumber(policyC.getPolicyNumber()); // set from create new
																									// policy
										policyDetails.setqId(p.getPolQId());

										pServ.setPolicyDetails(policyDetails);
									}
									double policyPremium = pServ.calculatePremium(policyC.getPolicyNumber());
									policyC.setAccountNumber(account.getAccountNumber()); // py.getAccnumber
									policyC.setPolicyPremium(policyPremium);
									policyC.setPolicyNumber(policyC.getPolicyNumber());
									pServ.createPolicy(policyC);
									System.out.println("Policy Created: " + policyC);
									System.out.println("\n" + policyPremium + " is your Evaluated Policy Premium.");
								}
								break;

							case 4: // View Account Details of Insurers
								List<Accounts> accountList = aServ.getAccountListByCreatedBy(userRole.getUserName());
								if (!accountList.isEmpty()) {
									for (Accounts acountc : accountList) {
										System.out.println(acountc);
									}
								} else
									System.err.println("No Account found that is made by you");
								break;

							case 5: // View Policy Details of Insurers
								List<Accounts> accountList1 = aServ.getAccountListByCreatedBy(userRole.getUserName());
                                if(!accountList1.isEmpty()) {
                                for (Accounts ac : accountList1) {
                                    List<Policy> polList = vServ.getPolicybyAccNo(ac.getAccountNumber());
                                    if(!polList.isEmpty()) {
                                    for (Policy pl : polList) {
                                        System.out.println(pl);
                                    }
                                    }else {
                                        System.err.println("No Policy found that is made by you");
                                    }
                                }
                                }else {
                                    System.err.println("Account needs to be created first");
                                }
                                break;

							case 6: // Generate Report
								System.out.println("Choose Report Type>>");
								System.out.println("\t1. Quick View");
								System.out.println("\t2. Detailed View");
								int ans3 = sc.nextInt();
								switch (ans3) {
								case 1: // Quick View
									List<Accounts> accountList2 = aServ.getAllAccounts();
									for (Accounts account2 : accountList2) {
										System.out.println("\nAccount Number: " + account2.getAccountNumber()
												+ " Insurer Name: " + account2.getInsuredName());
										List<Policy> policyList = pServ.getPolicyByAccNo(account2.getAccountNumber());
										for (Policy policy1 : policyList) {
											System.out.println("\t Policy Number: " + policy1.getPolicyNumber()
													+ ", Policy Premium: " + policy1.getPolicyPremium());
										}
									}
									break;

								case 2: // Detailed View
									System.out.println("Enter Policy Number: ");
									long polNo = sc.nextLong();
									Policy pol = vServ.getPolicyByPolicyNo(polNo);
									if (pol == null) {
										System.out.println("Policy number not valid");
									} else {
										System.out.println("\t Policy Number: " + pol.getPolicyNumber()
												+ ", PolicyPremium: " + pol.getPolicyPremium());
										Accounts accountD = aServ.getAccountbyAccNo(pol.getAccountNumber());
										System.out.println(accountD);
										List<PolicyDetails> polDetailsList = vServ
												.getPolicyDetailsByPolNo((int) pol.getPolicyNumber());
										System.out.print("List of Questions and Points Alloted: ");
										for (PolicyDetails polDet : polDetailsList) {
											PolicyQuestion polQues = vServ.getPolQuesByQId(polDet.getqId());
											System.out.print("\n\t" + polQues.getPolQDesc());
											System.out.print(": " + polDet.getAnswer());
										}
									}
									break;

								default:
									System.err.println("Wrong Input");
									break;

								}
								break;

							default:
								System.err.println("Wrong Input");
								break;
							}

						} catch (CustomException e) {
							System.err.println(e.getMessage());
						} catch (Exception e) {
							System.err.println("Invalid Input.");
						}
						System.out.println("\nDo u wish to continue?");
						ch = sc.next();
						System.out.println("\n---------------------------------------------------\n");
					} while (ch.equals("yes"));
					break;

				case "agent":
					do {
						try {
							ch = "no";
							System.out.println("Hello " + userRole.getUserName() + ", you are logged in as an Agent");
							System.out.println("Agent Functionalities>>");
							System.out.println("\t1. Account Creation");
							System.out.println("\t2. Policy Creation");
							System.out.println("\t3. View Account Details of customer");
							System.out.println("\t4. View Policy Details of customer");
							System.out.println("Enter Choice");
							ans = sc.nextInt();
							switch (ans) {
							case 1: // Account Creation

								System.out.println("Input the following feilds for account generation>>");
								System.out.println(
										"Enter data according to the guidline below: \n( Name starts with Capital letter and no spaces)\n");
								System.out.print("First Name: ");
								String name = sc.next();
								System.out.println(
										"\n( No spaces,No special characters");
								System.out.print("\nAddress: ");
								String address = sc.next();
								System.out.println(
										"\n( No spaces, No special characters");
								System.out.print("\nCity: ");
								String city = sc.next();
								System.out.println(
										"\n( No spaces, No special character");
								System.out.print("\nState: ");
								String state = sc.next();
								System.out.println("\n(Enter 5 digit zip code)");
								System.out.print("\nZip Code: ");
								long zipCode = sc.nextLong();
								System.out.println("Print list");
								System.out.print("\nType of Business Segment: Choose from below Options only\n");
								System.out.println("'B' for Business Auto");
								System.out.println("'R' for Restaurant");
								System.out.println("'A' for Apartment");
								System.out.println("'G' for General Merchant");
								String busSegType = sc.next();
								System.out.print("\nUserName from profile: ");
								String uName = sc.next();

								Accounts account = new Accounts(name, address, city, state, zipCode, busSegType, uName,
										userRole.getUserName());
								accountC = aServ.accountCreation(account);
								if (accountC == null) {
									System.err.println("Enter Data according to the guidelines specified.\n");
								} else {
									System.out.println("Account Created: " + accountC);
								}

								break;

							case 2: // Policy Creation

								// Policy Structure created
								Policy policy = new Policy(); // create new policy
								System.out.println("Please enter Insurer's username:");
								String inputUser = sc.next();
								Accounts account2 = aServ.getAccountsByUsername(inputUser);
								if (account2 == null) {
									System.out.println("Entered Invalid username");
								} else {
									policy.setAccountNumber(account2.getAccountNumber());
									Policy policyC = pServ.createPolicy(policy);

									// Policy Premium evaluated according to question asked
									System.out.println("Populating questions");
									List<PolicyQuestion> polQuesList = pServ
											.getPolicyQuestionBySegId(account2.getBusinessSegment());

									for (PolicyQuestion p : polQuesList) {
										String ansq = "";

										System.out.println("Question: " + p.getPolQDesc() + "?");
										System.out.println(
												"Choose from:\n\tOption A: " + p.getPolQAns1() + "\n\tOption B: "
														+ p.getPolQAns2() + "\n\tOption C: " + p.getPolQAns3());
										System.out.println("Please Enter your Option as A or B or C\n");
										ansq = sc.next();
										int val = 0;
										if ((ansq.equals("A")) || (ansq.equals("B")) || (ansq.equals("C"))) {
											if (ansq.equals("A")) {
												val = 200;
											} else if (ansq.equals("B")) {
												val = 400;
											} else {
												val = 600;
											}
										}
										PolicyDetails polDetails = new PolicyDetails();
										polDetails.setAnswer(val);
										polDetails.setPolicyNumber(policyC.getPolicyNumber()); // set from create new
																								// policy
										polDetails.setqId(p.getPolQId());

										pServ.setPolicyDetails(polDetails);
									}
									double policyPremium = pServ.calculatePremium(policyC.getPolicyNumber());
									policyC.setAccountNumber(account2.getAccountNumber()); // py.getAccnumber
									policyC.setPolicyPremium(policyPremium);
									policyC.setPolicyNumber(policyC.getPolicyNumber());
									pServ.createPolicy(policyC);
									System.out.println("Policy Created: " + policyC);
									System.out.println("\n" + policyPremium + " is your Evaluated Policy Premium.");
								}
								break;

							case 3: // View Account Details of my Insurers
								List<Accounts> accountList = aServ.getAccountListByCreatedBy(userRole.getUserName());
								if (!accountList.isEmpty()) {
									for (Accounts acountc : accountList) {
										System.out.println(acountc);
									}
								} else
									System.err.println("No Account found");
								break;

							case 4: // View Policy Details of my Insurers
								List<Accounts> accountList1 = aServ.getAccountListByCreatedBy(userRole.getUserName());
                                if(!accountList1.isEmpty()) {
                                for (Accounts ac : accountList1) {
                                    List<Policy> polList = vServ.getPolicybyAccNo(ac.getAccountNumber());
                                    if(!polList.isEmpty()) {
                                    for (Policy pl : polList) {
                                        System.out.println(pl);
                                    }
                                    }else {
                                        System.err.println("No Policy Found");
                                    }
                                }
                                }else {
                                    System.err.println("Account needs to be created first");
                                }
                                break;

							default:
								System.err.println("Wrong Input");
								break;
							}

						} catch (CustomException e) {
							System.err.println(e.getMessage());
						} catch (Exception e) {
							System.err.println("Invalid Input.");
						}
						System.out.println("Do u wish to continue?");
						ch = sc.next();
						System.out.println("\n---------------------------------------------------\n");
					} while (ch.equals("yes"));

					break;
				}

			} catch (CustomException e) {
				System.err.println(e.getMessage());
			}
			System.out.println("Do u wish to log in again?");
			ch = sc.next();

		} while (ch.equals("yes"));

		sc.close();
		System.out.println("Thanks for using our Services. Have a good day!");
		System.exit(0);

	}
}
